package com.siemens.metal_forming.connection;

import com.siemens.metal_forming.connection.opcua.PlcDataOpcua;
import com.siemens.metal_forming.connection.opcua.ToolData;
import com.siemens.metal_forming.domain.Curve;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.enumerated.ConnectionStatus;
import com.siemens.metal_forming.enumerated.ToolStatusType;
import com.siemens.metal_forming.service.AutomaticMonitoringService;
import com.siemens.metal_forming.service.PlcAutomaticUpdateService;
import com.siemens.metal_forming.service.ReferenceCurveCalculationService;
import com.siemens.metal_forming.testBuilders.TestCurveBuilder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@DisplayName("<= PLC CONNECTOR SPECIFICATION =>")
@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlcConnectorSpec {
    PlcConnector plcConnector;
    @Mock PlcDataProvider plcDataProvider;
    @Mock PlcAutomaticUpdateService plcAutomaticUpdateService;
    @Mock AutomaticMonitoringService automaticMonitoringService;
    @Mock ReferenceCurveCalculationService referenceCurveCalculationService;

    @BeforeEach
    void initialize(){
        plcConnector = new PlcConnectorImpl(plcAutomaticUpdateService, automaticMonitoringService, referenceCurveCalculationService, plcDataProvider);
    }

    @Nested @DisplayName("CONNECT")
    class Connection{
        @Test @DisplayName("updates information about PLC and connect it")
        void updatesInformationAboutPlcAndConnectIt(){
            Plc plcToConnect = Plc.builder().name("newPlc").ipAddress("192.168.0.1").build();
            Curve motorCurve = new TestCurveBuilder().randomPoints(360).build();
            PlcData plcData = PlcDataOpcua.builder()
                    .connectionStatus(ConnectionStatus.CONNECTED)
                    .serialNumber("SN 01")
                    .firmwareNumber("FW 01")
                    //.MaxOperationSpeed(10)
                    .toolData(new ToolData(1, "newTool"))
                    .motorCurve(motorCurve)
                    .build();

            when(plcDataProvider.getPlcData("192.168.0.1")).thenReturn(plcData);


            Plc updatedPlc = plcConnector.connect(plcToConnect);
            Tool updatedTool = updatedPlc.getCurrentTool();

            verify(plcDataProvider, times(1).description("Plc should be connected")).getPlcData("192.168.0.1");

            SoftAssertions softAssertions = new SoftAssertions();
            // plc
            softAssertions.assertThat(updatedPlc.getName()).as("plcName should be set").isEqualTo("newPlc");
            softAssertions.assertThat(updatedPlc.getIpAddress()).as("ipAddress should be set").isEqualTo("192.168.0.1");
            softAssertions.assertThat(updatedPlc.getConnection().getStatus()).as("connection status should be set").isEqualTo(ConnectionStatus.CONNECTED);
            softAssertions.assertThat(updatedPlc.getHardwareInformation().getSerialNumber()).as("serialNumber should be set").isEqualTo("SN 01");
            softAssertions.assertThat(updatedPlc.getHardwareInformation().getFirmwareNumber()).as("firmwareNumber should be set").isEqualTo("FW 01");
            softAssertions.assertThat(updatedPlc.getMotorCurve()).as("motorCurve").isEqualTo(motorCurve);

            //tool
            softAssertions.assertThat(updatedPlc.getTools().size()).as("tool should be added").isEqualTo(1);
            softAssertions.assertThat(updatedTool).as("tool should be selected as current tool").isNotNull();
            softAssertions.assertThat(updatedTool.getToolNumber()).as("tool should be created with correct number").isEqualTo(1);
            softAssertions.assertThat(updatedTool.getNameFromPlc()).as("tool should be created with correct name").isEqualTo("newTool");
            //softAssertions.assertThat(updatedTool.getMaxSpeedOperation()).as("tool should be created with correct maxSpeedOperation").isEqualTo(10);
            softAssertions.assertThat(updatedTool.getToolStatus()).as("tool should be marked as autodetected").isEqualTo(ToolStatusType.AUTODETECTED);
            softAssertions.assertThat(updatedTool.getAutomaticMonitoring()).as("tool is created with automaticMonitoring false").isFalse();
            softAssertions.assertThat(updatedTool.getCalculateReferenceCurve()).as("tool should be created with calculationReferenceCurve false").isFalse();

            softAssertions.assertAll();
        }

        @Test @DisplayName("mark plc as disconnected when the connection over OPC UA could not be established")
        void markPlcAsDisconnectedIfTheConnectionWasNotSuccessful(){
            Plc plcToConnect = Plc.builder().name("newPlc").ipAddress("192.168.0.1").build();
            PlcData plcData = PlcDataOpcua.builder()
                    .connectionStatus(ConnectionStatus.DISCONNECTED)
                    .build();

            when(plcDataProvider.getPlcData("192.168.0.1")).thenReturn(plcData);

            Plc updatedPlc = plcConnector.connect(plcToConnect);

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(updatedPlc.getTools()).as("plc should have no tools").isEmpty();
            softAssertions.assertThat(updatedPlc.getConnection().getStatus()).as("plc should be disconnected").isEqualTo(ConnectionStatus.DISCONNECTED);
            softAssertions.assertThat(updatedPlc.getName()).as("plcName should be unchanged").isEqualTo("newPlc");
            softAssertions.assertThat(updatedPlc.getIpAddress()).as("ipAddress should be unchanged").isEqualTo("192.168.0.1");
            softAssertions.assertAll();
        }

    }

    @Nested @DisplayName("DISCONNECT")
    class Disconnect{
        @Test @DisplayName("disconnects plc based on IP address")
        void disconnectsPlcBasedOnIpAddress(){
            Plc plcToConnect = Plc.builder().name("newPlc").ipAddress("192.168.0.1").build();
            PlcData plcData = mock(PlcData.class);

            when(plcDataProvider.getPlcData("192.168.0.1")).thenReturn(plcData);

            plcConnector.connect(plcToConnect);
            plcConnector.disconnect("192.168.0.1");

            verify(plcData, times(1)).disconnect();
        }

    }
}
