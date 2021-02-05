package com.siemens.metal_forming.connection.opcua;

import com.siemens.metal_forming.connection.PlcData;
import com.siemens.metal_forming.connection.opcua.configuration.OpcuaConfigurationImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@DisplayName("<= PLC DATA OPC UA SPECIFICATION =>")
@SpringBootTest
public class PlcDataOpcuaSpec {
    @Autowired
    OpcuaConfigurationImpl configuration;


}
