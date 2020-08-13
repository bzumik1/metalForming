package com.siemens.metal_forming.opcuaMock;

import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.opcua.OpcuaClient;
import com.siemens.metal_forming.opcua.OpcuaConnector;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class opcuaTest {
    @Autowired
    private OpcuaConnector opcuaConnector;

    @Test
    void test(){
        OpcuaClient opcuaClient = opcuaConnector.getClient(new Plc());
        System.out.println();
    }

}
