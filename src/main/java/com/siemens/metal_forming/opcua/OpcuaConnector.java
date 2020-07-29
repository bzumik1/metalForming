package com.siemens.metal_forming.opcua;

import com.siemens.metal_forming.entity.Plc;

public interface OpcuaConnector {
    OpcuaClient connectPlc(Plc plc);
    void disconnectPlc(Plc plc);
    OpcuaClient getClient(Plc plc);
    boolean isEmpty();
}
