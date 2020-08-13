package com.siemens.metal_forming.opcua.impl;

import com.siemens.metal_forming.opcua.OpcuaConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration("OpcuaConfigurationImpl")
@ConfigurationProperties(prefix = "opcua")
public class OpcuaConfigurationImpl extends OpcuaConfiguration {
}
