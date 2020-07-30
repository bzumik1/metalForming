package com.siemens.metal_forming.service.impl;

import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.exception.PlcNotFoundException;
import com.siemens.metal_forming.repository.PlcRepository;
import com.siemens.metal_forming.service.PlcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlcServiceImpl implements PlcService {
    private final PlcRepository plcRepository;

    public PlcServiceImpl(@Autowired PlcRepository plcRepository) {
        this.plcRepository = plcRepository;
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
    }
}
