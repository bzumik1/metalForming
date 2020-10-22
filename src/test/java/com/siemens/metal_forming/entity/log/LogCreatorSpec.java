package com.siemens.metal_forming.entity.log;

import com.siemens.metal_forming.entity.*;
import com.siemens.metal_forming.enumerated.StopReactionType;
import com.siemens.metal_forming.testBuilders.TestCurveBuilder;
import com.siemens.metal_forming.testBuilders.TestPlcBuilder;
import com.siemens.metal_forming.testBuilders.TestToolBuilder;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = LogCreatorImpl.class)
@DisplayName("<= ENTITY MAPPER SPECIFICATION =>")
class LogCreatorSpec {
    @Autowired
    LogCreator logCreator;

    TestCurveBuilder testCurveBuilder;
    TestToolBuilder testToolBuilder;
    TestPlcBuilder testPlcBuilder;

    @BeforeEach
    void initialize(){
        testCurveBuilder = new TestCurveBuilder();
        testToolBuilder = new TestToolBuilder();
        testPlcBuilder = new TestPlcBuilder();
    }

    @Nested @DisplayName("LOG FROM PLC, MEASURED CURVE AND COLLISION POINTS")
    class LogFromPlcMeasuredCurveAndCollisionPoints{


        @Test @DisplayName("id of log should be null")
        void idOfLogShouldBeNull(){
            Plc plcForLog = testPlcBuilder.id(1L).build();
            Curve measuredCurve = testCurveBuilder.id(1L).build();
            Set<CollisionPoint> collisionPoints = Stream
                    .generate(() -> CollisionPoint.builder().id(1L).speed((float)Math.random()).torque((float)Math.random()).build())
                    .limit(2)
                    .collect(Collectors.toSet());

            Log log = logCreator.create(plcForLog,measuredCurve,collisionPoints);

            assertThat(log.getId()).isNull();
        }

        @Test @DisplayName("copies correctly all attributes")
        void copiesCorrectlyAllAttributes(){
            Plc plcForLog = testPlcBuilder
                    .name("plcName").ipAddress("192.168.0.1").serialNumber("SN 001").firmwareNumber("FW 001")
                    .motorCurve(testCurveBuilder.randomPoints(10).build())
                    .currentTool(testToolBuilder
                            .id(1L).name("toolName").toolNumber(1).stopReaction(StopReactionType.IMMEDIATE)
                            .referenceCurve(testCurveBuilder.randomPoints(20).build())
                            .build())
                    .build();
            Curve measuredCurve =testCurveBuilder.randomPoints(30).build();
            Set<CollisionPoint> collisionPoints = Stream
                    .generate(() -> CollisionPoint.builder().id(1L).speed((float)Math.random()).torque((float)Math.random()).build())
                    .limit(2)
                    .collect(Collectors.toSet());


            Log log = logCreator.create(plcForLog,measuredCurve,collisionPoints);

            SoftAssertions softAssertions = new SoftAssertions();

            //measured curve
            for(int i=0; i<log.getMeasuredCurve().getPoints().size();i++){
                softAssertions.assertThat(log.getMeasuredCurve().getPoints().get(i))
                        .as("measured curve point[%d]",i)
                        .isEqualTo(measuredCurve.getPoints().get(i));
            }

            //motor curve
            for(int i=0; i<log.getMotorCurve().getPoints().size();i++){
                softAssertions.assertThat(log.getMotorCurve().getPoints().get(i))
                        .as("motor curve point [%d]",i)
                        .isEqualTo(plcForLog.getMotorCurve().getPoints().get(i));
            }

            //reference curve
            for(int i=0; i<log.getReferenceCurve().getPoints().size();i++){
                softAssertions.assertThat(log.getReferenceCurve().getPoints().get(i))
                        .as("reference curve point [%d]",i)
                        .isEqualTo(plcForLog.getCurrentTool().getReferenceCurve().getPoints().get(i));
            }

            //collision points
            softAssertions.assertThat(collisionPoints).containsAll(log.getCollisionPoints());

            //plc information
            softAssertions.assertThat(log.getPlcInformation().getName()).as("plc name").isEqualTo("plcName");
            softAssertions.assertThat(log.getPlcInformation().getIpAddress()).as("ipAddress").isEqualTo("192.168.0.1");
            softAssertions.assertThat(log.getPlcInformation().getSerialNumber()).as("serialNumber").isEqualTo("SN 001");
            softAssertions.assertThat(log.getPlcInformation().getFirmwareNumber()).as("firmwareNumber").isEqualTo("FW 001");

            //tool information
            softAssertions.assertThat(log.getToolInformation().getName()).as("name").isEqualTo("toolName");
            softAssertions.assertThat(log.getToolInformation().getToolId()).as("toolId").isEqualTo(1L);
            softAssertions.assertThat(log.getToolInformation().getToolNumber()).as("toolNumber").isEqualTo(1);
            softAssertions.assertThat(log.getToolInformation().getStopReaction()).as("stopReaction").isEqualTo(StopReactionType.IMMEDIATE);

            softAssertions.assertAll();
        }
    }

    @Nested @DisplayName("TO PLC INFORMATION")
    class ToPlcInformation{

        @Test @DisplayName("doesn't copy id of the plc as id of plcInfo")
        void doesNotCopyIdOfPlcAsIdOfPlcInfo(){
            Plc originalPlc = testPlcBuilder.id(1L).build();

            PlcInfo plcInfo = logCreator.toPlcInfo(originalPlc);

            assertThat(plcInfo.getId()).isNull();
        }

        @Test @DisplayName("copies all required attributes")
        void copiesAllRequiredAttributesFromPlc(){
            Plc originalPlc = testPlcBuilder
                    .name("plcName").ipAddress("192.168.0.1").serialNumber("SN 001").firmwareNumber("FW 001").build();

            PlcInfo plcInfo = logCreator.toPlcInfo(originalPlc);

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(plcInfo.getName()).as("name").isEqualTo("plcName");
            softAssertions.assertThat(plcInfo.getIpAddress()).as("ipAddress").isEqualTo("192.168.0.1");
            softAssertions.assertThat(plcInfo.getSerialNumber()).as("serialNumber").isEqualTo("SN 001");
            softAssertions.assertThat(plcInfo.getFirmwareNumber()).as("firmwareNumber").isEqualTo("FW 001");
            softAssertions.assertAll();
        }
    }

    @Nested @DisplayName("TO TOOL INFORMATION")
    class ToToolInformation{

        @Test @DisplayName("doesn't copy id of the tool as id of toolInfo")
        void doesNotCopyIdOfToolAsToolInfo(){
            Tool originalTool = testToolBuilder.id(1L).build();

            ToolInfo toolInfo = logCreator.toToolInfo(originalTool);

            assertThat(toolInfo.getId()).isNull();
        }

        @Test @DisplayName("copies all required attributes")
        void copiesAllRequiredAttributesFromPlc(){
            Tool originalTool = testToolBuilder.name("toolName").id(1L).toolNumber(1).stopReaction(StopReactionType.IMMEDIATE).build();

            ToolInfo toolInfo = logCreator.toToolInfo(originalTool);

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(toolInfo.getName()).as("name").isEqualTo("toolName");
            softAssertions.assertThat(toolInfo.getToolId()).as("toolId").isEqualTo(1L);
            softAssertions.assertThat(toolInfo.getToolNumber()).as("toolNumber").isEqualTo(1);
            softAssertions.assertThat(toolInfo.getStopReaction()).as("stopReaction").isEqualTo(StopReactionType.IMMEDIATE);
            softAssertions.assertAll();
        }
    }

    @Nested @DisplayName("COPY CURVE WITHOUT ID")
    class CopyCurveWithoutId{

        @Test @DisplayName("doesn't copy id of the curve")
        void doesNotCopyIdOfPlc(){
            Curve originalCurve = testCurveBuilder.id(1L).build();

            Curve copyOfOriginalCurve = logCreator.toCurveWithoutId(originalCurve);

            assertThat(copyOfOriginalCurve.getId()).isNull();
        }

        @Test @DisplayName("copies all required attributes")
        void copiesAllRequiredAttributesFromPlc(){
            Curve originalCurve = testCurveBuilder
                    .points(
                            CurvePoint.builder().speed(1.1F).torque(1.1F).build(),
                            CurvePoint.builder().speed(2.2F).torque(2.2F).build())
                    .build();

            Curve curveCopy = logCreator.toCurveWithoutId(originalCurve);

            assertThat(curveCopy.getPoints()).containsAll(originalCurve.getPoints());
        }

        @Test @DisplayName("deep copy curve - creates new curve with same points")
        void deepCopyCurve(){
            Curve originalCurve = testCurveBuilder
                    .points(
                            CurvePoint.builder().speed(1.1F).torque(1.1F).build(),
                            CurvePoint.builder().speed(2.2F).torque(2.2F).build())
                    .build();
            Curve curveCopy = logCreator.toCurveWithoutId(originalCurve);

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(curveCopy)
                    .as("curve is not same object")
                    .isNotSameAs(originalCurve);
            IntStream.range(0, curveCopy.getPoints().size())
                    .forEach(i -> softAssertions.assertThat(curveCopy.getPoints().get(i))
                            .as("points should not be same objects")
                            .isNotSameAs(originalCurve.getPoints().get(i)));
            softAssertions.assertAll();
        }

        @Test @DisplayName("doesn't copy id of points")
        void doesNotCopyIdOfPoints(){
            Curve originalCurve = testCurveBuilder
                    .points(
                            CurvePoint.builder().id(1L).speed(1.1F).torque(1.1F).build(),
                            CurvePoint.builder().id(2L).speed(2.2F).torque(2.2F).build())
                    .build();
            Curve curveCopy = logCreator.toCurveWithoutId(originalCurve);

            SoftAssertions softAssertions = new SoftAssertions();
            curveCopy.getPoints().forEach(curvePoint -> softAssertions.assertThat(curvePoint.getId()).isNull());
            softAssertions.assertAll();
        }
    }
}