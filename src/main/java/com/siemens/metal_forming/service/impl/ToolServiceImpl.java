package com.siemens.metal_forming.service.impl;

import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.exception.exceptions.PlcNotFoundException;
import com.siemens.metal_forming.repository.PlcRepository;
import com.siemens.metal_forming.repository.ToolRepository;
import com.siemens.metal_forming.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
