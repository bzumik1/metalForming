package com.siemens.metal_forming.testBuilders.specification;

import com.siemens.metal_forming.entity.*;
import com.siemens.metal_forming.enumerated.ConnectionStatus;
import com.siemens.metal_forming.testBuilders.TestPlcBuilder;
import com.siemens.metal_forming.testBuilders.TestToolBuilder;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import java.sql.Timestamp;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("<= TEST PLC BUILDER SPECIFICATION =>")
class TestPlcBuilderSpec extends TestBuilderSpec{
    TestPlcBuilder testPlcBuilder;

    TestPlcBuilderSpec() {
        super(Plc.class, TestPlcBuilder.class);
    }

    @BeforeEach
    void initialize(){
        testPlcBuilder = new TestPlcBuilder();
    }

    @Nested @DisplayName("SPECIAL METHODS")
    class SpecialMethods{
        @Test @DisplayName("adds given number of random tools to new plc")
        void addsGivenNumberOfRandomTools(){
            Plc testPlc = testPlcBuilder.randomTools(3).build();

            assertThat(testPlc.getTools().size()).isEqualTo(3);
        }

        @Test @DisplayName("sets status to CONNECTED and lastStatusChange to given timestamp")
        void setsStatusToConnectedAndLastStatusChangeToGivenTimestamp(){
            Plc testPlc = testPlcBuilder.connectedOn(new Timestamp(1)).build();

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(testPlc.getConnection().getStatus()).as("status").isEqualTo(ConnectionStatus.CONNECTED);
            softAssertions.assertThat(testPlc.getConnection().getLastStatusChange()).as("lastStatusChange").isEqualTo(new Timestamp(1L));
            softAssertions.assertAll();
        }

        @Test @DisplayName("sets status to DISCONNECTED and lastStatusChange to given timestamp")
        void setsStatusToDisconnectedAndLastStatusChangeToGivenTimestamp(){
            Plc testPlc = testPlcBuilder.disconnectedOn(new Timestamp(1)).build();

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(testPlc.getConnection().getStatus()).as("status").isEqualTo(ConnectionStatus.DISCONNECTED);
            softAssertions.assertThat(testPlc.getConnection().getLastStatusChange()).as("lastStatusChange").isEqualTo(new Timestamp(1L));
            softAssertions.assertAll();
        }

        @Test @DisplayName("adds tool to tools of new plc")
        void addsToolToToolsOfNewPlc(){
            Plc testPlc = testPlcBuilder
                    .addTool(new TestToolBuilder().toolNumber(1).build())
                    .addTool(new TestToolBuilder().toolNumber(2).build())
                    .build();

            assertThat(testPlc.getTools().size()).isEqualTo(2);
        }
    }


    @Nested @DisplayName("DIRECT SETTING METHODS")
    class DirectSettingMethods{
        @Test @DisplayName("sets id of new plc")
        void setsIdOfNewPlc(){
            Plc testPlc = testPlcBuilder.id(99L).build();

            assertThat(testPlc.getId()).isEqualTo(99L);
        }

        @Test @DisplayName("sets name of new plc")
        void setsNameOfNewPlc(){
            Plc testPlc = testPlcBuilder.name("newName").build();

            assertThat(testPlc.getName()).isEqualTo("newName");
        }

        @Test @DisplayName("sets ipAddress of new plc")
        void setsIpAddressOfNewPlc(){
            Plc testPlc = testPlcBuilder.ipAddress("192.168.0.1").build();

            assertThat(testPlc.getIpAddress()).isEqualTo("192.168.0.1");
        }

        @Test @DisplayName("sets hardwareInformation of new plc")
        void setsHardwareInformationOfNewPlc(){
            Plc testPlc = testPlcBuilder
                    .hardwareInformation(HardwareInformation.builder().serialNumber("SN 999").firmwareNumber("FW 999").build())
                    .build();

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(testPlc.getHardwareInformation().getSerialNumber()).isEqualTo("SN 999");
            softAssertions.assertThat(testPlc.getHardwareInformation().getFirmwareNumber()).isEqualTo("FW 999");
            softAssertions.assertAll();
        }

        @Test @DisplayName("sets connection of new plc")
        void setsConnectionOfNewPlc(){
            Plc testPlc = testPlcBuilder.connection(new Connection(null, ConnectionStatus.CONNECTED)).build();

            assertThat(testPlc.getConnection().getStatus()).isEqualTo(ConnectionStatus.CONNECTED);
        }

        @Test @DisplayName("sets motorCurve of new plc")
        void setsMotorCurveOfNewPlc(){
            Curve testCurve = Curve.builder()
                    .points(Stream.generate(() -> new CurvePoint(1.1F,1.1F)).limit(10).collect(Collectors.toList()))
                    .build();
            Plc testPlc = testPlcBuilder
                    .motorCurve(testCurve)
                    .build();

            assertThat(testPlc.getMotorCurve().getPoints().size()).isEqualTo(10);
        }

        @Test @DisplayName("sets currentTool of new plc")
        void setsCurrentToolOfNewPlc(){
            Tool testTool = Tool.builder().toolNumber(1).build();
            Plc testPlc = testPlcBuilder
                    .currentTool(testTool)
                    .build();

            assertThat(testPlc.getCurrentTool().getToolNumber()).isEqualTo(1);
        }

        @Test @DisplayName("sets tools of new plc")
        void setsToolsOfNewPlc(){
            Set<Tool> tools = Set.of(Tool.builder().toolNumber(1).build(),Tool.builder().toolNumber(2).build());
            Plc testPlc = testPlcBuilder
                    .tools(tools)
                    .build();

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(testPlc.getTools().containsAll(tools)).as("should contain all tools").isEqualTo(true);
            testPlc.getTools().forEach(tool -> softAssertions.assertThat(tool.getPlc()).isEqualTo(testPlc));
            softAssertions.assertAll();
        }
    }




}
