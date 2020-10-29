package com.siemens.metal_forming.testBuilders;

import com.siemens.metal_forming.entity.*;
import com.siemens.metal_forming.enumerated.ConnectionStatus;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestPlcBuilder{
    Long id;
    String name = "plcName";
    String ipAddress = "192.168.0.1";
    HardwareInformation hardwareInformation = HardwareInformation.builder().serialNumber("SN 001").firmwareNumber("FW 001").build();
    Connection connection = new Connection();
    Curve motorCurve = new TestCurveBuilder().randomPoints(100).build();
    Set<Tool> tools  = new HashSet<>();
    Tool currentTool ;



    public TestPlcBuilder id(Long id){
        this.id = id;
        return this;
    }

    public TestPlcBuilder name(String name){
        this.name = name;
        return this;
    }

    public TestPlcBuilder ipAddress(String ipAddress){
        this.ipAddress = ipAddress;
        return this;
    }

    public TestPlcBuilder hardwareInformation(HardwareInformation hardwareInformation){
        this.hardwareInformation = hardwareInformation;
        return this;
    }

    public TestPlcBuilder serialNumber(String serialNumber){
        hardwareInformation.setSerialNumber(serialNumber);
        return this;
    }

    public TestPlcBuilder firmwareNumber(String firmwareNumber){
        hardwareInformation.setFirmwareNumber(firmwareNumber);
        return this;
    }

    public TestPlcBuilder connection(Connection connection){
        this.connection = connection;
        return this;
    }

    public TestPlcBuilder connectedOn(Timestamp timestamp){
        this.connection = new TestConnectionBuilder().status(ConnectionStatus.CONNECTED).lastStatusChange(timestamp).build();
        return this;
    }

    public TestPlcBuilder disconnectedOn(Timestamp timestamp){
        this.connection = new TestConnectionBuilder().status(ConnectionStatus.DISCONNECTED).lastStatusChange(timestamp).build();
        return this;
    }

    public TestPlcBuilder motorCurve(Curve motorCurve){
        this.motorCurve = motorCurve;
        return this;
    }

    public TestPlcBuilder currentTool(Tool currentTool){
        tools.add(currentTool);
        this.currentTool = currentTool;
        return this;
    }

    public TestPlcBuilder tools(Set<Tool> tools){
        this.tools = tools;
        return this;
    }

    public TestPlcBuilder addTool(Tool tool){
        if(tools == null){
            tools = new HashSet<>();
        }
        tools.add(tool);

        return this;
    }

    public TestPlcBuilder randomTools(int numberOfRandomTools){
        tools = Stream.iterate(1, n ->n+1).map(i -> new TestToolBuilder().toolNumber(i).build()).limit(numberOfRandomTools).collect(Collectors.toSet());
        return this;
    }









    public Plc build(){
        Plc plcToCreate = new Plc();

        if(tools != null){
            tools.forEach(plcToCreate::addTool);
        }

        ReflectionTestUtils.setField(plcToCreate, "id", id);
        ReflectionTestUtils.setField(plcToCreate, "name", name);
        ReflectionTestUtils.setField(plcToCreate, "ipAddress", ipAddress);
        ReflectionTestUtils.setField(plcToCreate, "hardwareInformation", hardwareInformation);
        ReflectionTestUtils.setField(plcToCreate, "connection", connection);
        ReflectionTestUtils.setField(plcToCreate, "motorCurve", motorCurve);
        ReflectionTestUtils.setField(plcToCreate, "currentTool", currentTool);


        return plcToCreate;
    }
}
