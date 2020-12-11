package com.siemens.metal_forming.service.impl;

import com.siemens.metal_forming.connection.PlcData;
import com.siemens.metal_forming.domain.ReferenceCurveCalculation;
import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.exception.exceptions.CalculationNotFoundException;
import com.siemens.metal_forming.exception.exceptions.ToolNotFoundException;
import com.siemens.metal_forming.repository.PlcRepository;
import com.siemens.metal_forming.repository.ToolRepository;
import com.siemens.metal_forming.service.ReferenceCurveCalculationService;
import com.siemens.metal_forming.service.ToolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class ReferenceCurveCalculationServiceImpl implements ReferenceCurveCalculationService {
    private final PlcRepository plcRepository;
    private final ToolRepository toolRepository;
    private final Map<Long, ReferenceCurveCalculation> calculations = new HashMap<>(); //ToDo - should be thread save

    @Autowired
    public ReferenceCurveCalculationServiceImpl(PlcRepository plcRepository, ToolRepository toolRepository) {
        this.plcRepository = plcRepository;
        this.toolRepository = toolRepository;
    }

    @Transactional
    @Override
    public void onMeasuredCurveChange(PlcData plcData) {
        Optional<Plc> optionalPlcInDb = plcRepository.findByIpAddress(plcData.getIpAddress());
        if(optionalPlcInDb.isPresent()){
            Plc plcInDb = optionalPlcInDb.get();
            Tool currentTool = plcInDb.getCurrentTool();
            Curve measuredCurve = plcData.getMeasuredCurve();

            if(currentTool.getCalculateReferenceCurve()){
                Optional<ReferenceCurveCalculation> calculation = getReferenceCurveCalculation(currentTool.getId());
                if(calculation.isEmpty()){
                    addCalculation(currentTool.getId(),currentTool.getNumberOfReferenceCycles());
                }
                calculate(currentTool.getId(), measuredCurve);
            }
        } else {
            log.warn("During reference curve calculation for tool of PLC with IP address {}, the PLC wasn't found in database", plcData.getIpAddress());
        }
    }

    @Override
    public void onToolNumberChange(PlcData plcData) {
        Optional<Plc> optionalPlcInDb = plcRepository.findByIpAddress(plcData.getIpAddress());
        if(optionalPlcInDb.isPresent()){
            Plc plcInDb = optionalPlcInDb.get();
            removeCalculation(plcInDb.getCurrentTool().getId()); //Todo this could be problem because the tool can be changed before calculation is deleted
        } else {
            log.warn("During cancellation of reference curve calculation for tool of PLC with IP address {}, the PLC wasn't found in database", plcData.getIpAddress());
        }
    }

    private void removeCalculation(long toolId) {
        log.debug("Removing calculation of reference curve for tool with id: {}", toolId);
        calculations.remove(toolId);
    }


    private void addCalculation(long toolId, int numberOfReferenceCycles) {
        log.debug("Starting working on calculation of reference curve with {} cycles for tool with id: {}", numberOfReferenceCycles, toolId);
        calculations.put(toolId, new ReferenceCurveCalculation(numberOfReferenceCycles));
    }


    private void calculate(long toolId, Curve newCurveForCalculation) {
        log.debug("Adding curve to calculation of reference curve for tool with id: {}", toolId);
        Optional<ReferenceCurveCalculation> calculation = getReferenceCurveCalculation(toolId);

        Optional<Curve> referenceCurve = calculation.orElseThrow(() -> new CalculationNotFoundException(toolId))
                .calculate(newCurveForCalculation);

        if(referenceCurve.isPresent()){
            Tool toolInDb = toolRepository.findById(toolId).orElseThrow(() -> new ToolNotFoundException(toolId));
            toolInDb.setReferenceCurve(referenceCurve.get());
            toolInDb.setCalculateReferenceCurve(false);
            toolRepository.save(toolInDb);
            log.debug("Reference curve for tool with id {} was successfully calculated",toolId);
        }
    }


    private Optional<ReferenceCurveCalculation> getReferenceCurveCalculation(long toolId) {
        return Optional.ofNullable(calculations.get(toolId));
    }
}
