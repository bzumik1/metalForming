package com.siemens.metal_forming.service.impl;

import com.siemens.metal_forming.domain.ReferenceCurveCalculation;
import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.exception.exceptions.CalculationNotFoundException;
import com.siemens.metal_forming.service.ReferenceCurveCalculationService;
import com.siemens.metal_forming.service.ToolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class ReferenceCurveCalculationServiceImpl implements ReferenceCurveCalculationService {
    private final ToolService toolService;
    private final Map<Long, ReferenceCurveCalculation> calculations = new HashMap<>(); //ToDo - should be thread save

    @Autowired
    public ReferenceCurveCalculationServiceImpl(@Lazy ToolService toolService) {
        this.toolService = toolService;
    }

    @Override
    public void removeCalculation(long toolId) {
        log.debug("Removing calculation of reference curve for tool with id: {}", toolId);
        calculations.remove(toolId);
    }

    @Override
    public void addCalculation(long toolId, int numberOfReferenceCycles) {
        log.debug("Starting working on calculation of reference curve with {} cycles for tool with id: {}", numberOfReferenceCycles, toolId);
        calculations.put(toolId, new ReferenceCurveCalculation(numberOfReferenceCycles));
    }

    @Override
    public Optional<Curve> calculate(long toolId, Curve newCurveForCalculation) {
        log.debug("Adding curve to calculation of reference curve for tool with id: {}", toolId);
        Optional<ReferenceCurveCalculation> calculation = Optional.ofNullable(calculations.get(toolId));

        Optional<Curve> referenceCurve = calculation.orElseThrow(() -> new CalculationNotFoundException(toolId))
                .calculate(newCurveForCalculation);

        if(referenceCurve.isPresent()){
            toolService.updateReferenceCurve(toolId, referenceCurve.get());
            log.debug("Reference curve for tool with id {} was successfully calculated",toolId);
        }

        return referenceCurve;
    }

    @Override
    public Optional<ReferenceCurveCalculation> getReferenceCurveCalculation(long toolId) {
        return Optional.ofNullable(calculations.get(toolId));
    }
}
