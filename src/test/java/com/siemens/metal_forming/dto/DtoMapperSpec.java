package com.siemens.metal_forming.dto;

import com.siemens.metal_forming.MetalFormingApplication;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.enumerated.StopReactionType;
import com.siemens.metal_forming.enumerated.ToolStatusType;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = DtoMapperImpl.class)
@DisplayName("<= DTO MAPPER SPECIFICATION =>")
class DtoMapperSpec {
    @Autowired
    private DtoMapper dtoMapper;



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
                    .toolNumber(1)
                    .name("name")
                    .toolStatus(ToolStatusType.AUTODETECTED)
                    .automaticMonitoring(true)
                    .maxSpeedOperation(10)
                    .numberOfReferenceCycles(10)
                    .referenceCurve(null)
                    .stopReaction(StopReactionType.IMMEDIATE)
                    .build();

            ToolDto.Response.Overview toolDto = dtoMapper.toToolDtoOverview(toolWithAllAttributes);

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(toolDto.getId()).as("id").isEqualTo(toolWithAllAttributes.getId());
            softAssertions.assertThat(toolDto.getToolNumber()).as("toolNumber").isEqualTo(toolWithAllAttributes.getToolNumber());
            softAssertions.assertThat(toolDto.getName()).as("name").isEqualTo(toolWithAllAttributes.getName());
            softAssertions.assertThat(toolDto.getNumberOfReferenceCycles()).as("numberOfReferenceCycles").isEqualTo(toolWithAllAttributes.getNumberOfReferenceCycles());
            softAssertions.assertThat(toolDto.getStopReaction()).as("stopReaction").isEqualTo(toolWithAllAttributes.getStopReaction());
            softAssertions.assertThat(toolDto.getAutomaticMonitoring()).as("automaticMonitoring").isEqualTo(toolWithAllAttributes.getAutomaticMonitoring());
            softAssertions.assertThat(toolDto.getReferenceCurveIsCalculated()).as("referenceCurveIsCalculated").isEqualTo(toolWithAllAttributes.getReferenceCurve()!=null);
            softAssertions.assertThat(toolDto.getToolStatus()).as("toolStatus").isEqualTo(toolWithAllAttributes.getToolStatus());
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
                    .stopReaction(StopReactionType.IMMEDIATE)
                    .build();

            Tool tool = dtoMapper.toTool(toolDto);

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(tool.getToolNumber()).as("toolNumber").isEqualTo(toolDto.getToolNumber());
            softAssertions.assertThat(tool.getAutomaticMonitoring()).as("automaticMonitoring").isEqualTo(toolDto.getAutomaticMonitoring());
            softAssertions.assertThat(tool.getName()).as("name").isEqualTo(toolDto.getName());
            softAssertions.assertThat(tool.getNumberOfReferenceCycles()).as("numberOfReferenceCycles").isEqualTo(toolDto.getNumberOfReferenceCycles());
            softAssertions.assertThat(tool.getStopReaction()).as("stopReaction").isEqualTo(toolDto.getStopReaction());
            softAssertions.assertThat(tool.getToolStatus()).as("toolStatus").isEqualTo(toolDto.getToolStatus());
            softAssertions.assertAll();
        }
    }


}
