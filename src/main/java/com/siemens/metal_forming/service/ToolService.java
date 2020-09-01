package com.siemens.metal_forming.service;

import com.siemens.metal_forming.entity.Tool;

import java.util.List;
import java.util.function.Consumer;

public interface ToolService {
    List<Tool> findAll(Long plcId);
    void delete(Long plcId, Long toolId);
    Tool create(Long plcId, Tool tool);
    Tool update(Long plcId, Long toolId, Consumer<Tool> updateTool);
}
