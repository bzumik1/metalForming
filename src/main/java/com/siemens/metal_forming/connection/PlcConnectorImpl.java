package com.siemens.metal_forming.connection;



import com.siemens.metal_forming.service.CurveValidationService;
import com.siemens.metal_forming.service.impl.PlcAutomaticUpdateServiceImpl;
import com.siemens.metal_forming.service.ReferenceCurveCalculationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;

@Component @Slf4j
public class PlcConnectorImpl implements PlcConnector {
    @Autowired
    private PlcAutomaticUpdateServiceImpl plcUpdateService;
    @Autowired
    private CurveValidationService curveValidationService;
    @Autowired
    private ReferenceCurveCalculationService referenceCurveCalculationService;
    private final PlcDataProvider plcDataProvider;
    private final HashMap<String, PlcData> plcDataMap = new HashMap<>();

    /** @noinspection SpringJavaInjectionPointsAutowiringInspection*/
    public PlcConnectorImpl(@Autowired PlcDataProvider plcDataProvider) { //Correct implementation is selected based on application.properties
        this.plcDataProvider = plcDataProvider;
    }

    @Override
    public PlcData connectPlc(String ipAddress){
        Optional<PlcData> oldPlcData = Optional.ofNullable(plcDataMap.get(ipAddress));

        if(oldPlcData.isEmpty()){
            PlcData plcData = plcDataProvider.getPlcData(ipAddress);
            plcDataMap.put(ipAddress, plcData);
            registerForAutomaticPlcInformationUpdate(plcData);
            registerForCurveValidation(plcData);
            registerForReferenceCurveCalculation(plcData);
            return plcData;
        } else {
            log.warn("OPC UA client for plc with IP address {} already exist, existing one is returned", ipAddress);
            return oldPlcData.get();
        }
    }

    @Override
    public void disconnectPlc(String ipAddress){
        Optional<PlcData> oldPlcData = Optional.ofNullable(plcDataMap.get(ipAddress));

        if(oldPlcData.isPresent()){
            oldPlcData.get().disconnect();
            plcDataMap.remove(ipAddress);
        } else {
            log.warn("OPC UA client for plc with IP address {} did not exist",ipAddress);
        }
    }

    @Override
    public PlcData getPlcData(String ipAddress) {
        Optional<PlcData> opcuaClient = Optional.ofNullable(plcDataMap.get(ipAddress));
        return opcuaClient.orElseGet(() -> connectPlc(ipAddress));
    }

    private void registerForAutomaticPlcInformationUpdate(PlcData plcData){
        plcData.registerConnectionStatusObserver(plcUpdateService);
        plcData.registerFirmwareNumberObserver(plcUpdateService);
        plcData.registerSerialNumberObserver(plcUpdateService);
        plcData.registerToolNameObserver(plcUpdateService);
        plcData.registerToolNumberObserver(plcUpdateService);
    }

    private void registerForCurveValidation(PlcData plcData){
        plcData.registerMeasuredCurveObserver(curveValidationService);
    }

    private void registerForReferenceCurveCalculation(PlcData plcData){
        plcData.registerMeasuredCurveObserver(referenceCurveCalculationService);
    }
}
