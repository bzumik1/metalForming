package com.siemens.metal_forming.service.impl;

import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.exception.OpcuaConnectionException;
import com.siemens.metal_forming.exception.PlcNotFoundException;
import com.siemens.metal_forming.opcua.OpcuaClient;
import com.siemens.metal_forming.opcua.OpcuaConnector;
import com.siemens.metal_forming.repository.PlcRepository;
import com.siemens.metal_forming.service.PlcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Plc> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Plc> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Plc> findByIpAddress(String ipAddress) {
        return Optional.empty();
    }

    @Override
    public Plc create(Plc plc) {
        try {
            OpcuaClient opcuaClient = opcuaConnector.connectPlc(plc);
            plc.markAsConnected();
        } catch (OpcuaConnectionException e){
            log.warn("newly created PLC could not be connected");
            plc.markAsDisconnected();
        }

        return plcRepository.save(plc);
    }

    @Override
    public boolean deletePlcById(Long id) {
        return false;
    }

    @Override
    public boolean updatePlcById(Long id, Plc updatedPlc) {
        updatedPlc.setId(id);
        Optional<Plc> plcInDB = plcRepository.findById(id);
        if(plcInDB.isPresent()){
            plcRepository.save(updatedPlc);
            return true;
        }
        return false;
    }

    @Override
    public void changeCurrentTool(String ipAddress, int toolId) {
        Plc plc = plcRepository.findByIpAddress(ipAddress).orElseThrow(PlcNotFoundException::new);
        plc.setCurrentTool(toolId);
        plcRepository.save(plc);
    }
}
