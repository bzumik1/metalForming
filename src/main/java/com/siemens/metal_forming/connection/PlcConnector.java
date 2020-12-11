package com.siemens.metal_forming.connection;


public interface PlcConnector {
    PlcData connectPlc(String ipAddress);
    void disconnectPlc(String ipAddress);
    PlcData getPlcData(String ipAddress);
}
