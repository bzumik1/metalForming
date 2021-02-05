package com.siemens.metal_forming.connection;

import com.siemens.metal_forming.connection.opcua.PlcDataOpcua;
import com.siemens.metal_forming.connection.opcua.configuration.OpcuaConfiguration;
import com.siemens.metal_forming.connection.opcua.configuration.OpcuaConfigurationImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component("PlcDataProviderImpl")
@ConditionalOnProperty(
        value="connection.mock",
        havingValue = "false",
        matchIfMissing = true)
@Slf4j
public class PlcDataProviderImpl implements PlcDataProvider {
    OpcuaConfiguration opcuaConfiguration;

    public PlcDataProviderImpl(@Autowired OpcuaConfigurationImpl opcuaConfiguration) {
        this.opcuaConfiguration = opcuaConfiguration;
    }

    @Override
    public PlcData getPlcData(String ipAddress) {
        return new PlcDataOpcua(opcuaConfiguration, ipAddress);
    }
}
