package com.siemens.metal_forming.service;

import com.siemens.metal_forming.entity.Plc;

public interface PlcService {
    boolean updatePlcById(Long id, Plc updatedPlc);

    void changeCurrentTool(String ipAddress,int toolId);

}
