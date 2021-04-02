package com.siemens.metal_forming.connection;


import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.enumerated.ConnectionStatus;
import com.siemens.metal_forming.enumerated.ToolStatusType;
import com.siemens.metal_forming.service.AutomaticMonitoringService;
import com.siemens.metal_forming.service.AutomaticUpdateService;
import com.siemens.metal_forming.service.ReferenceCurveCalculationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component @Slf4j
public class PlcConnectorImpl implements PlcConnector {
    private final AutomaticUpdateService automaticUpdateService;
    private final AutomaticMonitoringService automaticMonitoringService;
    private final ReferenceCurveCalculationService referenceCurveCalculationService;
    private final PlcDataProvider plcDataProvider;
    private final HashMap<String, PlcData> plcDataMap = new HashMap<>();

    @Autowired
    public PlcConnectorImpl(AutomaticUpdateService automaticUpdateService, AutomaticMonitoringService automaticMonitoringService, ReferenceCurveCalculationService referenceCurveCalculationService, PlcDataProvider plcDataProvider) {
        this.automaticUpdateService = automaticUpdateService;
        this.automaticMonitoringService = automaticMonitoringService;
        this.referenceCurveCalculationService = referenceCurveCalculationService; //Correct implementation is selected based on application.properties
        this.plcDataProvider = plcDataProvider;
    }

    @Override
    public Plc connect(Plc plc){
        PlcData plcData = plcDataMap.get(plc.getIpAddress());

        if(plcData==null){
            plcData = plcDataProvider.getPlcData(plc.getIpAddress());
            plcDataMap.put(plc.getIpAddress(), plcData);
            registerForAutomaticPlcInformationUpdate(plcData);
            registerForCurveValidation(plcData);
            registerForReferenceCurveCalculation(plcData);
        } else {
            log.warn("OPC UA client for plc with IP address {} already exist, existing one is returned", plc.getIpAddress());
        }

        return updatePlcInformation(plc,plcData);
    }

    @Override
    public void disconnect(String ipAddress){
        PlcData oldPlcData = plcDataMap.get(ipAddress);

        if(oldPlcData!=null){
            oldPlcData.disconnect();
            plcDataMap.remove(ipAddress);
        } else {
            log.warn("OPC UA client for plc with IP address {} did not exist",ipAddress);
        }
    }

    @Override
    public Plc disconnect(Plc plc){
        disconnect(plc.getIpAddress());
        plc.markAsDisconnected();
        return plc;
    }

    private Plc updatePlcInformation(Plc plc, PlcData plcData) {
        if(plcData.getConnectionStatus() == ConnectionStatus.CONNECTED){
            plc.markAsConnected();
            plc.getHardwareInformation().setSerialNumber(plcData.getSerialNumber());
            plc.getHardwareInformation().setFirmwareNumber(plcData.getFirmwareNumber());
            plc.setMotorCurve(plcData.getMotorCurve());

            // set current tool
            Integer currentToolNumber = plcData.getToolData().getToolNumber();
            if(plc.getCurrentTool() == null){
                Tool newTool = Tool.builder()
                        .toolNumber(currentToolNumber)
                        .nameFromPlc(plcData.getToolData().getToolName())
                        //.maxSpeedOperation(plcData.getMaxOperationSpeed())
                        .toolStatus(ToolStatusType.AUTODETECTED)
                        .automaticMonitoring(false)
                        .calculateReferenceCurve(false)
                        .build();
                plc.addTool(newTool);
            }
            plc.setCurrentTool(currentToolNumber);
            log.debug("All information about plc were successfully read");

        } else {
            plc.markAsDisconnected();
        }

        return plc;
    }

    private void registerForAutomaticPlcInformationUpdate(PlcData plcData){
        plcData.registerConnectionStatusObserver(automaticUpdateService);
        plcData.registerFirmwareNumberObserver(automaticUpdateService);
        plcData.registerSerialNumberObserver(automaticUpdateService);
        plcData.registerToolDataObserver(automaticUpdateService);
    }

    private void registerForCurveValidation(PlcData plcData){
        plcData.registerMeasuredCurveObserver(automaticMonitoringService);
    }

    private void registerForReferenceCurveCalculation(PlcData plcData){
        plcData.registerMeasuredCurveObserver(referenceCurveCalculationService);
    }
}
