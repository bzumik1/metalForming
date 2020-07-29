package com.siemens.metal_forming.opcua;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "opcua")
@Getter @FieldDefaults(level = AccessLevel.PRIVATE)
public class OpcuaConfiguration {
    String applicationName;
    int requestTimeout;
    String port;
}
