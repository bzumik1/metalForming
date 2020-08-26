package com.siemens.metal_forming.service.impl;

import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.repository.ToolRepository;
import com.siemens.metal_forming.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ToolServiceImpl implements ToolService {
    private final ToolRepository toolRepository;

    public ToolServiceImpl(@Autowired ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    @Override
    public List<Tool> findAll(Long plcId) {
        return toolRepository.findAllByPlcId(plcId);
    }
}
