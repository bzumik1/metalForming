package com.siemens.metal_forming.opcua.impl;

import com.siemens.metal_forming.opcua.OpcuaClient;
import com.siemens.metal_forming.opcua.abst.OpcuaClientProviderAbstract;
import com.siemens.metal_forming.opcua.configuration.OpcuaConfigurationImpl;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig;
import org.eclipse.milo.opcua.stack.client.UaStackClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component("OpcuaClientProviderImpl")
@ConditionalOnProperty(
        value="opcua.mock-opcua",
        havingValue = "false",
        matchIfMissing = true)
@Slf4j
public class OpcuaClientProviderImpl extends OpcuaClientProviderAbstract {

    public OpcuaClientProviderImpl(@Autowired OpcuaConfigurationImpl opcuaConfiguration) {
        super();
        super.setOpcuaConfiguration(opcuaConfiguration);
    }

    @Override
    public String createUrl(String ipAddress) {
        return "opc.tcp://"+ipAddress+":"+ super.getOpcuaConfiguration().getPort();
    }

    @Override
    public OpcuaClient createNewClient(OpcUaClientConfig config, UaStackClient stackClient, String ipAddress) {
        return new OpcuaClientImpl(config,stackClient,ipAddress);
    }
}
