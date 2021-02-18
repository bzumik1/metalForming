package com.siemens.metal_forming.dto;

import com.siemens.metal_forming.domain.PointOfTorqueAndSpeed;
import com.siemens.metal_forming.dto.log.LogDto;
import com.siemens.metal_forming.dto.log.PointOfTorqueAndSpeedDto;
import com.siemens.metal_forming.entity.AbsoluteTolerance;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.entity.RelativeTolerance;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.entity.log.Log;
import com.siemens.metal_forming.entity.log.PlcInfo;
import com.siemens.metal_forming.entity.log.ToolInfo;
import com.siemens.metal_forming.enumerated.ConnectionStatus;
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

@SpringBootTest(classes = {WebSocketDtoMapperImpl.class, RestDtoMapperImpl.class})
@DisplayName("<= WEBSOCKET DTO MAPPER SPECIFICATION =>")
class WebSocketDtoMapperSpec {
    @Autowired
    private WebSocketDtoMapper dtoMapper;



    @Nested @DisplayName("TO DTO")
    class ToDto{
        @Test @DisplayName("transforms Plc to PlcDto.Response.Connection correctly")
        void transformsPlcToPlcDtoResponseConnectionCorrectly(){
            Plc testPlc = Plc.builder().id(1L).build();
            testPlc.markAsConnected();

            PlcDto.Response.Connection plcDto = dtoMapper.toPlcDtoConnection(testPlc);

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(plcDto).isNotNull();
            softAssertions.assertThat(plcDto.getId()).as("id").isEqualTo(testPlc.getId());
            softAssertions.assertThat(plcDto.getConnectionStatus()).isEqualTo(ConnectionStatus.CONNECTED);
            softAssertions.assertAll();
        }

        @Test @DisplayName("transforms Plc to PlcDto.Response.CurrentTool correctly")
        void transformsPlcToPlcDtoResponseCurrentToolCorrectly(){
            Plc testPlc = Plc.builder().id(1L).currentTool(Tool.builder().toolNumber(1).build()).build();

            PlcDto.Response.CurrentTool plcDto = dtoMapper.toPlcDtoCurrentTool(testPlc);

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(plcDto).isNotNull();
            softAssertions.assertThat(plcDto.getId()).as("id").isEqualTo(testPlc.getId());
            softAssertions.assertThat(plcDto.getToolNumber()).isEqualTo(1);
            softAssertions.assertAll();
        }

        @Test @DisplayName("transforms Plc to PlcDto.Response.NewTool correctly")
        void transformsPlcToPlcDtoResponseNewToolCorrectly(){
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
            Plc testPlc = Plc.builder().id(1L).currentTool(toolWithAllAttributes).build();

            PlcDto.Response.NewTool plcDto = dtoMapper.toPlcDtoNewTool(testPlc);

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(plcDto).isNotNull();
            softAssertions.assertThat(plcDto.getId()).as("id").isEqualTo(testPlc.getId());
            softAssertions.assertThat(plcDto.getNewTool().getId()).as("id").isEqualTo(toolWithAllAttributes.getId());
            softAssertions.assertThat(plcDto.getNewTool().getPlcId()).as("plcId").isEqualTo(toolWithAllAttributes.getPlc().getId());
            softAssertions.assertThat(plcDto.getNewTool().getToolNumber()).as("toolNumber").isEqualTo(toolWithAllAttributes.getToolNumber());
            softAssertions.assertThat(plcDto.getNewTool().getName()).as("name").isEqualTo(toolWithAllAttributes.getNickName());
            softAssertions.assertThat(plcDto.getNewTool().getNumberOfReferenceCycles()).as("numberOfReferenceCycles").isEqualTo(toolWithAllAttributes.getNumberOfReferenceCycles());
            softAssertions.assertThat(plcDto.getNewTool().getCalculateReferenceCurve()).as("calculateReferenceCurve").isEqualTo(toolWithAllAttributes.getCalculateReferenceCurve());
            softAssertions.assertThat(plcDto.getNewTool().getTolerance()).as("tolerance").isEqualTo(new AbsoluteToleranceDto(1,1));
            softAssertions.assertThat(plcDto.getNewTool().getStopReaction()).as("stopReaction").isEqualTo(toolWithAllAttributes.getStopReaction());
            softAssertions.assertThat(plcDto.getNewTool().getAutomaticMonitoring()).as("automaticMonitoring").isEqualTo(toolWithAllAttributes.getAutomaticMonitoring());
            softAssertions.assertThat(plcDto.getNewTool().getReferenceCurveIsCalculated()).as("referenceCurveIsCalculated").isEqualTo(toolWithAllAttributes.getReferenceCurve()!=null);
            softAssertions.assertThat(plcDto.getNewTool().getToolStatus()).as("toolStatus").isEqualTo(toolWithAllAttributes.getToolStatus());
            softAssertions.assertAll();
        }
    }

    @Nested @DisplayName("FROM DTO")
    class FromDto{

    }


}
