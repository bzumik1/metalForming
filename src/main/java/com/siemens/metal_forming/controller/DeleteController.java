package com.siemens.metal_forming.controller;

import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.opcua.OpcuaClient;
import com.siemens.metal_forming.opcua.OpcuaConnector;
import com.siemens.metal_forming.service.PlcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class DeleteController {
    @Autowired
    private OpcuaConnector opcuaConnector;

    @Autowired
    private PlcService plcService;

    @GetMapping(path = "/test")
    public void test(){
        String ipAddress = "192.168.0.1";

        Plc plc = Plc.builder().ipAddress(ipAddress).build();
        Plc oldPlc = plcService.create(plc);
        OpcuaClient opcuaClient = opcuaConnector.getClient(plc);
        //System.out.println("Serial number of PLC is: "+opcuaClient.readSerialNumber());
        Optional<Plc> plcInDb = plcService.findById(oldPlc.getId());

        //Time to read values 10s
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("first 10 seconds are out");

        //System.out.println("Firmware number of PLC is: "+opcuaClient.readFirmwareNumber());

        //Time to read values 1000s
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
