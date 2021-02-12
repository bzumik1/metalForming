package com.siemens.metal_forming.service;

import com.siemens.metal_forming.connection.PlcData;
import com.siemens.metal_forming.dto.PlcDto;
import com.siemens.metal_forming.dto.ToolDto;
import com.siemens.metal_forming.dto.WebSocketDtoMapper;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.enumerated.ConnectionStatus;
import com.siemens.metal_forming.repository.PlcRepository;
import com.siemens.metal_forming.service.impl.PlcAutomaticUpdateServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("<= PLC AUTOMATIC UPDATE SERVICE =>")
public class PlcAutomaticUpdateServiceSpec {

    private PlcAutomaticUpdateService plcAutomaticUpdateService;
    @Mock private PlcRepository plcRepository;
    @Mock private SimpMessagingTemplate simpMessagingTemplate;
    @Mock private WebSocketDtoMapper mapper;
    @Mock private PlcData plcData;
    @Captor ArgumentCaptor<Plc> plcCaptor;

    @BeforeEach
    void initialize(){
        plcAutomaticUpdateService = new PlcAutomaticUpdateServiceImpl(plcRepository, simpMessagingTemplate, mapper);
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

    @Nested @DisplayName("UPDATE CONNECTION STATUS")
    class UpdateConnectionStatus{
        @Test @DisplayName("sends updated connection status over websocket")
        void sendsUpdatedConnectionStatusOverWebSocket(){
            Plc plcInDatabase = new Plc();
            PlcDto.Response.Connection plcDto = PlcDto.Response.Connection.builder().id(1L).connectionStatus(ConnectionStatus.CONNECTED).build();

            when(plcData.getIpAddress()).thenReturn("192.168.0.1");
            when(plcData.getConnectionStatus()).thenReturn(ConnectionStatus.DISCONNECTED);
            when(plcRepository.findByIpAddress("192.168.0.1")).thenReturn(Optional.of(plcInDatabase));
            when(mapper.toPlcDtoConnection(plcInDatabase)).thenReturn(plcDto);

            plcAutomaticUpdateService.onConnectionStatusChange(plcData);

            verify(simpMessagingTemplate, times(1)).convertAndSend("/topic/plcs/connection-status", plcDto);
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
    }




    @Nested @DisplayName("CHANGE CURRENT TOOL")
    class ChangeCurrentTool{
        @Test @DisplayName("sends current tool number status over websocket")
        void sendsCurrentToolNumberOverWebSocket(){
            Plc plcInDatabase = Plc.builder().addTool(Tool.builder().toolNumber(1).build()).build();
            PlcDto.Response.CurrentTool plcDto = PlcDto.Response.CurrentTool.builder().id(1L).toolNumber(1).build();

            when(plcData.getIpAddress()).thenReturn("192.168.0.1");
            when(plcData.getToolNumber()).thenReturn(1);
            when(plcRepository.findByIpAddressFetchTools("192.168.0.1")).thenReturn(Optional.of(plcInDatabase));
            when(mapper.toPlcDtoCurrentTool(plcInDatabase)).thenReturn(plcDto);

            plcAutomaticUpdateService.onToolNumberChange(plcData);

            verify(simpMessagingTemplate, times(1)).convertAndSend("/topic/plcs/current-tool", plcDto);
        }

        @Test @DisplayName("sends new tool over websocket")
        void sendsNewToolOverOverWebSocket(){
            Plc plcInDatabase = Plc.builder().build();
            PlcDto.Response.NewTool plcDto = PlcDto.Response.NewTool.builder().id(1L).newTool(ToolDto.Response.Overview.builder().build()).build();

            when(plcData.getIpAddress()).thenReturn("192.168.0.1");
            when(plcData.getToolNumber()).thenReturn(1);
            when(plcRepository.findByIpAddressFetchTools("192.168.0.1")).thenReturn(Optional.of(plcInDatabase));
            when(mapper.toPlcDtoNewTool(plcInDatabase)).thenReturn(plcDto);

            plcAutomaticUpdateService.onToolNumberChange(plcData);

            verify(simpMessagingTemplate, times(1)).convertAndSend("/topic/plcs/new-tool", plcDto);
        }

        @Nested @DisplayName("PLC IS IN DATABASE") @Disabled("Needs to be written after PlcData structure changes")
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
