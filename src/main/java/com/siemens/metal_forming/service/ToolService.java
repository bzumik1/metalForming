package com.siemens.metal_forming.service;

import com.siemens.metal_forming.entity.Tool;

import java.util.List;

public interface ToolService {
    List<Tool> findAll(Long plcId);
}
