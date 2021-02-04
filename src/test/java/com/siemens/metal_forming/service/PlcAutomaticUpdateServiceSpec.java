package com.siemens.metal_forming.service;

import com.siemens.metal_forming.connection.PlcData;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.enumerated.ConnectionStatus;
import com.siemens.metal_forming.repository.PlcRepository;
import com.siemens.metal_forming.service.impl.PlcAutomaticUpdateServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("<= PLC AUTOMATIC UPDATE SERVICE =>")
public class PlcAutomaticUpdateServiceSpec {

    private PlcAutomaticUpdateService plcAutomaticUpdateService;
    @Mock private PlcRepository plcRepository;
    @Mock private PlcData plcData;
    @Captor ArgumentCaptor<Plc> plcCaptor;

    @BeforeEach
    void initialize(){
        plcAutomaticUpdateService = new PlcAutomaticUpdateServiceImpl(plcRepository);
    }

    @Test @DisplayName("updates firmware number in database")
    void updatesFirmwareNumberInDatabase(){
        Plc plcInDatabase = new Plc();

        when(plcData.getIpAddress()).thenReturn("192.168.0.1");
        when(plcData.getFirmwareNumber()).thenReturn("FW-NEW-FIRMWARE-NUMBER");
        when(plcRepository.findByIpAddress("192.168.0.1")).thenReturn(Optional.of(plcInDatabase));

        plcAutomaticUpdateService.onFirmwareNumberChange(plcData);

        verify(plcRepository, times(1)).save(plcCaptor.capture());
        assertThat(plcCaptor.getValue().getHardwareInformation().getFirmwareNumber()).isEqualTo("FW-NEW-FIRMWARE-NUMBER");
    }

    @Test @DisplayName("updates serial number in database")
    void updatesSerialNumberInDatabase(){
        Plc plcInDatabase = new Plc();

        when(plcData.getIpAddress()).thenReturn("192.168.0.1");
        when(plcData.getSerialNumber()).thenReturn("SW-NEW-SERIAL-NUMBER");
        when(plcRepository.findByIpAddress("192.168.0.1")).thenReturn(Optional.of(plcInDatabase));

        plcAutomaticUpdateService.onSerialNumberChange(plcData);

        verify(plcRepository, times(1)).save(plcCaptor.capture());
        assertThat(plcCaptor.getValue().getHardwareInformation().getSerialNumber()).isEqualTo("SW-NEW-SERIAL-NUMBER");
    }

    @Test @DisplayName("updates connection status in database")
    void updatesConnectionStatusInDatabase(){
        Plc plcInDatabase = new Plc();

        when(plcData.getIpAddress()).thenReturn("192.168.0.1");
        when(plcData.getConnectionStatus()).thenReturn(ConnectionStatus.DISCONNECTED);
        when(plcRepository.findByIpAddress("192.168.0.1")).thenReturn(Optional.of(plcInDatabase));

        plcAutomaticUpdateService.onConnectionStatusChange(plcData);

        verify(plcRepository, times(1)).save(plcCaptor.capture());
        assertThat(plcCaptor.getValue().getConnection().getStatus()).isEqualTo(ConnectionStatus.DISCONNECTED);
    }



    @Nested @DisplayName("CHANGE CURRENT TOOL") @Disabled("Needs to be written after PlcData structure changes")
    class ChangeCurrentTool{

        @Test @DisplayName("if plc with given ip address is not found return throws exception")
        void ifPlcIsNotFoundThrowsException(){
//            //Mock
//            when(plcRepository.findByIpAddress(ipOfNotExistentPlc)).thenReturn(Optional.empty());
//
//            assertThrows(PlcNotFoundException.class, () -> plcService.changeCurrentTool(ipOfNotExistentPlc, toolNumberOfExistingTool));
        }

        @Nested @DisplayName("PLC IS IN DATABASE")
        class PlcIsInDatabase{

            @Test @DisplayName("when tool exists in plc's tools than it is selected as current tool")
            void whenToolExistsInPlcsToolsThanItIsSelectedAsCurrentTool(){
//                Mockito.reset(plcInDb);
//                plcService.changeCurrentTool(ipOfExistingPlc, toolNumberOfExistingTool);
//
//                verify(plcInDb, times(1)).setCurrentTool(toolNumberOfExistingTool);
            }

            @Test @DisplayName("calculation of reference curve of old tool is canceled if it was running")
            void cancelsReferenceCurveCalculationIfItWasRunning(){
//                Mockito.reset(plcInDb);
//                plcInDb.getCurrentTool().setCalculateReferenceCurve(true);
//
//                when(referenceCurveCalculationService.getReferenceCurveCalculation(toolNumberOfExistingTool))
//                        .thenReturn(Optional.of(new ReferenceCurveCalculation(2)));
//
//                plcService.changeCurrentTool(ipOfExistingPlc, toolNumberOfExistingTool);
//
//                verify(referenceCurveCalculationService, times(1)).removeCalculation(toolNumberOfExistingTool);
            }

            @Test @DisplayName("when tool does not exist in plc's tools then new is created")
            void whenToolDoesNotExistInPlcsToolsThenNewIsCreated(){
//                Tool newAutodetectedTool = Tool.builder()
//                        .plc(plcInDb)
//                        .toolNumber(2)
//                        .nameFromPlc("newTool")
//                        .maxSpeedOperation(10)
//                        .toolStatus(ToolStatusType.AUTODETECTED)
//                        .automaticMonitoring(false)
//                        .calculateReferenceCurve(false)
//                        .build();
//
//
//                PlcData plcData = Mockito.mock(PlcData.class);
//                when(plcData.getToolNumber()).thenReturn(2);
//                when(plcData.getToolName()).thenReturn("newTool");
//                when(plcData.getMaxOperationSpeed()).thenReturn(10);
//                when(plcConnector.getPlcData(any())).thenReturn(plcData);
//
//                plcService.changeCurrentTool(ipOfExistingPlc,2);
//
//                verify(plcRepository, times(1)).save(plcCaptor.capture());
//                assertThat(plcCaptor.getValue().getCurrentTool()).isEqualTo(newAutodetectedTool);
            }

            @Test @DisplayName("updates plc in database")
            void updatesPlcInDatabase(){
//                plcService.changeCurrentTool(ipOfExistingPlc, toolNumberOfExistingTool);
//
//                verify(plcRepository,Mockito.times(1)).save(plcInDb);
            }
        }
    }
}