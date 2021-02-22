package com.siemens.metal_forming.service.impl;

import com.siemens.metal_forming.connection.PlcData;
import com.siemens.metal_forming.domain.Curve;
import com.siemens.metal_forming.dto.RestDtoMapper;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.domain.PointOfTorqueAndSpeed;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.entity.log.Log;
import com.siemens.metal_forming.entity.log.LogCreator;
import com.siemens.metal_forming.repository.LogRepository;
import com.siemens.metal_forming.repository.PlcRepository;
import com.siemens.metal_forming.service.AutomaticMonitoringService;
import com.siemens.metal_forming.service.CurveValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service("CurveValidationServiceImpl")
@Slf4j
public class AutomaticMonitoringServiceImpl implements AutomaticMonitoringService {
    private final PlcRepository plcRepository;
    private final LogRepository logRepository;
    private final LogCreator logCreator;
    private final CurveValidationService curveValidationService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final RestDtoMapper mapper;

    @Autowired
    public AutomaticMonitoringServiceImpl(PlcRepository plcRepository, LogRepository logRepository, LogCreator logCreator, CurveValidationService curveValidationService, SimpMessagingTemplate simpMessagingTemplate, RestDtoMapper mapper) {
        this.plcRepository = plcRepository;
        this.logRepository = logRepository;
        this.logCreator = logCreator;
        this.curveValidationService = curveValidationService;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.mapper = mapper;
    }


    @Transactional
    @Override
    public void onMeasuredCurveChange(PlcData plcData) {
        Optional<Plc> optionalPlcInDb = plcRepository.findByIpAddress(plcData.getIpAddress());
        if(optionalPlcInDb.isPresent()){
            Plc plcInDb = optionalPlcInDb.get();
            Tool currentTool = optionalPlcInDb.get().getCurrentTool();
            Curve measuredCurve = plcData.getMeasuredCurve();
            log.debug("Validating measured curve of PLC with id {} in database",plcInDb.getId());

            if(currentTool.getAutomaticMonitoring()){
                Set<PointOfTorqueAndSpeed> collisionPoints = curveValidationService.validate(currentTool.getTolerance(), currentTool.getReferenceCurve(),measuredCurve);

                if(!collisionPoints.isEmpty()){
                    //Creates log in database and sends it over WebSocket
                    Log log = logCreator.create(plcInDb, measuredCurve, collisionPoints);
                    logRepository.save(log);

                    //Sends new log over WebSocket
                    simpMessagingTemplate.convertAndSend("/topic/logs/new-log",mapper.toLogDtoOverview(log));

                    //Sends signal back to PLC
                    plcData.notifyPlcAboutCollision(currentTool.getStopReaction());
                }
            } else {
                log.debug("New curve wasn't validated because automatic monitoring for current tool with toolNumber {} is disabled",currentTool.getToolNumber());
            }
        } else {
            log.warn("Measured curve of PLC with IP address {} should be validated, but this PLC wasn't found in database", plcData.getIpAddress());
        }
    }
}
