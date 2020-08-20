package com.siemens.metal_forming.service.impl;

import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.exception.exceptions.OpcuaConnectionException;
import com.siemens.metal_forming.exception.exceptions.PlcNotFoundException;
import com.siemens.metal_forming.exception.exceptions.PlcUniqueConstrainException;
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
            opcuaConnector.connectPlc(plc);
            plc.markAsConnected();
        } catch (OpcuaConnectionException e){
            log.warn("Newly created PLC could not be connected");
            plc.markAsDisconnected();
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
}
