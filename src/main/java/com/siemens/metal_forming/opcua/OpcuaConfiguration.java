package com.siemens.metal_forming.opcua;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Getter @Setter @FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class OpcuaConfiguration {
    String applicationName;
    int requestTimeout;
    String port;
}
