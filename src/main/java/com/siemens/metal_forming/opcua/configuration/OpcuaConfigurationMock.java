package com.siemens.metal_forming.opcua.configuration;

import com.siemens.metal_forming.opcua.configuration.OpcuaConfiguration;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration("OpcuaConfigurationMock")
@ConfigurationProperties(prefix = "opcua")
@PropertySource("classpath:opcua-mock.properties")
@Getter @Setter @FieldDefaults(level = AccessLevel.PRIVATE)
public class OpcuaConfigurationMock extends OpcuaConfiguration {
    String server;
}
