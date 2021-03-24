package com.siemens.metal_forming.connection.opcua.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;


@Getter @Setter @FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class OpcuaConfiguration {
    String applicationName;
    int requestTimeout;
    String port;

    OpcuaNodeConfiguration plcSerialNumber;
    OpcuaNodeConfiguration plcFirmwareNumber;

    OpcuaNodeConfiguration toolData;

    OpcuaNodeConfiguration immediateStopIndicator;
    OpcuaNodeConfiguration topPositionStopIndicator;

    OpcuaNodeConfiguration measuredCurve;
    OpcuaNodeConfiguration motorCurve;


    @Setter @Getter
    public static class OpcuaNodeConfiguration{
        private int nameSpaceIndex;
        private String identifier;

        public NodeId getNode(){
            return new NodeId(nameSpaceIndex,identifier);
        }
    }



}
