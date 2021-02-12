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

@SpringBootTest(classes = WebSocketDtoMapperImpl.class)
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
    }

    @Nested @DisplayName("FROM DTO")
    class FromDto{

    }


}
