package com.siemens.metal_forming.opcua.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.springframework.boot.context.properties.NestedConfigurationProperty;


@Getter @Setter @FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class OpcuaConfiguration {
    String applicationName;
    int requestTimeout;
    String port;

    OpcuaNodeConfiguration plcSerialNumber;
    OpcuaNodeConfiguration plcFirmwareNumber;

    OpcuaNodeConfiguration toolName;
    OpcuaNodeConfiguration toolNumber;
    OpcuaNodeConfiguration toolMaxSpeedOperation;

    OpcuaNodeConfiguration immediateStopIndicator;
    OpcuaNodeConfiguration topPositionStopIndicator;

    OpcuaNodeConfiguration motorSpeedArray;
    OpcuaNodeConfiguration motorTorqueArray;
    OpcuaNodeConfiguration readCurveDataIndicator;


    @Setter @Getter
    public static class OpcuaNodeConfiguration{
        private int nameSpaceIndex;
        private String identifier;

        public NodeId getNode(){
            return new NodeId(nameSpaceIndex,identifier);
        }
    }



}
