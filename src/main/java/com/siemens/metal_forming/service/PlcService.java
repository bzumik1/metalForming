package com.siemens.metal_forming.service;

import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.enumerated.ConnectionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public interface PlcService {
    List<Plc> findAll();

    Optional<Plc> findById(Long id);

    Optional<Plc> findByIpAddress(String ipAddress);

    Plc create(Plc plc);

    void deleteById(Long id);

    void updateById(Long id, Plc updatedPlc);

    Plc updateByIpAddress(String ipAddress, Consumer<Plc> toUpdate);

    Plc updateById(Long id, Consumer<Plc> toUpdate);

    void changeCurrentTool(String ipAddress,int toolId);

    Tool addToolById(Tool tool);



}
