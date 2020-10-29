package com.siemens.metal_forming.service;

import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.enumerated.ConnectionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public interface PlcService {
    void checkIfExistsOrThrowException(Long id);

    List<Plc> findAll();

    Optional<Plc> find(Long id);

    Optional<Plc> find(String ipAddress);

    Plc create(Plc plc);

    void delete(Long id);

    Plc replace(Long id, Plc updatedPlc);

    Plc update(String ipAddress, Consumer<Plc> toUpdate);

    Plc update(Long id, Consumer<Plc> toUpdate);

    void changeCurrentTool(String ipAddress,int toolId);

    void processNewCurve(String ipAddress, Curve measuredCurve);



}
