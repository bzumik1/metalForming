package com.siemens.metal_forming.service;

import com.siemens.metal_forming.connection.PlcConnector;
import com.siemens.metal_forming.connection.PlcData;
import com.siemens.metal_forming.connection.opcua.PlcDataOpcua;
import com.siemens.metal_forming.dto.DtoMapper;
import com.siemens.metal_forming.dto.PlcDto;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.enumerated.ConnectionStatus;
import com.siemens.metal_forming.enumerated.ToolStatusType;
import com.siemens.metal_forming.exception.exceptions.PlcNotFoundException;
import com.siemens.metal_forming.exception.exceptions.PlcUniqueConstrainException;

import com.siemens.metal_forming.repository.PlcRepository;
import com.siemens.metal_forming.service.impl.PlcServiceImpl;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayName("<= PLC SERVICE SPECIFICATION =>")
class PlcServiceSpec {
    private PlcService plcService;
    @Mock private PlcRepository plcRepository;
    @Mock private PlcConnector plcConnector;
    @Mock private DtoMapper dtoMapper;
    @Mock private PlcData plcData;

    @Captor ArgumentCaptor<Plc> plcCaptor;



    @BeforeEach
    void initialize() {
        plcService = new PlcServiceImpl(plcRepository, plcConnector, dtoMapper);
    }

    @Nested @DisplayName("CREATE PLC")
    class CreatePlc{
        @Nested @DisplayName("PLC IS CONNECTED")
        class PlcIsConnected{
            @Test @DisplayName("updates PLC information from PlcData")
            void updatesPlcInformationFromPlcData(){
                PlcDto.Request.Create plcDto = PlcDto.Request.Create.builder().name("newPlc").ipAddress("192.168.0.1").build();
                Plc newPlc = Plc.builder().name("newPlc").ipAddress("192.168.0.1").build();
                PlcData plcData = PlcDataOpcua.builder()
                        .connectionStatus(ConnectionStatus.CONNECTED)
                        .serialNumber("SN 01")
                        .firmwareNumber("FW 01")
                        .MaxOperationSpeed(10)
                        .toolName("newTool")
                        .toolNumber(1)
                        .build();

                when(plcConnector.connectPlc("192.168.0.1")).thenReturn(plcData);
                when(dtoMapper.toPlc(plcDto)).thenReturn(newPlc);
                when(plcRepository.existsByName("newPlc")).thenReturn(false);
                when(plcRepository.existsByIpAddress("192.168.0.1")).thenReturn(false);

                plcService.createPlc(plcDto);

                verify(plcRepository, times(1)).save(plcCaptor.capture());
                Plc plcStoredToDb = plcCaptor.getValue();
                Tool toolStoredToDb = plcStoredToDb.getCurrentTool();

                SoftAssertions softAssertions = new SoftAssertions();
                // plc
                softAssertions.assertThat(plcStoredToDb.getName()).as("plcName should be set").isEqualTo("newPlc");
                softAssertions.assertThat(plcStoredToDb.getIpAddress()).as("ipAddress should be set").isEqualTo("192.168.0.1");
                softAssertions.assertThat(plcStoredToDb.getConnection().getStatus()).as("connection status should be set").isEqualTo(ConnectionStatus.CONNECTED);
                softAssertions.assertThat(plcStoredToDb.getHardwareInformation().getSerialNumber()).as("serialNumber should be set").isEqualTo("SN 01");
                softAssertions.assertThat(plcStoredToDb.getHardwareInformation().getFirmwareNumber()).as("firmwareNumber should be set").isEqualTo("FW 01");

                //tool
                softAssertions.assertThat(plcStoredToDb.getTools().size()).as("tool should be added").isEqualTo(1);
                softAssertions.assertThat(toolStoredToDb).as("tool should be selected as current tool").isNotNull();
                softAssertions.assertThat(toolStoredToDb.getToolNumber()).as("tool should be created with correct number").isEqualTo(1);
                softAssertions.assertThat(toolStoredToDb.getNameFromPlc()).as("tool should be created with correct name").isEqualTo("newTool");
                softAssertions.assertThat(toolStoredToDb.getMaxSpeedOperation()).as("tool should be created with correct maxSpeedOperation").isEqualTo(10);
                softAssertions.assertThat(toolStoredToDb.getToolStatus()).as("tool should be marked as autodetected").isEqualTo(ToolStatusType.AUTODETECTED);
                softAssertions.assertThat(toolStoredToDb.getAutomaticMonitoring()).as("tool is created with automaticMonitoring false").isFalse();
                softAssertions.assertThat(toolStoredToDb.getCalculateReferenceCurve()).as("tool should be created with calculationReferenceCurve false").isFalse();

                softAssertions.assertAll();
            }
        }

        @Nested @DisplayName("PLC IS DISCONNECTED")
        class PlcIsDisconnected{
            @Test @DisplayName("mark plc as disconnected when the connection over OPC UA could not be established")
            void markPlcAsDisconnectedIfTheConnectionWasNotSuccessful(){
                PlcDto.Request.Create plcDto = PlcDto.Request.Create.builder().name("newPlc").ipAddress("192.168.0.1").build();
                Plc newPlc = Plc.builder().name("newPlc").ipAddress("192.168.0.1").build();
                PlcData plcData = PlcDataOpcua.builder()
                        .connectionStatus(ConnectionStatus.DISCONNECTED)
                        .build();

                when(plcConnector.connectPlc("192.168.0.1")).thenReturn(plcData);
                when(dtoMapper.toPlc(plcDto)).thenReturn(newPlc);
                when(plcRepository.existsByName("newPlc")).thenReturn(false);
                when(plcRepository.existsByIpAddress("192.168.0.1")).thenReturn(false);

                plcService.createPlc(plcDto);

                verify(plcRepository, times(1)).save(plcCaptor.capture());
                Plc plcStoredToDb = plcCaptor.getValue();

                SoftAssertions softAssertions = new SoftAssertions();
                softAssertions.assertThat(plcStoredToDb.getTools()).as("plc has no tools").isEmpty();
                softAssertions.assertThat(plcStoredToDb.getConnection().getStatus()).as("plc is disconnected").isEqualTo(ConnectionStatus.DISCONNECTED);
                softAssertions.assertThat(plcStoredToDb.getName()).as("plcName is set").isEqualTo("newPlc");
                softAssertions.assertThat(plcStoredToDb.getIpAddress()).as("ipAddress is set").isEqualTo("192.168.0.1");
                softAssertions.assertAll();
            }
        }

        @Nested @DisplayName("UNIQUE VIOLATION")
        class UniqueViolation{
            @Test @DisplayName("throws PlcUniqueConstrainViolationException when PLC has same IP address as one of PLCs in database")
            void throwsExceptionWhenIpAddressIsNotUnique(){
                PlcDto.Request.Create plcDto = PlcDto.Request.Create.builder().name("newPlc").ipAddress("192.168.0.1").build();
                Plc newPlc = Plc.builder().name("newPlc").ipAddress("192.168.0.1").build();
                PlcData plcData = PlcDataOpcua.builder()
                        .connectionStatus(ConnectionStatus.DISCONNECTED)
                        .build();

                when(plcConnector.connectPlc("192.168.0.1")).thenReturn(plcData);
                when(dtoMapper.toPlc(plcDto)).thenReturn(newPlc);
                when(plcRepository.existsByName("newPlc")).thenReturn(false);
                when(plcRepository.existsByIpAddress("192.168.0.1")).thenReturn(true);

                assertThrows(PlcUniqueConstrainException.class,() -> plcService.createPlc(plcDto));
            }

            @Test @DisplayName("throws PlcUniqueConstrainViolationException when PLC has same name as one of PLCs in database")
            void throwsExceptionWhenNameIsNotUnique(){
                PlcDto.Request.Create plcDto = PlcDto.Request.Create.builder().name("newPlc").ipAddress("192.168.0.1").build();
                Plc newPlc = Plc.builder().name("newPlc").ipAddress("192.168.0.1").build();
                PlcData plcData = PlcDataOpcua.builder()
                        .connectionStatus(ConnectionStatus.DISCONNECTED)
                        .build();

                when(plcConnector.connectPlc("192.168.0.1")).thenReturn(plcData);
                when(dtoMapper.toPlc(plcDto)).thenReturn(newPlc);
                when(plcRepository.existsByName("newPlc")).thenReturn(true);
                when(plcRepository.existsByIpAddress("192.168.0.1")).thenReturn(false);

                assertThrows(PlcUniqueConstrainException.class,() -> plcService.createPlc(plcDto));
            }

            @Test @DisplayName("throws PlcUniqueConstrainViolationException with correct message when PLC has same name and ip as one of PLCs in database")
            void throwsExceptionWhenNameAnIpAreNotUnique(){
                PlcDto.Request.Create plcDto = PlcDto.Request.Create.builder().name("newPlc").ipAddress("192.168.0.1").build();
                Plc newPlc = Plc.builder().name("newPlc").ipAddress("192.168.0.1").build();
                PlcData plcData = PlcDataOpcua.builder()
                        .connectionStatus(ConnectionStatus.DISCONNECTED)
                        .build();

                when(plcConnector.connectPlc("192.168.0.1")).thenReturn(plcData);
                when(dtoMapper.toPlc(plcDto)).thenReturn(newPlc);
                when(plcRepository.existsByName("newPlc")).thenReturn(true);
                when(plcRepository.existsByIpAddress("192.168.0.1")).thenReturn(true);

                String message = assertThrows(PlcUniqueConstrainException.class,() -> plcService.createPlc(plcDto)).getMessage();
                assertThat(message).contains("IP address").contains("name");
            }
        }
    }

    @Nested @DisplayName("CONNECT ALL PLCS IN DATABASE")
    class ConnectAllPlcsInDatabase{
        @Test @DisplayName("For all plcs in database triggers connectPlc method")
        void forAllPlcsInDatabaseTriggersConnectPlc(){
            List<Plc> plcsInDatabase = List.of(Plc.builder().name("first plc").build(), Plc.builder().name("second plc").build());
            PlcData plcData = PlcDataOpcua.builder()
                    .connectionStatus(ConnectionStatus.DISCONNECTED)
                    .build();

            when(plcRepository.findAll()).thenReturn(plcsInDatabase);
            when(plcConnector.connectPlc(any())).thenReturn(plcData);

            plcService.connectAllPlcsInDatabase();

            verify(plcConnector,times(2)).connectPlc(any());
        }
    }

    @Nested @DisplayName("DELETE PLC BY ID")
    class DeletePlcById{
        @Nested @DisplayName("PLC EXISTS IN DATABASE")
        class PlcExistsInDatabase{

            @Test @DisplayName("deletes plc from database and disconnects it")
            void deletesPlcFromDatabase(){
                Plc plcInDb = Plc.builder().id(1L).ipAddress("192.168.0.1").build();
                when(plcRepository.findById(1L)).thenReturn(Optional.of(plcInDb));

                plcService.delete(1L);

                verify(plcRepository,Mockito.times(1)).deleteById(1L);
                verify(plcConnector, times(1)).disconnectPlc("192.168.0.1");
            }
        }

        @Test @DisplayName("throws PlcNotFoundException when PLC doesn't exist")
        void throwsPlcNotFoundExceptionWhenPlcDoesNotExist(){
            when(plcRepository.findById(1L)).thenReturn(Optional.empty());

            assertThrows(PlcNotFoundException.class,() -> plcService.delete(1L));
        }
    }

    @Nested @DisplayName("FIND BY ID")
    class FindById{
        @Test @DisplayName("throws PlcNotFoundException when PLC doesn't exist")
        void ifPlcIsNotInDbReturnsEmptyOptional(){
            when(plcRepository.findById(1L)).thenReturn(Optional.empty());

            assertThrows(PlcNotFoundException.class, () -> plcService.find(1L));
        }

        @Test @DisplayName("if PLC is  in DB returns proper plc DTO")
        void ifPlcIsInDbReturnsOptionalOfPlc(){
            Plc plcInDb = Plc.builder().id(1L).name("plcInDb").build();
            PlcDto.Response.Overview plcDtoToReturn = PlcDto.Response.Overview.builder().id(1L).name("plcInDb").build();

            when(plcRepository.findById(1L)).thenReturn(Optional.of(plcInDb));
            when(dtoMapper.toPlcDtoOverview(plcInDb)).thenReturn(plcDtoToReturn);

            assertThat(plcService.find(1L)).isEqualTo(plcDtoToReturn);
        }
    }


    @Nested @DisplayName("FIND ALL")
    class FindAll{
        @Test @DisplayName("returns list of plc DTOs which were found in DB")
        void triggersPlcRepository(){
            Plc plcInDb1 = Plc.builder().id(1L).build();
            Plc plcInDb2 = Plc.builder().id(2L).build();
            PlcDto.Response.Overview plcDtoToReturn1 = PlcDto.Response.Overview.builder().id(1L).build();
            PlcDto.Response.Overview plcDtoToReturn2 = PlcDto.Response.Overview.builder().id(2L).build();

            when(plcRepository.findAll()).thenReturn(List.of(plcInDb1, plcInDb2));
            when(dtoMapper.toPlcDtoOverview(plcInDb1)).thenReturn(plcDtoToReturn1);
            when(dtoMapper.toPlcDtoOverview(plcInDb2)).thenReturn(plcDtoToReturn2);

            assertThat(plcService.findAll()).containsExactly(plcDtoToReturn1, plcDtoToReturn2);
        }
    }

    @Nested @DisplayName("UPDATE PLC'S ATTRIBUTE(S) BY ID")
    class UpdatePlcsAttributeById{
        @Nested @DisplayName("PLC IS IN DB")
        class PlcIsInDb{

            @Test @DisplayName("updates plc and store it to db")
            void updatesPlcAndStoresItToDb(){
                Plc plcInDb = Plc.builder().id(1L).name("oldPlc").ipAddress("192.168.0.1").build();
                PlcDto.Request.Update plcDto = PlcDto.Request.Update.builder().name("newPlc").ipAddress("192.168.0.1").build();
                PlcDto.Response.Overview plcDtoToReturn = PlcDto.Response.Overview.builder().id(1L).name("newPlc").ipAddress("192.168.0.1").build();

                when(plcRepository.findByIdFetchAll(1L)).thenReturn(Optional.of(plcInDb));
                when(dtoMapper.toPlcDtoOverview(any())).thenReturn(plcDtoToReturn);

                assertThat(plcService.update(1L, plcDto)).isEqualTo(plcDtoToReturn);
                verify(plcRepository, times(1)).save(plcCaptor.capture());

                SoftAssertions softAssertions = new SoftAssertions();
                softAssertions.assertThat(plcCaptor.getValue().getName()).isEqualTo("newPlc");
                softAssertions.assertThat(plcCaptor.getValue().getIpAddress()).isEqualTo("192.168.0.1");
                softAssertions.assertThat(plcCaptor.getValue().getId()).isEqualTo(1L);
                softAssertions.assertAll();
            }


            @Test @DisplayName("throws exception when name or ip are not unique")
            void throwsExceptionWhenNameOrIpAreNotUnique(){
                PlcDto.Request.Update plcDto = PlcDto.Request.Update.builder().name("newPlc").ipAddress("192.168.0.1").build();

                when(plcRepository.existsByIpAddressIgnoringId("192.168.0.1", 1L)).thenReturn(false);
                when(plcRepository.existsByNameIgnoringId("newPlc",1L)).thenReturn(true);

                assertThrows(PlcUniqueConstrainException.class, () -> plcService.update(1L, plcDto));
            }

            @Test @DisplayName("reconnects PLC when IP address changes")
            void reconnectsPlcWhenIpAddressChanges(){
                Plc plcInDb = Plc.builder().id(1L).name("oldPlc").ipAddress("192.168.0.1").build();
                PlcDto.Request.Update plcDto = PlcDto.Request.Update.builder().name("oldPlc").ipAddress("192.168.0.2").build();

                when(plcRepository.findByIdFetchAll(1L)).thenReturn(Optional.of(plcInDb));
                when(plcConnector.connectPlc("192.168.0.2")).thenReturn(plcData);

                plcService.update(1L, plcDto);

                verify(plcConnector,times(1)).disconnectPlc("192.168.0.1");
                verify(plcConnector,times(1)).connectPlc("192.168.0.2");
            }
        }

        @Test @DisplayName("throws PlcNotFoundException when PLC is not in DB")
        void throwsExceptionWhenIsNotInDb(){
            PlcDto.Request.Update plcDto = PlcDto.Request.Update.builder().name("newPlc").ipAddress("192.168.0.1").build();

            when(plcRepository.existsByIpAddressIgnoringId("192.168.0.1", 1L)).thenReturn(false);
            when(plcRepository.existsByNameIgnoringId("newPlc",1L)).thenReturn(false);
            when(plcRepository.findByIdFetchAll(1L)).thenReturn(Optional.empty());

            assertThrows(PlcNotFoundException.class, () -> plcService.update(1L, plcDto));
        }
    }
}
