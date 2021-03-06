package com.siemens.metal_forming.service.impl;

import com.siemens.metal_forming.connection.PlcData;
import com.siemens.metal_forming.domain.ReferenceCurveCalculation;
import com.siemens.metal_forming.domain.Curve;
import com.siemens.metal_forming.dto.ToolDto;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.repository.ToolRepository;
import com.siemens.metal_forming.service.ReferenceCurveCalculationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class ReferenceCurveCalculationServiceImpl implements ReferenceCurveCalculationService {
    private final ToolRepository toolRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final Map<String, ReferenceCurveCalculation> calculations = new HashMap<>(); //ToDo - should be thread save

    @Autowired
    public ReferenceCurveCalculationServiceImpl(ToolRepository toolRepository, SimpMessagingTemplate simpMessagingTemplate) {
        this.toolRepository = toolRepository;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Transactional
    @Override
    public void onMeasuredCurveChange(PlcData plcData) {
        final String ipAddress = plcData.getIpAddress();
        final Curve measuredCurve = plcData.getMeasuredCurve();

        Optional<Tool> toolInDb = toolRepository.findByPlcIpAddressAndToolNumber(ipAddress, plcData.getToolData().getToolNumber());
        if(toolInDb.isPresent()){
            Tool currentTool = toolInDb.get();

            if(currentTool.getCalculateReferenceCurve()){
                ReferenceCurveCalculation calculation = calculations.get(ipAddress);

                if(calculation==null){
                    log.info("Starting reference curve calculation for tool with id {}", currentTool.getId());
                    calculation = new ReferenceCurveCalculation(currentTool.getNumberOfReferenceCycles());
                    calculations.put(ipAddress, calculation);
                    log.debug("Reference calculation status for tool with id {} is: {}", currentTool.getId(), calculation.getStatus());
                }


                Optional<Curve> referenceCurve = calculation.calculate(measuredCurve);

                //Send information over WebSocket
                log.debug("Sending new tool with number \"{}\" for PLC with IP {} over WebSocket",plcData.getToolData().getToolNumber(), plcData.getIpAddress());
                simpMessagingTemplate.convertAndSend("/topic/tools/calculation-status", new ToolDto.Response.ReferenceCurveCalculation(currentTool.getId(),calculation.getStatus()));

                if(referenceCurve.isPresent()){
                    currentTool.setReferenceCurve(referenceCurve.get());
                    currentTool.setCalculateReferenceCurve(false);
                    toolRepository.save(currentTool);
                    calculations.remove(ipAddress);
                    log.info("Reference curve for tool with id {} was successfully calculated",currentTool.getId());
                }
            } else{
                if(calculations.remove(ipAddress) != null){
                    log.info("Canceling reference curve calculation for tool with id {}", currentTool.getId());
                }
            }
        } else {
            log.warn("During reference curve calculation for tool of PLC with IP address {}, the tool wasn't found in database", ipAddress);
        }
    }

    @Override
    public void onToolDataChange(PlcData plcData) {
        final String ipAddress = plcData.getIpAddress();

        if(calculations.containsKey(ipAddress)){
            log.warn("Tool changed during reference curve calculation so calculation of reference curve for tool of PLC with IP address {} was canceled.", ipAddress);
            calculations.remove(ipAddress);
        }
    }


}
