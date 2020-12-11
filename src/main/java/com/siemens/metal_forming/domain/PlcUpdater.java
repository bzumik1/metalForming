//package com.siemens.metal_forming.domain;
//
//import com.siemens.metal_forming.connection.PlcData;
//import com.siemens.metal_forming.connection.observer.*;
//import com.siemens.metal_forming.entity.Plc;
//import com.siemens.metal_forming.entity.Tool;
//import com.siemens.metal_forming.enumerated.ConnectionStatus;
//import com.siemens.metal_forming.enumerated.ToolStatusType;
//import com.siemens.metal_forming.repository.PlcRepository;
//import lombok.AccessLevel;
//import lombok.experimental.FieldDefaults;
//import lombok.extern.slf4j.Slf4j;
//import org.hibernate.engine.jdbc.spi.ConnectionObserver;
//
//import javax.transaction.Transactional;
//import java.sql.Connection;
//import java.util.Optional;
//
//@Slf4j
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//public class PlcUpdater implements ConnectionStatusObserver, FirmwareNumberObserver, SerialNumberObserver, ToolNameObserver, ToolNumberObserver {
//    PlcRepository plcRepository;
//    PlcData plcData;
//
//    public PlcUpdater(PlcRepository plcRepository, PlcData plcData){
//        this.plcRepository = plcRepository;
//        this.plcData = plcData;
//        plcData.registerConnectionStatusObserver(this);
//        plcData.registerFirmwareNumberObserver(this);
//        plcData.registerSerialNumberObserver(this);
//        plcData.registerToolNameObserver(this);
//        plcData.registerToolNumberObserver(this);
//    }
//
//    @Override
//    public void onFirmwareNumberChange(String firmwareNumber) {
//        Optional<Plc> optionalPlcInDb = plcRepository.findByIpAddress(plcData.getIpAddress());
//        if(optionalPlcInDb.isPresent()){
//            Plc plcInDb = optionalPlcInDb.get();
//            log.info("Updating firmware number of PLC with id {} in database",plcInDb.getId());
//            plcInDb.getHardwareInformation().setFirmwareNumber(plcData.getFirmwareNumber());
//            plcRepository.save(plcInDb);
//        } else {
//            log.warn("Firmware number of PLC with IP address {} should be updated, but this PLC wasn't found in database", plcData.getIpAddress());
//        }
//    }
//
//    @Override
//    public void onSerialNumberChange(String serialNumber) {
//        Optional<Plc> optionalPlcInDb = plcRepository.findByIpAddress(plcData.getIpAddress());
//        if(optionalPlcInDb.isPresent()){
//            Plc plcInDb = optionalPlcInDb.get();
//            log.info("Updating serial number of PLC with id {} in database",plcInDb.getId());
//            plcInDb.getHardwareInformation().setSerialNumber(plcData.getSerialNumber());
//            plcRepository.save(plcInDb);
//        } else {
//            log.warn("Serial number of PLC with IP address {} should be updated, but this PLC wasn't found in database", plcData.getIpAddress());
//        }
//    }
//
//    @Override
//    public void onToolNameChange(String serialNumber) {
//
//    }
//
//    @Transactional
//    @Override
//    public void onToolNumberChange(int toolNumber) {
//        Optional<Plc> optionalPlcInDb = plcRepository.findByIpAddressFetchTools(plcData.getIpAddress());
//        if(optionalPlcInDb.isPresent()){
//            Plc plcInDb = optionalPlcInDb.get();
//            log.info("Updating current tool of PLC with id {} in database",plcInDb.getId());
//
//            if(plcInDb.hasToolByToolNumber(toolNumber)){
//                plcInDb.setCurrentTool(toolNumber);
//                log.debug("Setting current tool: {}", plcInDb.getCurrentTool());
//            } else {
//                Tool autodetectedTool = Tool.builder()
//                        .toolNumber(plcData.getToolNumber())
//                        .nameFromPlc(plcData.getToolName())
//                        .maxSpeedOperation(plcData.getMaxOperationSpeed())
//                        .toolStatus(ToolStatusType.AUTODETECTED)
//                        .automaticMonitoring(false)
//                        .calculateReferenceCurve(false)
//                        .build();
//                log.debug("Setting autodetected tool as current tool: {}", autodetectedTool);
//                plcInDb.addTool(autodetectedTool);
//                plcInDb.setCurrentTool(toolNumber);
//            }
//
//            plcRepository.save(plcInDb);
//        } else {
//            log.warn("Current tool of PLC with IP address {} should be updated, but this PLC wasn't found in database", plcData.getIpAddress());
//        }
//    }
//
//    @Override
//    public void onConnectionStatusChange(ConnectionStatus connectionStatus) {
//        Optional<Plc> optionalPlcInDb = plcRepository.findByIpAddress(plcData.getIpAddress());
//        if(optionalPlcInDb.isPresent()){
//            Plc plcInDb = optionalPlcInDb.get();
//            log.info("Updating connection status of PLC with id {} in database",plcInDb.getId());
//            plcInDb.setConnectionStatus(plcData.getConnectionStatus());
//            plcRepository.save(plcInDb);
//        } else {
//            log.warn("Connection status of PLC with IP address {} should be updated, but this PLC wasn't found in database", plcData.getIpAddress());
//        }
//    }
//}
