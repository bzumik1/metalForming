package com.siemens.metal_forming.opcua.impl;

import com.siemens.metal_forming.opcua.OpcuaClient;
import com.siemens.metal_forming.opcua.OpcuaConfiguration;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig;
import org.eclipse.milo.opcua.stack.client.UaStackClient;
import org.springframework.beans.factory.annotation.Autowired;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class OpcuaClientImpl extends OpcUaClient implements OpcuaClient {
    String ipAddress;


    public OpcuaClientImpl(OpcUaClientConfig config, UaStackClient stackClient, String ipAddress) {
        super(config, stackClient);
        this.ipAddress = ipAddress;
    }


}
