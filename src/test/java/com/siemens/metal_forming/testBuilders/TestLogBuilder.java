package com.siemens.metal_forming.testBuilders;

import com.siemens.metal_forming.domain.Curve;
import com.siemens.metal_forming.domain.PointOfTorqueAndSpeed;
import com.siemens.metal_forming.entity.log.Log;
import com.siemens.metal_forming.entity.log.PlcInfo;
import com.siemens.metal_forming.entity.log.ToolInfo;
import com.siemens.metal_forming.enumerated.StopReactionType;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.Timestamp;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestLogBuilder {
    Long id;
    Timestamp createdOn = new Timestamp(System.currentTimeMillis());
    Curve measuredCurve = new TestCurveBuilder().randomPoints(100).build();
    Curve motorCurve = new TestCurveBuilder().randomPoints(100).build();
    Curve referenceCurve = new TestCurveBuilder().randomPoints(100).build();
    Set<PointOfTorqueAndSpeed> collisionPoints = Set.of(new PointOfTorqueAndSpeed(1.1F,1.1F),new PointOfTorqueAndSpeed(2.2F,2.2F));
    PlcInfo plcInformation = PlcInfo.builder().name("plcName").firmwareNumber("FW 001").serialNumber("SN 001").ipAddress("192.168.0.1").build();
    ToolInfo toolInformation = ToolInfo.builder().nameFromPlc("toolName").toolId(1L).toolNumber(1).stopReaction(StopReactionType.IMMEDIATE).build();
    String comment;

    public TestLogBuilder id(Long id){
        this.id = id;
        return this;
    }

    public TestLogBuilder createdOn(Timestamp createdOn){
        this.createdOn = createdOn;
        return this;
    }

    public TestLogBuilder measuredCurve(Curve measuredCurve){
        this.measuredCurve = measuredCurve;
        return this;
    }

    public TestLogBuilder randomMeasuredCurve(int numberOfPoints){
        this.measuredCurve = new TestCurveBuilder().randomPoints(numberOfPoints).build();
        return this;
    }

    public TestLogBuilder motorCurve(Curve motorCurve){
        this.motorCurve = motorCurve;
        return this;
    }

    public TestLogBuilder randomMotorCurve(int numberOfPoints){
        this.motorCurve = new TestCurveBuilder().randomPoints(numberOfPoints).build();
        return this;
    }

    public TestLogBuilder referenceCurve(Curve referenceCurve){
        this.referenceCurve = referenceCurve;
        return this;
    }

    public TestLogBuilder randomReferenceCurve(int numberOfPoints){
        this.referenceCurve = new TestCurveBuilder().randomPoints(numberOfPoints).build();
        return this;
    }

    public TestLogBuilder collisionPoints(Set<PointOfTorqueAndSpeed> collisionPoints){
        this.collisionPoints = collisionPoints;
        return this;
    }

    public TestLogBuilder randomPointOfTorqueAndSpeeds(int numberOfPoints){
        this.collisionPoints = Stream
                .generate(() -> new PointOfTorqueAndSpeed((float)Math.random(),(float)Math.random()))
                .limit(numberOfPoints)
                .collect(Collectors.toSet());
        return this;
    }

    public TestLogBuilder plcInformation(PlcInfo plcInformation){
        this.plcInformation = plcInformation;
        return this;
    }

    public TestLogBuilder toolInformation(ToolInfo toolInformation){
        this.toolInformation = toolInformation;
        return this;
    }

    public TestLogBuilder comment(String comment){
        this.comment = comment;
        return this;
    }

    public Log build(){
        Log logToCreate = Log.builder().build();

        ReflectionTestUtils.setField(logToCreate, "id", id);
        ReflectionTestUtils.setField(logToCreate, "createdOn", createdOn);
        ReflectionTestUtils.setField(logToCreate, "measuredCurve", measuredCurve);
        ReflectionTestUtils.setField(logToCreate, "motorCurve", motorCurve);
        ReflectionTestUtils.setField(logToCreate, "referenceCurve", referenceCurve);
        ReflectionTestUtils.setField(logToCreate, "collisionPoints", collisionPoints);
        ReflectionTestUtils.setField(logToCreate, "plcInformation", plcInformation);
        ReflectionTestUtils.setField(logToCreate, "toolInformation", toolInformation);
        ReflectionTestUtils.setField(logToCreate, "comment", comment);

        return logToCreate;
    }
}
