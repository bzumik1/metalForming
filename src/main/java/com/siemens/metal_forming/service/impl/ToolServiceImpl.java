package com.siemens.metal_forming.service.impl;

import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.exception.exceptions.PlcNotFoundException;
import com.siemens.metal_forming.exception.exceptions.ToolNotFoundException;
import com.siemens.metal_forming.repository.PlcRepository;
import com.siemens.metal_forming.repository.ToolRepository;
import com.siemens.metal_forming.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToolServiceImpl implements ToolService {
    private final ToolRepository toolRepository;
    private final PlcRepository plcRepository;

    public ToolServiceImpl(@Autowired ToolRepository toolRepository, PlcRepository plcRepository) {
        this.toolRepository = toolRepository;
        this.plcRepository = plcRepository;
    }

    @Override
    public List<Tool> findAll(Long plcId) {
        if(!plcRepository.existsById(plcId)){
            throw new PlcNotFoundException(plcId);
        }
        return toolRepository.findAllByPlcId(plcId);
    }

    @Override //TODO replace by update from plcService
    public void deleteByPlcIdAndToolId(Long plcId, Long toolId){
        Optional<Plc> plcInDb = plcRepository.findById(plcId);
        if (plcInDb.isPresent()) {
            Plc plc = plcInDb.get();
            Optional<Tool> toolToDelete = plc.getTools().stream().filter(tool -> tool.getId().equals(toolId)).findFirst();

            if(toolToDelete.isPresent()){
                plc.getTools().remove(toolToDelete.get());
                plcRepository.save(plc);
            } else {
                throw new ToolNotFoundException(toolId);
            }
        } else {
            throw new PlcNotFoundException(plcId);
        }
    }
}
