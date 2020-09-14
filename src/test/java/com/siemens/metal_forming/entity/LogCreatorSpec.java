package com.siemens.metal_forming.entity;

import com.siemens.metal_forming.entity.log.CollisionPoint;
import com.siemens.metal_forming.entity.log.Log;
import com.siemens.metal_forming.entity.log.PlcInfo;
import com.siemens.metal_forming.entity.log.ToolInfo;
import com.siemens.metal_forming.enumerated.ToolStatusType;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = LogCreatorImpl.class)
@DisplayName("<= ENTITY MAPPER SPECIFICATION =>")
class LogCreatorSpec {
    @Autowired
    LogCreator logCreator;

    @Nested @DisplayName("LOG FROM PLC, MEASURED CURVE AND COLLISION POINTS")
    class LogFromPlcMeasuredCurveAndCollisionPoints{
        Plc plcWithAllAttributes;
        Curve measuredCurve;
        Set<CollisionPoint> collisionPoints;

        @BeforeEach
        void initializeForLog(){
            plcWithAllAttributes = new Plc();
            Curve motorCurve = new Curve();
            motorCurve.setId(1L);
            for(int i=0; i<100; i++){
                motorCurve.getPoints().add(new CurvePoint((float)Math.random(),(float)Math.random()));
            }
            Set<Tool> tools = new HashSet<>();
            for(int i=0; i<10; i++){
                Tool tool = new Tool();
                tool.setId((long)i);
                tool.setToolNumber(i);
                tool.setToolStatus(ToolStatusType.AUTODETECTED);
                Curve referenceCurve = new Curve();
                referenceCurve.setId((long)i);
                for(int j=0; j<100; j++){
                    referenceCurve.getPoints().add(new CurvePoint((float)Math.random(),(float)Math.random()));
                }
                tool.setReferenceCurve(referenceCurve);
                tools.add(tool);
            }

            plcWithAllAttributes.getHardwareInformation().setSerialNumber("SN 8370938");
            plcWithAllAttributes.getHardwareInformation().setFirmwareNumber("FW V1.2");
            plcWithAllAttributes.setMotorCurve(motorCurve);
            plcWithAllAttributes.setIpAddress("192.168.1.1");
            plcWithAllAttributes.markAsConnected();
            plcWithAllAttributes.setTools(tools);
            plcWithAllAttributes.setCurrentTool(0);
            plcWithAllAttributes.setName("name");
            plcWithAllAttributes.setId(1L);


            measuredCurve = new Curve();
            for(int i=0; i<100; i++){
                measuredCurve.getPoints().add(new CurvePoint((float)Math.random(),(float)Math.random()));
            }

            collisionPoints = new HashSet<>();
            for(int i=0; i<100; i++){
                collisionPoints.add(new CollisionPoint((float)Math.random(),(float)Math.random()));
            }
        }

        @Test @DisplayName("id of log should be null")
        void idOfLogShouldBeNull(){
            Log log = logCreator.create(plcWithAllAttributes,measuredCurve,collisionPoints);

            assertThat(log.getId()).isNull();
        }

        @Test @DisplayName("copies correctly all attributes")
        void copiesCorrectlyAllAttributes(){
            Log log = logCreator.create(plcWithAllAttributes,measuredCurve,collisionPoints);

            SoftAssertions softAssertions = new SoftAssertions();

            //measured curve
            for(int i=0; i<log.getMeasuredCurve().getPoints().size();i++){
                softAssertions.assertThat(log.getMeasuredCurve().getPoints().get(i)).isEqualTo(measuredCurve.getPoints().get(i));
            }

            //motor curve
            for(int i=0; i<log.getMotorCurve().getPoints().size();i++){
                softAssertions.assertThat(log.getMotorCurve().getPoints().get(i))
                        .as("motor curve point [%d]",i)
                        .isEqualTo(plcWithAllAttributes.getMotorCurve().getPoints().get(i));
            }

            //reference curve
            for(int i=0; i<log.getReferenceCurve().getPoints().size();i++){
                softAssertions.assertThat(log.getReferenceCurve().getPoints().get(i))
                        .as("reference curve point [%d]",i)
                        .isEqualTo(plcWithAllAttributes.getCurrentTool().getReferenceCurve().getPoints().get(i));
            }

            //collision points
            for(CollisionPoint collisionPoint:log.getCollisionPoints()){
                softAssertions.assertThat(collisionPoints.contains(collisionPoint))
                        .as("collision point ["+collisionPoint+"]")
                        .isEqualTo(true);
            }

            //plc information
            softAssertions.assertThat(log.getPlcInformation().getName()).as("plc name").isEqualTo(plcWithAllAttributes.getName());
            softAssertions.assertThat(log.getPlcInformation().getIpAddress()).as("ipAddress").isEqualTo(plcWithAllAttributes.getIpAddress());
            softAssertions.assertThat(log.getPlcInformation().getSerialNumber()).as("serialNumber").isEqualTo(plcWithAllAttributes.getHardwareInformation().getSerialNumber());
            softAssertions.assertThat(log.getPlcInformation().getFirmwareNumber()).as("firmwareNumber").isEqualTo(plcWithAllAttributes.getHardwareInformation().getFirmwareNumber());

            //tool information
            softAssertions.assertThat(log.getToolInformation().getName()).as("name").isEqualTo(plcWithAllAttributes.getCurrentTool().getName());
            softAssertions.assertThat(log.getToolInformation()).as("toolNumber").isEqualTo(plcWithAllAttributes.getCurrentTool().getToolNumber());
            softAssertions.assertThat(log.getToolInformation()).as("stopReaction").isEqualTo(plcWithAllAttributes.getCurrentTool().getStopReaction());
        }
    }

    @Nested @DisplayName("TO PLC INFORMATION")
    class ToPlcInformation{
        Plc plcWithAllAttributes;

        @BeforeEach
        void initialize(){
            plcWithAllAttributes = new Plc();
            Curve motorCurve = new Curve();
            motorCurve.setId(1L);
            for(int i=0; i<100; i++){
                motorCurve.getPoints().add(new CurvePoint((float)Math.random(),(float)Math.random()));
            }
            Set<Tool> tools = new HashSet<>();
            for(int i=0; i<10; i++){
                Tool tool = new Tool();
                tool.setId((long)i);
                tool.setToolNumber(i);
                tool.setToolStatus(ToolStatusType.AUTODETECTED);
                Curve referenceCurve = new Curve();
                referenceCurve.setId((long)i);
                for(int j=0; j<100; j++){
                    referenceCurve.getPoints().add(new CurvePoint((float)Math.random(),(float)Math.random()));
                }
                tool.setReferenceCurve(referenceCurve);
                tools.add(tool);
            }

            plcWithAllAttributes.getHardwareInformation().setSerialNumber("SN 8370938");
            plcWithAllAttributes.getHardwareInformation().setFirmwareNumber("FW V1.2");
            plcWithAllAttributes.setMotorCurve(motorCurve);
            plcWithAllAttributes.setIpAddress("192.168.1.1");
            plcWithAllAttributes.markAsConnected();
            plcWithAllAttributes.setTools(tools);
            plcWithAllAttributes.setCurrentTool(0);
            plcWithAllAttributes.setId(1L);
            plcWithAllAttributes.setName("name");
        }

        @Test @DisplayName("doesn't copy id of the plc")
        void doesNotCopyIdOfPlc(){
            PlcInfo plcInfo = logCreator.toPlcInfo(plcWithAllAttributes);

            assertThat(plcWithAllAttributes.getId()).isNotNull();
            assertThat(plcInfo.getId()).isNull();
        }

        @Test @DisplayName("copies all required attributes")
        void copiesAllRequiredAttributesFromPlc(){
            PlcInfo plcInfo = logCreator.toPlcInfo(plcWithAllAttributes);

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(plcInfo.getName()).as("name").isEqualTo(plcWithAllAttributes.getName());
            softAssertions.assertThat(plcInfo.getIpAddress()).as("ipAddress").isEqualTo(plcWithAllAttributes.getIpAddress());
            softAssertions.assertThat(plcInfo.getSerialNumber()).as("serialNumber").isEqualTo(plcWithAllAttributes.getHardwareInformation().getSerialNumber());
            softAssertions.assertThat(plcInfo.getFirmwareNumber()).as("firmwareNumber").isEqualTo(plcWithAllAttributes.getHardwareInformation().getFirmwareNumber());
            softAssertions.assertAll();
        }
    }

    @Nested @DisplayName("TO TOOL INFORMATION")
    class ToToolInformation{
        Tool toolWithAllAttributes;

        @BeforeEach
        void initialize(){
            toolWithAllAttributes = new Tool();
            toolWithAllAttributes.setId(1L);
            toolWithAllAttributes.setToolNumber(1);
            toolWithAllAttributes.setToolStatus(ToolStatusType.AUTODETECTED);
            Curve referenceCurve = new Curve();
            referenceCurve.setId(1L);
            for(int j=0; j<100; j++){
                referenceCurve.getPoints().add(new CurvePoint((float)Math.random(),(float)Math.random()));
            }
            toolWithAllAttributes.setReferenceCurve(referenceCurve);
        }

        @Test @DisplayName("doesn't copy id of the plc")
        void doesNotCopyIdOfPlc(){
            ToolInfo toolInfo = logCreator.toToolInfo(toolWithAllAttributes);

            assertThat(toolWithAllAttributes.getId()).isNotNull();
            assertThat(toolInfo.getId()).isNull();
        }

        @Test @DisplayName("copies all required attributes")
        void copiesAllRequiredAttributesFromPlc(){
            ToolInfo toolInfo = logCreator.toToolInfo(toolWithAllAttributes);

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(toolInfo.getName()).as("name").isEqualTo(toolWithAllAttributes.getName());
            softAssertions.assertThat(toolInfo.getToolNumber()).as("toolNumber").isEqualTo(toolWithAllAttributes.getToolNumber());
            softAssertions.assertThat(toolInfo.getStopReaction()).as("stopReaction").isEqualTo(toolWithAllAttributes.getStopReaction());
            softAssertions.assertAll();
        }
    }

    @Nested @DisplayName("COPY CURVE WITHOUT ID")
    class CopyCurveWithoutId{
        Curve curveWithAllAttributes;

        @BeforeEach
        void initializeForCurveWithoutId(){
            curveWithAllAttributes = new Curve();
            curveWithAllAttributes.setId(1L);
            for(int j=0; j<100; j++){
                curveWithAllAttributes.getPoints().add(new CurvePoint((float)Math.random(),(float)Math.random()));
            }
        }

        @Test @DisplayName("doesn't copy id of the plc")
        void doesNotCopyIdOfPlc(){
            Curve curveCopy = logCreator.toCurveWithoutId(curveWithAllAttributes);

            assertThat(curveWithAllAttributes.getId()).isNotNull();
            assertThat(curveCopy.getId()).isNull();
        }

        @Test @DisplayName("copies all required attributes")
        void copiesAllRequiredAttributesFromPlc(){
            Curve curveCopy = logCreator.toCurveWithoutId(curveWithAllAttributes);

            assertThat(curveCopy.getPoints().size()).as("number of points must be same").isEqualTo(curveWithAllAttributes.getPoints().size());
            for(int i = 0; i<curveCopy.getPoints().size();i++){
                assertThat(curveCopy.getPoints().get(i)).isEqualTo(curveWithAllAttributes.getPoints().get(i));
            }
        }

        @Test @DisplayName("deep copy curve - creates new curve with same points")
        void deepCopyCurve(){
            Curve curveCopy = logCreator.toCurveWithoutId(curveWithAllAttributes);

            assertThat(curveCopy != curveWithAllAttributes).as("curve is not same object").isEqualTo(true);
            assertThat(curveCopy.getPoints().get(0) != curveWithAllAttributes.getPoints().get(0)).as("point is not same object").isEqualTo(true);
        }
    }
}
