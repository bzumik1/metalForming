package com.siemens.metal_forming.service;

import com.siemens.metal_forming.entity.Plc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PlcService {
    List<Plc> findAll();

    Optional<Plc> findById(Long id);

    Optional<Plc> findByIpAddress(String ipAddress);

    Plc create(Plc plc);

    void deleteById(Long id);

    void updateById(Long id, Plc updatedPlc);

    void changeCurrentTool(String ipAddress,int toolId);

}
