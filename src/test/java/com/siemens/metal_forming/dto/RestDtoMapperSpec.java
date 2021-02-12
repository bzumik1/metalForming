package com.siemens.metal_forming.dto;

import com.siemens.metal_forming.domain.PointOfTorqueAndSpeed;
import com.siemens.metal_forming.dto.log.LogDto;
import com.siemens.metal_forming.dto.log.PointOfTorqueAndSpeedDto;
import com.siemens.metal_forming.entity.*;
import com.siemens.metal_forming.entity.log.Log;
import com.siemens.metal_forming.entity.log.PlcInfo;
import com.siemens.metal_forming.entity.log.ToolInfo;
import com.siemens.metal_forming.enumerated.StopReactionType;
import com.siemens.metal_forming.enumerated.ToolStatusType;
import com.siemens.metal_forming.testBuilders.TestLogBuilder;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest(classes = RestDtoMapperImpl.class)
@DisplayName("<= DTO MAPPER SPECIFICATION =>")
class RestDtoMapperSpec {
    @Autowired
    private RestDtoMapper dtoMapper;



    @Nested @DisplayName("TO DTO")
    class ToDto{

        @Test @DisplayName("transforms Plc to PlcDto.Response.Overview correctly")
        void transformsPlcToPlcDtoResponseOverviewCorrectly(){
            Plc plcWithAllAttributes = Plc.builder().ipAddress("192.168.0.1").id(1L).name("name").build();
            plcWithAllAttributes.getHardwareInformation().setFirmwareNumber("FW 1.4a");
            plcWithAllAttributes.getHardwareInformation().setSerialNumber("SN 12KDJ3JJDSS");

            PlcDto.Response.Overview plcDto = dtoMapper.toPlcDtoOverview(plcWithAllAttributes);

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(plcDto).isNotNull();
            softAssertions.assertThat(plcDto.getIpAddress()).isEqualTo(plcWithAllAttributes.getIpAddress());
            softAssertions.assertThat(plcDto.getId()).isEqualTo(plcWithAllAttributes.getId());
            softAssertions.assertThat(plcDto.getHardwareInformation().getFirmwareNumber()).isEqualTo(plcWithAllAttributes.getHardwareInformation().getFirmwareNumber());
            softAssertions.assertThat(plcDto.getHardwareInformation().getSerialNumber()).isEqualTo(plcWithAllAttributes.getHardwareInformation().getSerialNumber());
            softAssertions.assertThat(plcDto.getConnection().getStatus()).isEqualTo(plcWithAllAttributes.getConnection().getStatus());
            softAssertions.assertThat(plcDto.getConnection().getLastStatusChange()).isEqualTo(plcWithAllAttributes.getConnection().getLastStatusChange());
            softAssertions.assertThat(plcDto.getName()).isEqualTo(plcWithAllAttributes.getName());
            softAssertions.assertAll();
        }

        @Test @DisplayName("transforms Tool to ToolDto.Response.Overview correctly")
        void transformsToolToToolDtoResponseOverviewCorrectly(){
            Tool toolWithAllAttributes = Tool.builder()
                    .id(1L)
                    .plc(Plc.builder().id(1L).build())
                    .toolNumber(1)
                    .nickName("nickName")
                    .toolStatus(ToolStatusType.AUTODETECTED)
                    .automaticMonitoring(true)
                    .maxSpeedOperation(10)
                    .numberOfReferenceCycles(10)
                    .calculateReferenceCurve(true)
                    .tolerance(new AbsoluteTolerance(1,1))
                    .referenceCurve(null)
                    .stopReaction(StopReactionType.IMMEDIATE)
                    .build();

            ToolDto.Response.Overview toolDto = dtoMapper.toToolDtoOverview(toolWithAllAttributes);

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(toolDto.getId()).as("id").isEqualTo(toolWithAllAttributes.getId());
            softAssertions.assertThat(toolDto.getPlcId()).as("plcId").isEqualTo(toolWithAllAttributes.getPlc().getId());
            softAssertions.assertThat(toolDto.getToolNumber()).as("toolNumber").isEqualTo(toolWithAllAttributes.getToolNumber());
            softAssertions.assertThat(toolDto.getName()).as("name").isEqualTo(toolWithAllAttributes.getNickName());
            softAssertions.assertThat(toolDto.getNumberOfReferenceCycles()).as("numberOfReferenceCycles").isEqualTo(toolWithAllAttributes.getNumberOfReferenceCycles());
            softAssertions.assertThat(toolDto.getCalculateReferenceCurve()).as("calculateReferenceCurve").isEqualTo(toolWithAllAttributes.getCalculateReferenceCurve());
            softAssertions.assertThat(toolDto.getTolerance()).as("tolerance").isEqualTo(new AbsoluteToleranceDto(1,1));
            softAssertions.assertThat(toolDto.getStopReaction()).as("stopReaction").isEqualTo(toolWithAllAttributes.getStopReaction());
            softAssertions.assertThat(toolDto.getAutomaticMonitoring()).as("automaticMonitoring").isEqualTo(toolWithAllAttributes.getAutomaticMonitoring());
            softAssertions.assertThat(toolDto.getReferenceCurveIsCalculated()).as("referenceCurveIsCalculated").isEqualTo(toolWithAllAttributes.getReferenceCurve()!=null);
            softAssertions.assertThat(toolDto.getToolStatus()).as("toolStatus").isEqualTo(toolWithAllAttributes.getToolStatus());
            softAssertions.assertAll();
        }

        @Test @DisplayName("transforms Log to LogDto.Response.Detail correctly")
        void transformsLogToLogDtoResponseDetailCorrectly(){
            Log logWithAllAttributes = new TestLogBuilder()
                    .id(1L)
                    .createdOn(new Timestamp(1))
                    .comment("comment")
                    .toolInformation(ToolInfo.builder()
                            .id(1L)
                            .nameFromPlc("nameFromPlc")
                            .nickName("nickName")
                            .toolNumber(1)
                            .toolId(1L)
                            .stopReaction(StopReactionType.IMMEDIATE)
                            .tolerance(new RelativeTolerance(10, 10))
                            .build())
                    .plcInformation(PlcInfo.builder()
                            .id(1L)
                            .name("plcName")
                            .ipAddress("192.168.0.1")
                            .serialNumber("SN 001")
                            .firmwareNumber("FW 001")
                            .build())
                    .randomPointOfTorqueAndSpeeds(2)
                    .randomMeasuredCurve(50)
                    .randomMotorCurve(50)
                    .randomReferenceCurve(50)
                    .build();

            LogDto.Response.Detail logDto = dtoMapper.toLogDtoDetail(logWithAllAttributes);

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(logDto.getId()).as("id").isEqualTo(1L);
            softAssertions.assertThat(logDto.getCreatedOn()).as("createdOn").isEqualTo(new Timestamp(1));
            softAssertions.assertThat(logDto.getComment()).as("comment").isEqualTo("comment");

            //ToolInformation
            softAssertions.assertThat(logDto.getToolInformation().getName()).as("name").isEqualTo("nickName");
            softAssertions.assertThat(logDto.getToolInformation().getToolNumber()).as("toolNumber").isEqualTo(1);
            softAssertions.assertThat(logDto.getToolInformation().getToolId()).as("toolId").isEqualTo(1L);
            softAssertions.assertThat(logDto.getToolInformation().getStopReaction()).as("stopReaction").isEqualTo(StopReactionType.IMMEDIATE);
            softAssertions.assertThat(logDto.getToolInformation().getTolerance()).as("tolerance").isEqualTo(new RelativeToleranceDto(10,10));

            //PlcInformation
            softAssertions.assertThat(logDto.getPlcInformation().getName()).as("plcName").isEqualTo("plcName");
            softAssertions.assertThat(logDto.getPlcInformation().getIpAddress()).as("ipAddress").isEqualTo("192.168.0.1");
            softAssertions.assertThat(logDto.getPlcInformation().getSerialNumber()).as("serialNumber").isEqualTo("SN 001");
            softAssertions.assertThat(logDto.getPlcInformation().getFirmwareNumber()).as("firmwareNumber").isEqualTo("FW 001");

            //PointOfTorqueAndSpeeds
            boolean isSame;
            for(PointOfTorqueAndSpeedDto pointDto:logDto.getCollisionPoints()){
                isSame = false;
                //must be equal at least to one point
                for (PointOfTorqueAndSpeed point:logWithAllAttributes.getCollisionPoints()){
                    if (pointDto.getSpeed() == point.getSpeed() && pointDto.getTorque() == point.getTorque()) {
                        isSame = true;
                        break;
                    }
                }
                softAssertions.assertThat(isSame).as("collisionPoints").isTrue();
            }

            //MeasuredCurve
            for(int i = 0; i<logDto.getMeasuredCurve().getPoints().size();i++){
                softAssertions.assertThat(logDto.getMeasuredCurve().getPoints().get(i).getSpeed())
                        .as("measuredCurve - speed")
                        .isEqualTo(logWithAllAttributes.getMeasuredCurve().getPoints().get(i).getSpeed());

                softAssertions.assertThat(logDto.getMeasuredCurve().getPoints().get(i).getTorque())
                        .as("measuredCurve - torque")
                        .isEqualTo(logWithAllAttributes.getMeasuredCurve().getPoints().get(i).getTorque());
            }

            //MotorCurve
            for(int i = 0; i<logDto.getMotorCurve().getPoints().size();i++){
                softAssertions.assertThat(logDto.getMotorCurve().getPoints().get(i).getSpeed())
                        .as("motorCurve - speed")
                        .isEqualTo(logWithAllAttributes.getMotorCurve().getPoints().get(i).getSpeed());

                softAssertions.assertThat(logDto.getMotorCurve().getPoints().get(i).getTorque())
                        .as("motorCurve - torque")
                        .isEqualTo(logWithAllAttributes.getMotorCurve().getPoints().get(i).getTorque());
            }

            //ReferenceCurve
            for(int i = 0; i<logDto.getReferenceCurve().getPoints().size();i++){
                softAssertions.assertThat(logDto.getReferenceCurve().getPoints().get(i).getSpeed())
                        .as("referenceCurve - speed")
                        .isEqualTo(logWithAllAttributes.getReferenceCurve().getPoints().get(i).getSpeed());

                softAssertions.assertThat(logDto.getReferenceCurve().getPoints().get(i).getTorque())
                        .as("referenceCurve - torque")
                        .isEqualTo(logWithAllAttributes.getReferenceCurve().getPoints().get(i).getTorque());
            }

            softAssertions.assertAll();
        }

        @Test @DisplayName("transforms Log to LogDto.Response.Overview correctly")
        void transformsLogToLogDtoResponseOverviewCorrectly(){
            Log logWithAllAttributes = new TestLogBuilder()
                    .id(1L)
                    .createdOn(new Timestamp(1))
                    .comment("comment")
                    .toolInformation(ToolInfo.builder()
                            .id(1L)
                            .nameFromPlc("nameFromPlc")
                            .toolNumber(1)
                            .toolId(1L)
                            .stopReaction(StopReactionType.IMMEDIATE)
                            .tolerance(new RelativeTolerance(10,10))
                            .build())
                    .plcInformation(PlcInfo.builder()
                            .id(1L)
                            .name("plcName")
                            .ipAddress("192.168.0.1")
                            .serialNumber("SN 001")
                            .firmwareNumber("FW 001")
                            .build())
                    .randomPointOfTorqueAndSpeeds(2)
                    .randomMeasuredCurve(50)
                    .randomMotorCurve(50)
                    .randomReferenceCurve(50)
                    .build();

            LogDto.Response.Overview logDto = dtoMapper.toLogDtoOverview(logWithAllAttributes);

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(logDto.getId()).as("id").isEqualTo(1L);
            softAssertions.assertThat(logDto.getCreatedOn()).as("createdOn").isEqualTo(new Timestamp(1));
            softAssertions.assertThat(logDto.getComment()).as("comment").isEqualTo("comment");

            //ToolInformation
            softAssertions.assertThat(logDto.getToolInformation().getName()).as("toolName").isEqualTo("nameFromPlc");
            softAssertions.assertThat(logDto.getToolInformation().getToolNumber()).as("toolNumber").isEqualTo(1);
            softAssertions.assertThat(logDto.getToolInformation().getToolId()).as("toolId").isEqualTo(1L);
            softAssertions.assertThat(logDto.getToolInformation().getStopReaction()).as("stopReaction").isEqualTo(StopReactionType.IMMEDIATE);
            softAssertions.assertThat(logDto.getToolInformation().getTolerance()).as("tolerance").isEqualTo(new RelativeToleranceDto(10,10));

            //PlcInformation
            softAssertions.assertThat(logDto.getPlcInformation().getName()).as("plcName").isEqualTo("plcName");
            softAssertions.assertThat(logDto.getPlcInformation().getIpAddress()).as("ipAddress").isEqualTo("192.168.0.1");
            softAssertions.assertThat(logDto.getPlcInformation().getSerialNumber()).as("serialNumber").isEqualTo("SN 001");
            softAssertions.assertThat(logDto.getPlcInformation().getFirmwareNumber()).as("firmwareNumber").isEqualTo("FW 001");

            softAssertions.assertAll();
        }
    }

    @Nested @DisplayName("FROM DTO")
    class FromDto{
        @Test @DisplayName("transforms PlcDto.Request.Create to Plc correctly")
        void transformsPlcDtoRequestCreateToPlcCorrectly(){
            PlcDto.Request.Create plcDto = PlcDto.Request.Create.builder().ipAddress("192.168.0.1").name("name").build();
            Plc plc = dtoMapper.toPlc(plcDto);

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(plc.getIpAddress()).isEqualTo(plcDto.getIpAddress());
            softAssertions.assertThat(plc.getName()).isEqualTo(plcDto.getName());
            softAssertions.assertAll();
        }

        @Test @DisplayName("transforms ToolDto.Request.Create to Plc correctly")
        void transformsToolDtoRequestCreateToToolCorrectly(){
            ToolDto.Request.Create toolDto = ToolDto.Request.Create.builder()
                    .toolNumber(1)
                    .automaticMonitoring(true)
                    .name("name")
                    .numberOfReferenceCycles(10)
                    .tolerance(new AbsoluteToleranceDto(1,1))
                    .calculateReferenceCurve(true)
                    .stopReaction(StopReactionType.IMMEDIATE)
                    .build();

            Tool tool = dtoMapper.toTool(toolDto);

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(tool.getToolNumber()).as("toolNumber").isEqualTo(toolDto.getToolNumber());
            softAssertions.assertThat(tool.getAutomaticMonitoring()).as("automaticMonitoring").isEqualTo(toolDto.getAutomaticMonitoring());
            softAssertions.assertThat(tool.getNameFromPlc()).as("nameFromPlc").isNull();
            softAssertions.assertThat(tool.getNickName()).as("nickName").isEqualTo(toolDto.getName());
            softAssertions.assertThat(tool.getNumberOfReferenceCycles()).as("numberOfReferenceCycles").isEqualTo(toolDto.getNumberOfReferenceCycles());
            softAssertions.assertThat(tool.getTolerance()).as("tolerance").isEqualTo(new AbsoluteTolerance(1,1));
            softAssertions.assertThat(tool.getCalculateReferenceCurve()).as("calculateReferenceCurve").isEqualTo(toolDto.getCalculateReferenceCurve());
            softAssertions.assertThat(tool.getStopReaction()).as("stopReaction").isEqualTo(toolDto.getStopReaction());
            softAssertions.assertThat(tool.getToolStatus()).as("toolStatus").isEqualTo(toolDto.getToolStatus());
            softAssertions.assertAll();
        }

        @Test @DisplayName("transforms ToolDto.Request.Update to Plc correctly")
        void transformsToolDtoRequestUpdateToToolCorrectly(){
            ToolDto.Request.Update toolDto = ToolDto.Request.Update.builder()
                    .toolNumber(1)
                    .automaticMonitoring(true)
                    .name("name")
                    .numberOfReferenceCycles(10)
                    .tolerance(new AbsoluteToleranceDto(1,1))
                    .calculateReferenceCurve(true)
                    .stopReaction(StopReactionType.IMMEDIATE)
                    .build();

            Tool tool = dtoMapper.toTool(toolDto);

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(tool.getToolNumber()).as("toolNumber").isEqualTo(toolDto.getToolNumber());
            softAssertions.assertThat(tool.getAutomaticMonitoring()).as("automaticMonitoring").isEqualTo(toolDto.getAutomaticMonitoring());
            softAssertions.assertThat(tool.getNameFromPlc()).as("nameFromPlc").isNull();
            softAssertions.assertThat(tool.getNickName()).as("nickName").isEqualTo(toolDto.getName());
            softAssertions.assertThat(tool.getNumberOfReferenceCycles()).as("numberOfReferenceCycles").isEqualTo(toolDto.getNumberOfReferenceCycles());
            softAssertions.assertThat(tool.getTolerance()).as("tolerance").isEqualTo(new AbsoluteTolerance(1,1));
            softAssertions.assertThat(tool.getCalculateReferenceCurve()).as("calculateReferenceCurve").isEqualTo(toolDto.getCalculateReferenceCurve());
            softAssertions.assertThat(tool.getStopReaction()).as("stopReaction").isEqualTo(toolDto.getStopReaction());
            softAssertions.assertAll();
        }
    }


}
