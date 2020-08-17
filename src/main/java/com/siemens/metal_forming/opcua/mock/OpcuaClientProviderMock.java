package com.siemens.metal_forming.opcua.mock;


import com.siemens.metal_forming.opcua.OpcuaClient;
import com.siemens.metal_forming.opcua.abst.OpcuaClientProviderAbstract;
import com.siemens.metal_forming.opcua.configuration.OpcuaConfigurationMock;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig;
import org.eclipse.milo.opcua.stack.client.UaStackClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component("OpcuaClientProviderMock")
@ConditionalOnProperty(
        value="opcua.mock-opcua",
        havingValue = "true")
@Slf4j
public class OpcuaClientProviderMock extends OpcuaClientProviderAbstract {

    public OpcuaClientProviderMock(@Autowired OpcuaConfigurationMock opcuaConfiguration) {
        super();
        super.setOpcuaConfiguration(opcuaConfiguration);
    }

    @Override
    public String createUrl(String ipAddress) {
        return ((OpcuaConfigurationMock)super.getOpcuaConfiguration()).getServer();
    }

    @Override
    public OpcuaClient createNewClient(OpcUaClientConfig config, UaStackClient stackClient, String ipAddress) {
        return new OpcuaClientMock(config,stackClient,ipAddress);
    }
}
