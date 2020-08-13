package com.siemens.metal_forming.opcua.impl;

import com.siemens.metal_forming.opcua.OpcuaClient;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig;
import org.eclipse.milo.opcua.stack.client.UaStackClient;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class OpcuaClientImpl extends OpcUaClient implements OpcuaClient {
    String ipAddress;


    public OpcuaClientImpl(OpcUaClientConfig config, UaStackClient stackClient, String ipAddress) {
        super(config, stackClient);
        this.ipAddress = ipAddress;
    }


    @Override
    public String readSerialNumber() {
        return null;
    }
}
