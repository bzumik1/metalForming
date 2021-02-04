package com.siemens.metal_forming.connection;


import com.siemens.metal_forming.entity.Plc;

public interface PlcConnector {
    Plc connect(Plc plc);
    Plc disconnect(Plc plc);
    void disconnect(String ipAddress);

}
