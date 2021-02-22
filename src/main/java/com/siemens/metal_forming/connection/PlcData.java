package com.siemens.metal_forming.connection;

import com.siemens.metal_forming.connection.observer.*;
import com.siemens.metal_forming.domain.Curve;
import com.siemens.metal_forming.enumerated.ConnectionStatus;
import com.siemens.metal_forming.enumerated.StopReactionType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Setter @FieldDefaults(level = AccessLevel.PRIVATE) @SuperBuilder @Slf4j
public abstract class PlcData implements SerialNumberSource, FirmwareNumberSource, ToolNameSource, ToolNumberSource, MeasuredCurveSource, MotorCurveSource, ConnectionStatusSource {
    @Getter
    protected final String ipAddress;
    @Getter
    String serialNumber;
    @Getter
    String firmwareNumber;
    @Getter
    String toolName;
    @Getter
    Integer toolNumber;
    @Getter
    Curve measuredCurve;
    @Getter
    Curve motorCurve;
    @Getter
    ConnectionStatus connectionStatus;

    @Getter
    int MaxOperationSpeed;

    final List<SerialNumberObserver> serialNumberObservers = new ArrayList<>();
    final List<FirmwareNumberObserver> firmwareNumberObservers = new ArrayList<>();
    final List<ToolNameObserver> toolNameObservers = new ArrayList<>();
    final List<ToolNumberObserver> toolNumberObservers = new ArrayList<>();
    final List<MeasuredCurveObserver> measuredCurveObservers = new ArrayList<>();
    final List<MotorCurveObserver> motorCurveObservers = new ArrayList<>();
    final List<ConnectionStatusObserver> connectionStatusObservers = new ArrayList<>();

    protected PlcData(String ipAddress) {
        this.ipAddress = ipAddress;
    }


    public void setSerialNumber(String serialNumber) {
        if(serialNumber != null && !serialNumber.equals(this.serialNumber)){
            log.debug("Serial number of PLC with IP address {} has changed from \"{}\" to \"{}\"",ipAddress, this.serialNumber, serialNumber);
            this.serialNumber = serialNumber;
            notifySerialNumberObservers();
        }
    }

    public void setFirmwareNumber(String firmwareNumber) {
        if(firmwareNumber != null && !firmwareNumber.equals(this.firmwareNumber)){
            log.debug("Firmware number of PLC with IP address {} has changed from \"{}\" to \"{}\"",ipAddress, this.firmwareNumber, firmwareNumber);
            this.firmwareNumber = firmwareNumber;
            notifyFirmwareNumberObservers();
        }
    }

    public void setToolName(String toolName) {
        if(toolName != null && !toolName.equals(this.toolName)){
            log.debug("Tool name of PLC with IP address {} has changed from \"{}\" to \"{}\"",ipAddress,this.toolName, toolName);
            this.toolName = toolName;
            notifyToolNameObservers();
        }
    }

    public void setToolNumber(Integer toolNumber) {
        if(toolNumber != null && !toolNumber.equals(this.toolNumber)){
            log.debug("Tool number of PLC with IP address {} has changed from \"{}\" to \"{}\"",ipAddress, this.toolNumber, toolNumber);
            this.toolNumber = toolNumber;
            notifyToolNumberObservers();
        }
    }

    public void setMeasuredCurve(Curve measuredCurve) {
        if(measuredCurve != null && !measuredCurve.equals(this.measuredCurve)){
            log.debug("Measured curve of PLC with IP address {} has changed to:\n\tTorque:{}\n\tSpeed: {}", ipAddress, measuredCurve.getTorqueValues(), measuredCurve.getSpeedValues());
            this.measuredCurve = measuredCurve;
            notifyMeasuredCurveObservers();
        }
    }

    public void setMotorCurve(Curve motorCurveCurve) {
        if(motorCurveCurve != null && !motorCurveCurve.equals(this.motorCurve)){
            log.debug("Motor curve of PLC with IP address {} has changed to:\n\tTorque:{}\n\tSpeed: {}", ipAddress, motorCurveCurve.getTorqueValues(), motorCurveCurve.getSpeedValues());
            this.motorCurve = motorCurveCurve;
            notifyMotorCurveObservers();
        }
    }

    public void setConnectionStatus(ConnectionStatus connectionStatus) {
        if(connectionStatus != null && !connectionStatus.equals(this.connectionStatus)){
            log.debug("Connection status of PLC with IP address {} has changed from \"{}\" to \"{}\"", ipAddress, this.connectionStatus, connectionStatus);
            this.connectionStatus = connectionStatus;
            notifyConnectionStatusObservers();
        }
    }

    @Override
    public void registerFirmwareNumberObserver(FirmwareNumberObserver firmwareNumberObserver) {
        firmwareNumberObservers.add(firmwareNumberObserver);
    }

    @Override
    public void removeFirmwareNumberObserver(FirmwareNumberObserver firmwareNumberObserver) {
        firmwareNumberObservers.remove(firmwareNumberObserver);
    }

    @Override
    public void notifyFirmwareNumberObservers() {
        for(FirmwareNumberObserver observer : firmwareNumberObservers){
            observer.onFirmwareNumberChange(this);
        }
    }

    @Override
    public void registerMeasuredCurveObserver(MeasuredCurveObserver measuredCurveObserver) {
        measuredCurveObservers.add(measuredCurveObserver);
    }

    @Override
    public void removeMeasuredCurveObserver(MeasuredCurveObserver measuredCurveObserver) {
        measuredCurveObservers.remove(measuredCurveObserver);
    }

    @Override
    public void notifyMeasuredCurveObservers() {
        for(MeasuredCurveObserver observer : measuredCurveObservers){
            observer.onMeasuredCurveChange(this);
        }
    }

    @Override
    public void registerMotorCurveObserver(MotorCurveObserver motorCurveObserver) {
        motorCurveObservers.add(motorCurveObserver);
    }

    @Override
    public void removeMotorCurveObserver(MotorCurveObserver motorCurveObserver) {
        motorCurveObservers.remove(motorCurveObserver);
    }

    @Override
    public void notifyMotorCurveObservers() {
        for(MotorCurveObserver observer : motorCurveObservers){
            observer.onMotorCurveChange(this);
        }
    }

    @Override
    public void registerSerialNumberObserver(SerialNumberObserver serialNumberObserver) {
        serialNumberObservers.add(serialNumberObserver);
    }

    @Override
    public void removeSerialNumberObserver(SerialNumberObserver serialNumberObserver) {
        serialNumberObservers.remove(serialNumberObserver);
    }

    @Override
    public void notifySerialNumberObservers() {
        for(SerialNumberObserver observer: serialNumberObservers){
            observer.onSerialNumberChange(this);
        }
    }

    @Override
    public void registerToolNameObserver(ToolNameObserver toolNameObserver) {
        toolNameObservers.add(toolNameObserver);
    }

    @Override
    public void removeToolNameObserver(ToolNameObserver toolNameObserver) {
        toolNameObservers.remove(toolNameObserver);
    }

    @Override
    public void notifyToolNameObservers() {
        for(ToolNameObserver observer:toolNameObservers){
            observer.onToolNameChange(this);
        }
    }

    @Override
    public void registerToolNumberObserver(ToolNumberObserver toolNumberObserver) {
        toolNumberObservers.add(toolNumberObserver);
    }

    @Override
    public void removeToolNumberObserver(ToolNumberObserver toolNumberObserver) {
        toolNumberObservers.remove(toolNumberObserver);
    }

    @Override
    public void notifyToolNumberObservers() {
        for(ToolNumberObserver observer: toolNumberObservers){
            observer.onToolNumberChange(this);
        }
    }

    @Override
    public void registerConnectionStatusObserver(ConnectionStatusObserver connectionStatusObserver) {
        connectionStatusObservers.add(connectionStatusObserver);
    }

    @Override
    public void removeConnectionStatusObserver(ConnectionStatusObserver connectionStatusObserver) {
        connectionStatusObservers.remove(connectionStatusObserver);
    }

    @Override
    public void notifyConnectionStatusObservers() {
        for(ConnectionStatusObserver observer: connectionStatusObservers){
            observer.onConnectionStatusChange(this);
        }
    }

    protected void removeAllObservers(){
        serialNumberObservers.clear();
        firmwareNumberObservers.clear();
        toolNameObservers.clear();
        toolNumberObservers.clear();
        measuredCurveObservers.clear();
        motorCurveObservers.clear();
        connectionStatusObservers.clear();
    }


    public abstract void notifyPlcAboutCollision(StopReactionType stopReaction);
    public abstract void disconnect();
}
