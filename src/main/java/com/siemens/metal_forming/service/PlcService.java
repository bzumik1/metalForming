package com.siemens.metal_forming.service;

import com.siemens.metal_forming.dto.PlcDto;
import com.siemens.metal_forming.entity.Plc;

import java.util.List;

public interface PlcService {

    List<PlcDto.Response.Overview> findAll();

    PlcDto.Response.Overview find(Long id);

    PlcDto.Response.Overview createPlc(PlcDto.Request.Create plcDto);

    void delete(Long id);

    PlcDto.Response.Overview update(Long id, PlcDto.Request.Update plcDto);
}
