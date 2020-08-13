package com.siemens.metal_forming.opcua.mock;

import com.siemens.metal_forming.opcua.OpcuaConfiguration;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration("OpcuaConfigurationMock")
@ConfigurationProperties(prefix = "mock-opcua")
@Getter @Setter @FieldDefaults(level = AccessLevel.PRIVATE)
public class OpcuaConfigurationMock extends OpcuaConfiguration {
    String server;
    NodeId serialNumberNode;
}
