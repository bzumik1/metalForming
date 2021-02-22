package com.siemens.metal_forming.service.impl;

import com.siemens.metal_forming.connection.PlcData;
import com.siemens.metal_forming.dto.WebSocketDtoMapper;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.enumerated.ToolStatusType;
import com.siemens.metal_forming.repository.PlcRepository;
import com.siemens.metal_forming.service.PlcAutomaticUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service @Slf4j
public class PlcAutomaticUpdateServiceImpl implements PlcAutomaticUpdateService {
    private final PlcRepository plcRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final WebSocketDtoMapper mapper;

    @Autowired
    public PlcAutomaticUpdateServiceImpl(PlcRepository plcRepository, SimpMessagingTemplate simpMessagingTemplate, WebSocketDtoMapper mapper) {
        this.plcRepository = plcRepository;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.mapper = mapper;
    }

    @Override
    public void onFirmwareNumberChange(PlcData plcData) {
        Optional<Plc> optionalPlcInDb = plcRepository.findByIpAddress(plcData.getIpAddress());
        if(optionalPlcInDb.isPresent()){
            Plc plcInDb = optionalPlcInDb.get();
            log.info("Updating firmware number of PLC with id {} in database",plcInDb.getId());
            plcInDb.getHardwareInformation().setFirmwareNumber(plcData.getFirmwareNumber());
            plcRepository.save(plcInDb);
        } else {
            log.error("Firmware number of PLC with IP address {} should be updated, but this PLC wasn't found in database", plcData.getIpAddress());
        }
    }

    @Override
    public void onSerialNumberChange(PlcData plcData) {
        Optional<Plc> optionalPlcInDb = plcRepository.findByIpAddress(plcData.getIpAddress());
        if(optionalPlcInDb.isPresent()){
            Plc plcInDb = optionalPlcInDb.get();
            log.info("Updating serial number of PLC with id {} in database",plcInDb.getId());
            plcInDb.getHardwareInformation().setSerialNumber(plcData.getSerialNumber());
            plcRepository.save(plcInDb);
        } else {
            log.error("Serial number of PLC with IP address {} should be updated, but this PLC wasn't found in database", plcData.getIpAddress());
        }
    }

    @Override
    public void onToolNameChange(PlcData plcData) {

    }

    @Transactional
    @Override
    public void onToolNumberChange(PlcData plcData) {
        Optional<Plc> optionalPlcInDb = plcRepository.findByIpAddressFetchTools(plcData.getIpAddress());
        if(optionalPlcInDb.isPresent()){
            Plc plcInDb = optionalPlcInDb.get();
            log.info("Updating current tool of PLC with id {} in database",plcInDb.getId());

            if(plcInDb.hasToolByToolNumber(plcData.getToolNumber())){
                plcInDb.setCurrentTool(plcData.getToolNumber());
                log.debug("Setting current tool: {}", plcInDb.getCurrentTool());
            } else {
                Tool autodetectedTool = Tool.builder()
                        .toolNumber(plcData.getToolNumber())
                        .nameFromPlc(plcData.getToolName())
                        .maxSpeedOperation(plcData.getMaxOperationSpeed())
                        .toolStatus(ToolStatusType.AUTODETECTED)
                        .automaticMonitoring(false)
                        .calculateReferenceCurve(false)
                        .build();
                log.debug("Setting autodetected tool as current tool: {}", autodetectedTool);
                plcInDb.addTool(autodetectedTool);
                plcInDb.setCurrentTool(plcData.getToolNumber());

                //Send information over WebSocket
                log.debug("Sending new tool with number \"{}\" for PLC with IP {} over WebSocket",plcData.getToolNumber(), plcData.getIpAddress());
                simpMessagingTemplate.convertAndSend("/topic/plcs/new-tool", mapper.toPlcDtoNewTool(plcInDb));
            }

            plcRepository.save(plcInDb);

            //Send information over WebSocket
            log.debug("Sending current tool number \"{}\" for PLC with IP {} over WebSocket",plcData.getToolNumber(), plcData.getIpAddress());
            simpMessagingTemplate.convertAndSend("/topic/plcs/current-tool", mapper.toPlcDtoCurrentTool(plcInDb));
        } else {
            log.error("Current tool of PLC with IP address {} should be updated, but this PLC wasn't found in database", plcData.getIpAddress());
        }
    }

    @Override
    public void onConnectionStatusChange(PlcData plcData) {
        Optional<Plc> optionalPlcInDb = plcRepository.findByIpAddress(plcData.getIpAddress());
        if(optionalPlcInDb.isPresent()){
            Plc plcInDb = optionalPlcInDb.get();
            log.info("Updating connection status of PLC with id {} in database",plcInDb.getId());
            plcInDb.setConnectionStatus(plcData.getConnectionStatus());
            plcRepository.save(plcInDb);

            //Send information over WebSocket
            log.debug("Sending updated connection status \"{}\" for PLC with IP {} over WebSocket",plcData.getConnectionStatus(), plcData.getIpAddress());
            simpMessagingTemplate.convertAndSend("/topic/plcs/connection-status", mapper.toPlcDtoConnection(plcInDb));

        } else {
            log.error("Connection status of PLC with IP address {} should be updated, but this PLC wasn't found in database", plcData.getIpAddress());
        }
    }

    @Override
    public void onMotorCurveChange(PlcData plcData) {
        Optional<Plc> optionalPlcInDb = plcRepository.findByIpAddress(plcData.getIpAddress());
        if(optionalPlcInDb.isPresent()){
            Plc plcInDb = optionalPlcInDb.get();
            log.info("Updating motor curve of PLC with id {} in database",plcInDb.getId());
            plcInDb.setMotorCurve(plcData.getMotorCurve());
            plcRepository.save(plcInDb);
        } else {
            log.error("Connection status of PLC with IP address {} should be updated, but this PLC wasn't found in database", plcData.getIpAddress());
        }
    }
}
