package com.siemens.metal_forming.opcua.configuration;

import com.siemens.metal_forming.opcua.configuration.OpcuaConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration("OpcuaConfigurationImpl")
@ConfigurationProperties(prefix = "opcua")
@PropertySource("classpath:opcua-production.properties")
public class OpcuaConfigurationImpl extends OpcuaConfiguration {
}
