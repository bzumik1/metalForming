package com.siemens.metal_forming.opcua.impl;

import com.siemens.metal_forming.opcua.OpcuaClient;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig;
import org.eclipse.milo.opcua.stack.client.UaStackClient;

import java.util.concurrent.CompletableFuture;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class OpcuaClientImpl extends OpcUaClient implements OpcuaClient {
    String ipAddress;


    public OpcuaClientImpl(OpcUaClientConfig config, UaStackClient stackClient, String ipAddress) {
        super(config, stackClient);
        this.ipAddress = ipAddress;
    }


    @Override
    public CompletableFuture<String> readSerialNumber() {
        return CompletableFuture.completedFuture("SN REAL PLC");
    }

    @Override
    public CompletableFuture<String> readFirmwareNumber() {
        return CompletableFuture.completedFuture("FW REAL PLC");
    }


}
