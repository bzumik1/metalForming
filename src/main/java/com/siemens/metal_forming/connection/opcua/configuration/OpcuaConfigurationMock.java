package com.siemens.metal_forming.connection.opcua.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration("OpcuaConfigurationMock")
@ConfigurationProperties(prefix = "mock-opcua")
@PropertySource("classpath:opcua-mock.properties")
@Getter @Setter @FieldDefaults(level = AccessLevel.PRIVATE)
public class OpcuaConfigurationMock extends OpcuaConfiguration {
    String server;
}
