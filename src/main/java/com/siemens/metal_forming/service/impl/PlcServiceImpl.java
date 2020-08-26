package com.siemens.metal_forming.service.impl;

import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.enumerated.ConnectionStatus;
import com.siemens.metal_forming.exception.exceptions.OpcuaConnectionException;
import com.siemens.metal_forming.exception.exceptions.PlcNotFoundException;
import com.siemens.metal_forming.exception.exceptions.PlcUniqueConstrainException;
import com.siemens.metal_forming.opcua.OpcuaClient;
import com.siemens.metal_forming.opcua.OpcuaConnector;
import com.siemens.metal_forming.repository.PlcRepository;
import com.siemens.metal_forming.service.PlcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Service @Slf4j
public class PlcServiceImpl implements PlcService {
    private final PlcRepository plcRepository;
    private final OpcuaConnector opcuaConnector;

    public PlcServiceImpl(@Autowired PlcRepository plcRepository, @Autowired OpcuaConnector opcuaConnector) {
        this.plcRepository = plcRepository;
        this.opcuaConnector = opcuaConnector;
    }

    @Override
    public List<Plc> findAll() {
        return plcRepository.findAll();
    }

    @Override
    public Optional<Plc> findById(Long id) {
        return plcRepository.findById(id);
    }

    @Override
    public Optional<Plc> findByIpAddress(String ipAddress) {
        return plcRepository.findByIpAddress(ipAddress);
    }

    @Override
    public Plc create(Plc plc) {
        hasUniqueAttributes(plc);
        try {
            OpcuaClient client = opcuaConnector.connectPlc(plc);
            plc.markAsConnected();
            plc.getHardwareInformation().setSerialNumber(client.readSerialNumber().get());
            plc.getHardwareInformation().setFirmwareNumber(client.readFirmwareNumber().get());
        } catch (OpcuaConnectionException e){
            log.warn("Newly created PLC could not be connected");
            plc.markAsDisconnected();
        } catch (InterruptedException | ExecutionException e) {
            log.warn("HardwareInformation could not be updated: {}",e.getMessage());
        }

        return plcRepository.save(plc);
    }

    private void hasUniqueAttributes(Plc plc){
        StringBuilder exceptionMessage = new StringBuilder();
        String separator = ", ";
        if(plcRepository.existsByIpAddress(plc.getIpAddress())){
            exceptionMessage.append("PLC with given IP address ").append(plc.getIpAddress()).append(" already exists").append(separator);
        }

        if (plcRepository.existsByName(plc.getName())){
            exceptionMessage.append("PLC with given name ").append(plc.getName()).append(" already exists").append(separator);
        }

        if(exceptionMessage.length()!=0){
            exceptionMessage.setLength(exceptionMessage.length()-separator.length());
            throw new PlcUniqueConstrainException(exceptionMessage.toString());
        }
    }

    @Override
    public void deleteById(Long id) {
        Optional<Plc> oldPlc = plcRepository.findById(id);
        if(oldPlc.isPresent()){
            opcuaConnector.disconnectPlc(oldPlc.get());
            plcRepository.deleteById(id);
        } else {
            throw new PlcNotFoundException(id);
        }
    }

    @Override
    public void updateById(Long id, Plc updatedPlc) {
        updatedPlc.setId(id);
        Optional<Plc> plcInDB = plcRepository.findById(id);
        if(plcInDB.isPresent()){
            if(!plcInDB.get().getIpAddress().equals(updatedPlc.getIpAddress())){
                opcuaConnector.disconnectPlc(plcInDB.get());
                create(updatedPlc);
            }else{
                plcRepository.save(updatedPlc);
            }
        } else {
            throw new PlcNotFoundException(id);
        }
    }

    @Override
    public void changeCurrentTool(String ipAddress, int toolId) {
        Plc plc = plcRepository.findByIpAddress(ipAddress).orElseThrow(() -> new PlcNotFoundException("Plc with IP address "+ipAddress+" was not found."));
        plc.setCurrentTool(toolId);
        plcRepository.save(plc);
    }

    @Override
    public Plc updateByIpAddress(String ipAddress, Consumer<Plc> updatePlc) {
        Optional<Plc> plcInDb = plcRepository.findByIpAddress(ipAddress);
        if(plcInDb.isPresent()){
            return update(plcInDb.get(),updatePlc);
        } else {
            throw new PlcNotFoundException("Plc with given IP address "+ipAddress+" was not in database");
        }
    }

    @Override
    public Plc updateById(Long id, Consumer<Plc> updatePlc) {
        Optional<Plc> plcInDb = plcRepository.findById(id);
        if(plcInDb.isPresent()){
            return update(plcInDb.get(),updatePlc);
        } else {
            throw new PlcNotFoundException("Plc with given id "+id+" was not in database");
        }
    }

    private Plc update(Plc plc, Consumer<Plc> updatePlc){
        final Plc oldPlc = plc.toBuilder().build();
        updatePlc.accept(plc);
        //if plc has different IP address then it needs to be reconnected
        if(!plc.getIpAddress().equals(oldPlc.getIpAddress())){
            opcuaConnector.disconnectPlc(oldPlc);
            create(plc);
        }
        return plcRepository.save(plc);
    }


}
