package com.siemens.metal_forming.testBuilders;

import com.siemens.metal_forming.entity.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

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

    public TestPlcBuilder connection(Connection connection){
        this.connection = connection;
        return this;
    }

    public TestPlcBuilder motorCurve(Curve motorCurve){
        this.motorCurve = motorCurve;
        return this;
    }

    public TestPlcBuilder currentTool(Tool currentTool){
        this.currentTool = currentTool;
        return this;
    }

    public TestPlcBuilder tools(Set<Tool> tools){
        this.tools = tools;
        return this;
    }

    public TestPlcBuilder randomTools(int numberOfRandomTools){
        tools = Stream.iterate(1, n ->n+1).map(i -> new TestToolBuilder().toolNumber(i).build()).limit(numberOfRandomTools).collect(Collectors.toSet());
        return this;
    }









    public Plc build(){
        Plc plcToCreate = new Plc(id,name,ipAddress,motorCurve,currentTool);
        if(tools != null){
            tools.forEach(plcToCreate::addTool);
        }

        return plcToCreate;
    }
}
