package com.siemens.metal_forming.service;

import com.siemens.metal_forming.dto.ToolDto;
import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.entity.Tool;

import java.util.List;
import java.util.function.Consumer;

public interface ToolService {
    List<ToolDto.Response.Overview> findAll(Long plcId);
    List<ToolDto.Response.Overview> findAll();
    void delete(Long plcId, Long toolId);
    ToolDto.Response.Overview create(Long plcId, ToolDto.Request.Create toolDto);
    ToolDto.Response.Overview update(Long plcId, Long toolId, ToolDto.Request.Update toolDto);
}
