package com.siemens.metal_forming.dto;

import com.siemens.metal_forming.entity.Plc;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("<= DTO MAPPER SPECIFICATION =>")
public class DtoMapperSpec {
    private DtoMapper mapper;
    @BeforeEach
    void initialize(){
        mapper = DtoMapper.INSTANCE;
    }



    @Nested @DisplayName("TO DTO")
    class ToDto{
        private Plc plcWithAllAttributes;

        @BeforeEach
        void initializeForToDto(){
            plcWithAllAttributes = Plc.builder().ipAddress("192.168.0.1").id(1L).name("name").build();
            plcWithAllAttributes.getHardwareInformation().setFirmwareNumber("FW 1.4a");
            plcWithAllAttributes.getHardwareInformation().setSerialNumber("SN 12KDJ3JJDSS");
        }

        @Test @DisplayName("transforms Plc to PlcDto.Response.Overview correctly")
        void transformsPlcToPlcDtoResponseOverviewCorrectly(){
            PlcDto.Response.Overview plcDto = mapper.toPlcDtoOverview(plcWithAllAttributes);

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
    }

    @Nested @DisplayName("FROM DTO")
    class FromDto{
        @Test @DisplayName("transforms PlcDto.Request.Create to Plc correctly")
        void transformsPlcDtoRequestCreateToPlcCorrectly(){
            PlcDto.Request.Create plcDto = PlcDto.Request.Create.builder().ipAddress("192.168.0.1").name("name").build();
            Plc plc = mapper.toPlc(plcDto);

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(plc.getIpAddress()).isEqualTo(plcDto.getIpAddress());
            softAssertions.assertThat(plc.getName()).isEqualTo(plcDto.getName());
            softAssertions.assertAll();
        }
    }


}
