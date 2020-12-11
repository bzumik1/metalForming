package com.siemens.metal_forming.connection;

import com.siemens.metal_forming.connection.observer.*;
import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.enumerated.ConnectionStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Setter @FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class PlcData implements SerialNumberSource, FirmwareNumberSource, ToolNameSource, ToolNumberSource, MeasuredCurveSource, ConnectionStatusSource {
    @Getter
    protected final String ipAddress;
    @Getter
    String serialNumber;
    @Getter
    String firmwareNumber;
    @Getter
    String toolName;
    @Getter
    int toolNumber;
    @Getter
    Curve measuredCurve;
    //@Getter
    //Curve motorCurve;
    @Getter
    ConnectionStatus connectionStatus;

    @Getter
    int MaxOperationSpeed;

    final List<SerialNumberObserver> serialNumberObservers = new ArrayList<>();
    final List<FirmwareNumberObserver> firmwareNumberObservers = new ArrayList<>();
    final List<ToolNameObserver> toolNameObservers = new ArrayList<>();
    final List<ToolNumberObserver> toolNumberObservers = new ArrayList<>();
    final List<MeasuredCurveObserver> measuredCurveObservers = new ArrayList<>();
    final List<ConnectionStatusObserver> connectionStatusObservers = new ArrayList<>();

    protected PlcData(String ipAddress) {
        this.ipAddress = ipAddress;
    }


    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        notifySerialNumberObservers();
    }

    public void setFirmwareNumber(String firmwareNumber) {
        this.firmwareNumber = firmwareNumber;
        notifyFirmwareNumberObservers();
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
        notifyToolNameObservers();
    }

    public void setToolNumber(int toolNumber) {
        this.toolNumber = toolNumber;
        notifyToolNumberObservers();
    }

    public void setMeasuredCurve(Curve measuredCurve) {
        this.measuredCurve = measuredCurve;
        notifyMeasuredCurveObservers();
    }

    public void setConnectionStatus(ConnectionStatus connectionStatus) {
        this.connectionStatus = connectionStatus;
        notifyConnectionStatusObservers();
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


    public abstract void immediateStop();
    public abstract void topPositionStop();
    public abstract void disconnect();
}
