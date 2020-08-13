package com.siemens.metal_forming.opcua;

public interface OpcuaClientProvider {
    OpcuaClient createClient(String ipAddress);
}
