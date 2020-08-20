package com.siemens.metal_forming.IntegrationTests;

import com.siemens.metal_forming.MetalFormingApplication;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.opcua.OpcuaClient;
import com.siemens.metal_forming.opcua.OpcuaConnector;
import com.siemens.metal_forming.service.PlcService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.concurrent.ExecutionException;

@SpringBootTest(classes = MetalFormingApplication.class)
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")

//@ActiveProfiles("test")
public class opcuaTest {
    @Autowired
    private OpcuaConnector opcuaConnector;

    @Autowired
    private PlcService plcService;

    @Test @Tag("IntegrationTest")
    void test() throws ExecutionException, InterruptedException {
        String ipAddress = "192.168.0.1";
        String ipAddress2 = "192.168.0.2";

        Plc plc = Plc.builder().ipAddress(ipAddress).build();
        Plc plc2 = Plc.builder().ipAddress(ipAddress2).build();

        Plc plcInDb = plcService.create(plc);
        Plc plcInDb2 = plcService.create(plc2);

        OpcuaClient opcuaClient = opcuaConnector.getClient(plc);
        System.out.println("\n\n\n\n");
        System.out.println("Serial number of PLC is: "+opcuaClient.readSerialNumber().handle((sn,ex) -> sn).get());
        System.out.println("Firmware number of PLC is: "+opcuaClient.readFirmwareNumber().get());
        System.out.println("\n\n\n\n");

        //Time to read values 10s
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("first 10 seconds are out");
        plcService.deleteById(plcInDb2.getId());



        //Time to read values 1000s
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
