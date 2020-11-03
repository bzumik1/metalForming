package com.siemens.metal_forming.service;

import com.siemens.metal_forming.domain.ReferenceCurveCalculation;
import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.entity.log.CollisionPoint;
import com.siemens.metal_forming.entity.log.Log;
import com.siemens.metal_forming.entity.log.LogCreator;
import com.siemens.metal_forming.enumerated.ConnectionStatus;
import com.siemens.metal_forming.enumerated.StopReactionType;
import com.siemens.metal_forming.enumerated.ToolStatusType;
import com.siemens.metal_forming.exception.exceptions.OpcuaConnectionException;
import com.siemens.metal_forming.exception.exceptions.PlcNotFoundException;
import com.siemens.metal_forming.exception.exceptions.PlcUniqueConstrainException;
import com.siemens.metal_forming.opcua.OpcuaClient;
import com.siemens.metal_forming.opcua.OpcuaConnector;
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
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayName("<= PLC SERVICE SPECIFICATION =>")
class PlcServiceSpec {
    private PlcService plcService;
    @Mock
    private PlcRepository plcRepository;
    @Mock
    private OpcuaConnector opcuaConnector;
    @Mock
    private CurveValidationService curveValidationService;
    @Mock
    private LogService logService;
    @Mock
    private LogCreator logCreator;
    @Mock
    private ReferenceCurveCalculationService referenceCurveCalculationService;

    @Captor
    ArgumentCaptor<Log> logCaptor;

    @Captor
    ArgumentCaptor<Plc> plcCaptor;

    //VARIABLES USED IN TESTS
    private final Long idOfExistingPlc = 1L;
    private final Long idOfNonExistentPlc = 2L;

    private final String ipOfExistingPlc = "192.168.0.1";
    private final String ipOfNotExistentPlc = "192.168.0.2";

    private final int toolNumberOfExistingTool = 1;

    private final String nameOfExistingPlc = "nameOfExistingPlc";

    private Plc plcInDb;
    private Plc newPlc;

    @BeforeEach
    void initialize() {
        //INITIALIZE MOCKS
        plcService = spy(new PlcServiceImpl(plcRepository, opcuaConnector, curveValidationService, referenceCurveCalculationService, logService, logCreator));

        //INITIALIZE VARIABLES
        plcInDb = Mockito.spy(Plc.builder().ipAddress(ipOfExistingPlc).name(nameOfExistingPlc).build());
        plcInDb.addTool(Tool.builder().toolNumber(toolNumberOfExistingTool).id(1L).calculateReferenceCurve(true).numberOfReferenceCycles(1).build());
        plcInDb.setCurrentTool(toolNumberOfExistingTool);
        newPlc = Plc.builder().ipAddress(ipOfNotExistentPlc).build();

        //PLC REPOSITORY DEFAULT BEHAVIOUR
        when(plcRepository.save(any(Plc.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    static class SetBehaviour {
        static void correctlyConnectsOverOpcUa(OpcuaConnector opcuaConnector){
            OpcuaClient client = Mockito.mock(OpcuaClient.class);
            when(client.readSerialNumber()).thenReturn(CompletableFuture.completedFuture("SN"));
            when(client.readFirmwareNumber()).thenReturn(CompletableFuture.completedFuture("FW"));
            when(client.readToolNumber()).thenReturn(CompletableFuture.completedFuture(1));
            when(client.readToolName()).thenReturn(CompletableFuture.completedFuture("toolName"));
            when(client.readToolMaxSpeedOperation()).thenReturn(CompletableFuture.completedFuture(40));
            when(opcuaConnector.connectPlc(any(Plc.class))).thenReturn(client);
        }
    }

    @Nested @DisplayName("CHECK IF EXISTS OR THROWS EXCEPTION")
    class CheckIfExistsOrThrowsException{
        @Test @DisplayName("throws PlcNotFoundException if PLC does not exist in database")
        void throwsExceptionWhenPlcDoesNotExist(){
            when(plcRepository.existsById(idOfNonExistentPlc)).thenReturn(false);

            assertThrows(PlcNotFoundException.class,() -> plcService.checkIfExistsOrThrowException(idOfNonExistentPlc));
        }
    }

    @Nested @DisplayName("UPDATE PLC BY ID")
    class UpdatePlcById{
        @Test @DisplayName("throws PlcNotFoundException if PLC is not found in database")
        void ifPlcIsNotFoundReturnFalse(){
            when(plcRepository.findById(idOfNonExistentPlc)).thenReturn(Optional.empty());

            assertThrows(PlcNotFoundException.class,() -> plcService.replace(1L, newPlc));
        }

        @Nested @DisplayName("PLC EXISTS AND CONNECTION OVER OPC UA IS SUCCESSFUL")
        class ConnectionOverOpcUaIsSuccessful {
            @BeforeEach
            void initializeForConnectionOverOpcUaIsSuccessful(){
                SetBehaviour.correctlyConnectsOverOpcUa(opcuaConnector);
                when(plcRepository.findById(idOfExistingPlc)).thenReturn(Optional.of(plcInDb));
            }

            @Test @DisplayName("replaces id of updated PLC by the id inputted into the method")
            void idIsReplaced(){
                Plc updatedPlc = plcService.replace(idOfExistingPlc, newPlc);

                assertThat(updatedPlc.getId()).isEqualTo(idOfExistingPlc);
            }

            @Test @DisplayName("saves updated PLCto DB")
            void newPlcIsSavedToDb(){
                plcService.replace(idOfExistingPlc, newPlc);

                verify(plcRepository,Mockito.times(1)).save(newPlc);
            }

            @Test @DisplayName("updates client if ip of updated plc is different")
            void updatesClientWhenPlcIsUpdated(){
                plcService.replace(idOfExistingPlc, newPlc);

                verify(opcuaConnector,Mockito.times(1)).disconnectPlc(plcInDb);
                verify(opcuaConnector,Mockito.times(1)).connectPlc(newPlc);
            }

            @Test @DisplayName("sets PLC status to CONNECTED when OPC UA connection was established")
            void setsCorrectConnectionStatusWhenConnectionWasEstablished(){
                Plc updatedPlc = plcService.replace(idOfExistingPlc, newPlc);

                assertThat(updatedPlc.getConnection().getStatus()).isEqualTo(ConnectionStatus.CONNECTED);
            }


        }

        @Test @DisplayName("sets PLC status to DISCONNECTED when OPC UA connection could not be established")
        void setsCorrectConnectionStatusWhenConnectionCouldNotBeEstablished(){
            newPlc.markAsConnected();
            when(plcRepository.findById(idOfExistingPlc)).thenReturn(Optional.of(plcInDb));
            when(opcuaConnector.connectPlc(newPlc)).thenThrow(new OpcuaConnectionException());

            plcService.replace(idOfExistingPlc, newPlc);

            assertThat(newPlc.getConnection().getStatus()).isEqualTo(ConnectionStatus.DISCONNECTED);
        }
    }

    @Nested @DisplayName("CHANGE CURRENT TOOL")
    class ChangeCurrentTool{

        @Test @DisplayName("if plc with given ip address is not found return throws exception")
        void ifPlcIsNotFoundThrowsException(){
            //Mock
            when(plcRepository.findByIpAddress(ipOfNotExistentPlc)).thenReturn(Optional.empty());

            assertThrows(PlcNotFoundException.class, () -> plcService.changeCurrentTool(ipOfNotExistentPlc, toolNumberOfExistingTool));
        }

        @Nested @DisplayName("PLC IS IN DATABASE")
        class PlcIsInDatabase{
            @BeforeEach
            void initializeForPlcIsInDatabase(){
                when(plcRepository.findByIpAddress(ipOfExistingPlc)).thenReturn(Optional.of(plcInDb));
            }

            @Test @DisplayName("when tool exists in plc's tools than it is selected as current tool")
            void whenToolExistsInPlcsToolsThanItIsSelectedAsCurrentTool(){
                Mockito.reset(plcInDb);
                plcService.changeCurrentTool(ipOfExistingPlc, toolNumberOfExistingTool);

                verify(plcInDb, times(1)).setCurrentTool(toolNumberOfExistingTool);
            }

            @Test @DisplayName("calculation of reference curve of old tool is canceled if it was running")
            void cancelsReferenceCurveCalculationIfItWasRunning(){
                Mockito.reset(plcInDb);
                plcInDb.getCurrentTool().setCalculateReferenceCurve(true);

                when(referenceCurveCalculationService.getReferenceCurveCalculation(toolNumberOfExistingTool))
                        .thenReturn(Optional.of(new ReferenceCurveCalculation(2)));

                plcService.changeCurrentTool(ipOfExistingPlc, toolNumberOfExistingTool);

                verify(referenceCurveCalculationService, times(1)).removeCalculation(toolNumberOfExistingTool);
            }

            @Test @DisplayName("when tool does not exist in plc's tools then new is created")
            void whenToolDoesNotExistInPlcsToolsThenNewIsCreated(){
                Tool newAutodetectedTool = Tool.builder()
                        .plc(plcInDb)
                        .toolNumber(2)
                        .name("newTool")
                        .maxSpeedOperation(10)
                        .toolStatus(ToolStatusType.AUTODETECTED)
                        .automaticMonitoring(false)
                        .calculateReferenceCurve(false)
                        .build();


                OpcuaClient client = Mockito.mock(OpcuaClient.class);
                when(client.readToolNumber()).thenReturn(CompletableFuture.completedFuture(2));
                when(client.readToolName()).thenReturn(CompletableFuture.completedFuture("newTool"));
                when(client.readToolMaxSpeedOperation()).thenReturn(CompletableFuture.completedFuture(10));
                when(opcuaConnector.getClient(any(Plc.class))).thenReturn(client);

                plcService.changeCurrentTool(ipOfExistingPlc,2);

                verify(plcRepository, times(1)).save(plcCaptor.capture());
                assertThat(plcCaptor.getValue().getCurrentTool()).isEqualTo(newAutodetectedTool);
            }

            @Test @DisplayName("updates plc in database")
            void updatesPlcInDatabase(){
                plcService.changeCurrentTool(ipOfExistingPlc, toolNumberOfExistingTool);

                verify(plcRepository,Mockito.times(1)).save(plcInDb);
            }
        }


    }

    @Nested @DisplayName("PROCESS NEW CURVE")
    class ProcessNewCurve{
        @Test @DisplayName("when curve is invalid creates creates log with corresponding data and stops the press")
        void whenCurveIsInvalidCreatesLogWithCorrespondingDataAndStopsThePress(){
            Plc testPlc = Plc.builder()
                    .currentTool(Tool.builder()
                            .referenceCurve( Curve.builder()
                                    .build())
                            .automaticMonitoring(true)
                            .calculateReferenceCurve(false)
                            .stopReaction(StopReactionType.IMMEDIATE)
                            .build())
                    .build();
            Curve measuredCurve = Curve.builder().build();
            Set<CollisionPoint> collisionPoints = Set.of(new CollisionPoint(1.1f,1.1f));
            Log logToBeCreated = Log.builder().build();
            OpcuaClient client = Mockito.mock(OpcuaClient.class);


            when(plcRepository.findByIpAddress("192.168.0.1")).thenReturn(Optional.of(testPlc));
            when(curveValidationService.validate(testPlc.getCurrentTool().getReferenceCurve(),measuredCurve))
                    .thenReturn(collisionPoints);
            when(logCreator.create(testPlc,measuredCurve,collisionPoints)).thenReturn(logToBeCreated);
            when(opcuaConnector.getClient(testPlc)).thenReturn(client);

            plcService.processNewCurve("192.168.0.1", measuredCurve);

            verify(logService, times(1)).save(logCaptor.capture());
            verify(client, times(1)).immediateStop();

            assertThat(logCaptor.getValue()).isEqualTo(logToBeCreated);
        }

        @Test @DisplayName("when automatic monitoring is false does nothing")
        void whenAutomaticMonitoringIsFalseDoesNothing(){
            Plc testPlc = Plc.builder()
                    .currentTool(Tool.builder()
                            .referenceCurve( Curve.builder()
                                    .build())
                            .automaticMonitoring(false)
                            .calculateReferenceCurve(false)
                            .stopReaction(StopReactionType.IMMEDIATE)
                            .build())
                    .build();
            Curve measuredCurve = Curve.builder().build();

            when(plcRepository.findByIpAddress("192.168.0.1")).thenReturn(Optional.of(testPlc));

            plcService.processNewCurve("192.168.0.1", measuredCurve);

            verify(logService, times(0)).save(any());
            verify(curveValidationService, times(0)).validate(any(),any());
            verify(opcuaConnector, times(0)).getClient(any());
        }

        @Test @DisplayName("when curve is valid and calculation of reference curve is required then calculates reference curve")
        void calculatesReferenceCurveWhenNeeded(){
            Plc testPlc = Plc.builder()
                    .currentTool(Tool.builder()
                            .automaticMonitoring(false)
                            .id(1L)
                            .calculateReferenceCurve(true)
                            .numberOfReferenceCycles(10)
                            .build())
                    .build();
            Curve measuredCurve = Curve.builder().build();
            OpcuaClient client = Mockito.mock(OpcuaClient.class);


            when(plcRepository.findByIpAddress("192.168.0.1")).thenReturn(Optional.of(testPlc));
            when(referenceCurveCalculationService.getReferenceCurveCalculation(1L)).thenReturn(Optional.empty());

            plcService.processNewCurve("192.168.0.1", measuredCurve);

            verify(referenceCurveCalculationService, times(1)).calculate(1L, measuredCurve);
        }

        @Test @DisplayName("starts calculation of reference curve when it already doesn't exist")
        void startsCalculationOfReferenceCurveWhenItAlreadyDoesNotExist(){
            Mockito.reset(plcInDb);
            plcInDb.getCurrentTool().setCalculateReferenceCurve(true);
            plcInDb.getCurrentTool().setAutomaticMonitoring(false);
            plcInDb.getCurrentTool().setNumberOfReferenceCycles(1);
            plcInDb.getCurrentTool().setId(1L);
            Curve measuredCurve = Curve.builder().build();

            when(plcRepository.findByIpAddress(ipOfExistingPlc)).thenReturn(Optional.of(plcInDb));
            when(referenceCurveCalculationService.getReferenceCurveCalculation(1L)).thenReturn(Optional.empty());

            plcService.processNewCurve(ipOfExistingPlc,measuredCurve);

            verify(referenceCurveCalculationService, times(1)).addCalculation(1L,1);
        }
    }

    @Nested @DisplayName("CREATE PLC")
    class CreatePlc{
        @Nested @DisplayName("PLC IS CONNECTED")
        class PlcIsConnected{
            @BeforeEach
            void initializeForPlcIsConnected(){
                SetBehaviour.correctlyConnectsOverOpcUa(opcuaConnector);
            }

            @Test @DisplayName("stores PLC to database")
            void storesPlcInDatabase(){
                plcService.createPlc(newPlc);

                verify(plcRepository,Mockito.times(1)).save(newPlc);
            }

            @Test @DisplayName("connects PLC over opcua")
            void connectsPlcOverOpcua(){
                plcService.createPlc(newPlc);

                verify(opcuaConnector, Mockito.times(1)).connectPlc(newPlc);
            }

            @Test @DisplayName("sets PLC status to CONNECTED when connection was established")
            void setPlcStatusToConnected(){
                Plc createdPlc = plcService.createPlc(newPlc);

                assertThat(createdPlc.getConnection().getStatus()).isEqualTo(ConnectionStatus.CONNECTED);
            }

            @Test @DisplayName("adds new tool as autodetected to new plc")
            void addsNewToolAsAutodetectedToNewPlc(){
                Plc createdPlc = plcService.createPlc(newPlc);

                SoftAssertions softAssertions = new SoftAssertions();
                softAssertions.assertThat(createdPlc.getTools().size()).as("tool should be added").isEqualTo(1);
                softAssertions.assertThat(createdPlc.getCurrentTool()).as("tool should be selected as current tool").isNotNull();
                softAssertions.assertThat(createdPlc.getCurrentTool().getToolNumber()).as("tool should be created with correct number").isEqualTo(1);
                softAssertions.assertThat(createdPlc.getCurrentTool().getName()).as("tool should be created with correct name").isEqualTo("toolName");
                softAssertions.assertThat(createdPlc.getCurrentTool().getMaxSpeedOperation()).as("tool should be created with correct maxSpeedOperation").isEqualTo(40);
                softAssertions.assertThat(createdPlc.getCurrentTool().getToolStatus()).as("tool should be marked as autodetected").isEqualTo(ToolStatusType.AUTODETECTED);
                softAssertions.assertThat(createdPlc.getCurrentTool().getAutomaticMonitoring()).as("tool is created with automaticMonitoring false").isFalse();
                softAssertions.assertThat(createdPlc.getCurrentTool().getCalculateReferenceCurve()).as("tool should be created with calculationReferenceCurve false").isFalse();
                softAssertions.assertAll();
            }

            @Nested @DisplayName("UPDATES HARDWARE INFORMATION")
            class UpdatesHardwareInformation{
                @Test @DisplayName("updates serial number")
                void updatesSerialNumber(){
                    Plc createdPlc = plcService.createPlc(newPlc);

                    assertThat(createdPlc.getHardwareInformation().getSerialNumber()).isEqualTo("SN");
                }

                @Test @DisplayName("updates firmware number")
                void updatesFirmwareNumber(){
                    Plc createdPlc = plcService.createPlc(newPlc);

                    assertThat(createdPlc.getHardwareInformation().getFirmwareNumber()).isEqualTo("FW");
                }
            }
        }

        @Nested @DisplayName("PLC IS DISCONNECTED")
        class PlcIsDisconnected{
            @Test @DisplayName("mark plc as disconnected when the connection over OPC UA could not be established")
            void markPlcAsDisconnectedIfTheConnectionWasNotSuccessful(){
                Plc plcToBeConnected = Plc.builder().build();
                plcToBeConnected.markAsConnected();

                when(opcuaConnector.connectPlc(plcToBeConnected)).thenThrow(new OpcuaConnectionException());

                plcService.createPlc(plcToBeConnected);

                verify(plcRepository, times(1)).save(plcCaptor.capture());
                assertThat(plcToBeConnected.getConnection().getStatus()).isEqualTo(ConnectionStatus.DISCONNECTED);
            }
        }

        @Nested @DisplayName("UNIQUE VIOLATION")
        class UniqueViolation{
            @Test @DisplayName("throws PlcUniqueConstrainViolationException when PLC has same IP address as one of PLCs in database")
            void throwsExceptionWhenIpAddressIsNotUnique(){
                when(plcRepository.existsByIpAddress(ipOfExistingPlc)).thenReturn(true);

                assertThrows(PlcUniqueConstrainException.class,() -> plcService.createPlc(plcInDb));
            }

            @Test @DisplayName("throws PlcUniqueConstrainViolationException when PLC has same name as one of PLCs in database")
            void throwsExceptionWhenNameIsNotUnique(){
                when(plcRepository.existsByName(nameOfExistingPlc)).thenReturn(true);

                assertThrows(PlcUniqueConstrainException.class,() -> plcService.createPlc(plcInDb));
            }

            @Test @DisplayName("throws PlcUniqueConstrainViolationException with correct message when PLC has same name and ip as one of PLCs in database")
            void throwsExceptionWhenNameAnIpAreNotUnique(){
                when(plcRepository.existsByName(nameOfExistingPlc)).thenReturn(true);
                when(plcRepository.existsByIpAddress(ipOfExistingPlc)).thenReturn(true);

                String message = assertThrows(PlcUniqueConstrainException.class, () -> plcService.createPlc(plcInDb)).getMessage();
                assertThat(message).contains("IP address").contains("name");
            }
        }
    }

    @Nested @DisplayName("CONNECT PLC")
    class ConnectPlc{
        @Nested @DisplayName("PLC IS CONNECTED")
        class PlcIsConnected{
            @BeforeEach
            void initializeForPlcIsConnected(){
                SetBehaviour.correctlyConnectsOverOpcUa(opcuaConnector);
            }

            @Test @DisplayName("stores PLC to database")
            void storesPlcInDatabase(){
                plcService.connectPlc(newPlc);

                verify(plcRepository,Mockito.times(1)).save(newPlc);
            }

            @Test @DisplayName("connects PLC over opcua")
            void connectsPlcOverOpcua(){
                plcService.connectPlc(newPlc);

                verify(opcuaConnector, Mockito.times(1)).connectPlc(newPlc);
            }

            @Test @DisplayName("sets PLC status to CONNECTED when connection was established")
            void setPlcStatusToConnected(){
                Plc createdPlc = plcService.connectPlc(newPlc);

                assertThat(createdPlc.getConnection().getStatus()).isEqualTo(ConnectionStatus.CONNECTED);
            }

            @Test @DisplayName("adds new tool as autodetected to new plc")
            void addsNewToolAsAutodetectedToNewPlc(){
                Plc createdPlc = plcService.connectPlc(newPlc);

                SoftAssertions softAssertions = new SoftAssertions();
                softAssertions.assertThat(createdPlc.getTools().size()).as("tool should be added").isEqualTo(1);
                softAssertions.assertThat(createdPlc.getCurrentTool()).as("tool should be selected as current tool").isNotNull();
                softAssertions.assertThat(createdPlc.getCurrentTool().getToolNumber()).as("tool should be created with correct number").isEqualTo(1);
                softAssertions.assertThat(createdPlc.getCurrentTool().getName()).as("tool should be created with correct name").isEqualTo("toolName");
                softAssertions.assertThat(createdPlc.getCurrentTool().getMaxSpeedOperation()).as("tool should be created with correct maxSpeedOperation").isEqualTo(40);
                softAssertions.assertThat(createdPlc.getCurrentTool().getToolStatus()).as("tool should be marked as autodetected").isEqualTo(ToolStatusType.AUTODETECTED);
                softAssertions.assertThat(createdPlc.getCurrentTool().getAutomaticMonitoring()).as("tool is created with automaticMonitoring false").isFalse();
                softAssertions.assertThat(createdPlc.getCurrentTool().getCalculateReferenceCurve()).as("tool should be created with calculationReferenceCurve false").isFalse();
                softAssertions.assertAll();
            }

            @Nested @DisplayName("UPDATES HARDWARE INFORMATION")
            class UpdatesHardwareInformation{
                @Test @DisplayName("updates serial number")
                void updatesSerialNumber(){
                    Plc createdPlc = plcService.connectPlc(newPlc);

                    assertThat(createdPlc.getHardwareInformation().getSerialNumber()).isEqualTo("SN");
                }

                @Test @DisplayName("updates firmware number")
                void updatesFirmwareNumber(){
                    Plc createdPlc = plcService.connectPlc(newPlc);

                    assertThat(createdPlc.getHardwareInformation().getFirmwareNumber()).isEqualTo("FW");
                }
            }
        }

        @Nested @DisplayName("PLC IS DISCONNECTED")
        class PlcIsDisconnected{
            @Test @DisplayName("mark plc as disconnected when the connection over OPC UA could not be established")
            void markPlcAsDisconnectedIfTheConnectionWasNotSuccessful(){
                Plc plcToBeConnected = Plc.builder().build();
                plcToBeConnected.markAsConnected();

                when(opcuaConnector.connectPlc(plcToBeConnected)).thenThrow(new OpcuaConnectionException());

                plcService.connectPlc(plcToBeConnected);

                verify(plcRepository, times(1)).save(plcCaptor.capture());
                assertThat(plcToBeConnected.getConnection().getStatus()).isEqualTo(ConnectionStatus.DISCONNECTED);
            }
        }
    }

    @Nested @DisplayName("CONNECT ALL PLCS IN DATABASE")
    class ConnectAllPlcsInDatabase{
        @Test @DisplayName("For all plcs in database triggers connectPlc method")
        void forAllPlcsInDatabaseTriggersConnectPlc(){
            List<Plc> plcsInDatabase = List.of(Plc.builder().name("first plc").build(), Plc.builder().name("second plc").build());

            when(plcRepository.findAll()).thenReturn(plcsInDatabase);
            when(opcuaConnector.connectPlc(any())).thenThrow(new OpcuaConnectionException());

            plcService.connectAllPlcsInDatabase();

            verify(plcService,times(2)).connectPlc(any(Plc.class));
        }
    }

    @Nested @DisplayName("DELETE PLC BY ID")
    class DeletePlcById{
        @Nested @DisplayName("PLC EXISTS IN DATABASE")
        class PlcExistsInDatabase{
            @BeforeEach
            void initializeForPlcExistsInDatabase(){
                when(plcRepository.findById(idOfExistingPlc)).thenReturn(Optional.of(plcInDb));
                when(plcRepository.existsById(idOfExistingPlc)).thenReturn(true);
            }
            @Test @DisplayName("deletes plc from database when exists")
            void deletesPlcFromDatabase(){
                plcService.delete(idOfExistingPlc);

                verify(plcRepository,Mockito.times(1)).deleteById(idOfExistingPlc);
            }

            @Test @DisplayName("disconnects PLC from OPC UA when PLC exists")
            void disconnectsPlcFromOpcuaWhenPlcExists(){
                plcService.delete(idOfExistingPlc);

                verify(opcuaConnector,Mockito.times(1)).disconnectPlc(plcInDb);
            }
        }

        @Test @DisplayName("throws PlcNotFoundException when PLC doesn't exist")
        void throwsPlcNotFoundExceptionWhenPlcDoesNotExist(){
            when(plcRepository.existsById(idOfNonExistentPlc)).thenReturn(false);
            when(plcRepository.findById(idOfNonExistentPlc)).thenReturn(Optional.empty());

            assertThrows(PlcNotFoundException.class,() -> plcService.delete(idOfNonExistentPlc));
        }




    }

    @Nested @DisplayName("FIND BY ID")
    class FindById{
        @Test @DisplayName("if PLC is not found in DB returns empty optional")
        void ifPlcIsNotInDbReturnsEmptyOptional(){
            when(plcRepository.findById(idOfNonExistentPlc)).thenReturn(Optional.empty());

            assertThat(plcService.find(idOfNonExistentPlc)).isEmpty();

        }

        @Test @DisplayName("if PLC is  in DB returns optional of plc")
        void ifPlcIsInDbReturnsOptionalOfPlc(){
            when(plcRepository.findById(idOfExistingPlc)).thenReturn(Optional.of(plcInDb));

            Optional<Plc> foundPlc = plcService.find(1L);
            assertThat(foundPlc).isNotEmpty();
            assertThat(foundPlc.get()).isEqualTo(plcInDb);
        }
    }

    @Nested @DisplayName("FIND BY IP ADDRESS")
    class FindByIpAddress{
        @Test @DisplayName("if PLC is not found in DB returns empty optional")
        void ifPlcIsNotInDbReturnsEmptyOptional(){
            when(plcRepository.findByIpAddress(ipOfNotExistentPlc)).thenReturn(Optional.empty());

            assertThat(plcService.find(ipOfNotExistentPlc)).isEmpty();
        }

        @Test @DisplayName("if PLC is  in DB returns optional of plc")
        void ifPlcIsInDbReturnsOptionalOfPlc(){
            when(plcRepository.findByIpAddress(ipOfExistingPlc)).thenReturn(Optional.of(plcInDb));

            Optional<Plc> foundPlc = plcService.find(ipOfExistingPlc);
            assertThat(foundPlc).isNotEmpty();
            assertThat(foundPlc.get()).isEqualTo(plcInDb);
        }
    }

    @Nested @DisplayName("FIND ALL")
    class FindAll{
        @Test @DisplayName("triggers plcRepository.findAll()")
        void triggersPlcRepository(){
            plcService.findAll();

            verify(plcRepository,Mockito.times(1)).findAll();
        }
    }

    @Nested @DisplayName("UPDATE PLC'S ATTRIBUTE(S) BY IP ADDRESS")
    class UpdatePlcsAttributeByIpAddress{
        @Nested @DisplayName("PLC IS IN DB")
        class PlcIsInDb{
            ArgumentCaptor<Plc> plcCaptor;
            @BeforeEach
            void initializeForPlcIsInDb(){
                when(plcRepository.findByIpAddress(ipOfExistingPlc)).thenReturn(Optional.of(plcInDb));

                plcCaptor = ArgumentCaptor.forClass(Plc.class);
            }

            @Test @DisplayName("updates one attribute (connection status) of PLC")
            void updatesOneAttribute(){
                plcService.update(ipOfExistingPlc, plc -> plc.setConnectionStatus(ConnectionStatus.CONNECTED));
                verify(plcRepository).save(plcCaptor.capture());

                assertThat(plcCaptor.getValue().getConnection().getStatus()).isEqualTo(ConnectionStatus.CONNECTED);
            }

            @Test @DisplayName("updates multiple attributes (name, connection) of PLC")
            void updatesMultipleAttributes(){

                plcService.update(ipOfExistingPlc, plc -> {
                    plc.setName("newName");
                    plc.markAsConnected();
                });

                verify(plcRepository).save(plcCaptor.capture());

                SoftAssertions softAssertions = new SoftAssertions();
                softAssertions.assertThat(plcCaptor.getValue().getName()).isEqualTo("newName");
                softAssertions.assertThat(plcCaptor.getValue().getConnection().getStatus()).isEqualTo(ConnectionStatus.CONNECTED);
                softAssertions.assertAll();
            }

            @Test @DisplayName("stores updated PLC in DB")
            void storesUpdatedPlcInDb(){
                plcService.update(ipOfExistingPlc, plc -> {});

                verify(plcRepository,Mockito.times(1)).save(any(Plc.class));
            }

            @Test @DisplayName("returns updated PLC")
            void returnsUpdatedPlc(){
                Plc updatedPlc = plcService.update(ipOfExistingPlc, plc -> plc.setName("newName"));

                SoftAssertions softAssertions = new SoftAssertions();
                softAssertions.assertThat(updatedPlc.getName()).isEqualTo("newName");
                softAssertions.assertThat(updatedPlc.getIpAddress()).isEqualTo(plcInDb.getIpAddress());
                softAssertions.assertThat(updatedPlc.getId()).isEqualTo(plcInDb.getId());
                softAssertions.assertAll();
            }

            @Test @DisplayName("reconnects PLC when IP address changes")
            void reconnectsPlcWhenIpAddressChanges(){
                SetBehaviour.correctlyConnectsOverOpcUa(opcuaConnector);

                plcService.update(ipOfExistingPlc, plc -> plc.setIpAddress("192.168.0.2"));

                verify(opcuaConnector,Mockito.times(1)).disconnectPlc(plcCaptor.capture());
                assertThat(plcCaptor.getValue().getIpAddress()).isEqualTo("192.168.0.1");
                verify(opcuaConnector,Mockito.times(1)).connectPlc(plcCaptor.capture());
                assertThat(plcCaptor.getValue().getIpAddress()).isEqualTo("192.168.0.2");
            }
        }

        @Test @DisplayName("throws PlcNotFoundException when PLC is not in DB")
        void throwsExceptionWhenIsNotInDb(){
            when(plcRepository.findByIpAddress(ipOfNotExistentPlc)).thenReturn(Optional.empty());
            assertThrows(PlcNotFoundException.class, () -> plcService.update(ipOfNotExistentPlc, plc -> {}));
        }
    }

    @Nested @DisplayName("UPDATE PLC'S ATTRIBUTE(S) BY ID")
    class UpdatePlcsAttributeById{
        @Nested @DisplayName("PLC IS IN DB")
        class PlcIsInDb{
            ArgumentCaptor<Plc> plcCaptor;
            @BeforeEach
            void initializeForPlcIsInDb(){
                when(plcRepository.findById(idOfExistingPlc)).thenReturn(Optional.of(plcInDb));

                plcCaptor = ArgumentCaptor.forClass(Plc.class);
            }

            @Test @DisplayName("updates one attribute (connection status) of PLC")
            void updatesOneAttribute(){
                plcService.update(idOfExistingPlc, plc -> plc.setConnectionStatus(ConnectionStatus.CONNECTED));

                verify(plcRepository).save(plcCaptor.capture());

                assertThat(plcCaptor.getValue().getConnection().getStatus()).isEqualTo(ConnectionStatus.CONNECTED);
            }

            @Test @DisplayName("updates multiple attributes (name, connection) of PLC")
            void updatesMultipleAttributes(){

                plcService.update(idOfExistingPlc, plc -> {
                    plc.setName("newName");
                    plc.markAsConnected();
                });

                verify(plcRepository).save(plcCaptor.capture());

                SoftAssertions softAssertions = new SoftAssertions();
                softAssertions.assertThat(plcCaptor.getValue().getName()).isEqualTo("newName");
                softAssertions.assertThat(plcCaptor.getValue().getConnection().getStatus()).isEqualTo(ConnectionStatus.CONNECTED);
                softAssertions.assertAll();
            }

            @Test @DisplayName("stores updated PLC in DB")
            void storesUpdatedPlcInDb(){
                plcService.update(idOfExistingPlc, plc -> {});

                verify(plcRepository,Mockito.times(1)).save(plcInDb);
            }

            @Test @DisplayName("returns updated PLC")
            void returnsUpdatedPlc(){
                Plc updatedPlc = plcService.update(idOfExistingPlc, plc -> plc.setName("newName"));

                SoftAssertions softAssertions = new SoftAssertions();
                softAssertions.assertThat(updatedPlc.getName()).isEqualTo("newName");
                softAssertions.assertThat(updatedPlc.getIpAddress()).isEqualTo(plcInDb.getIpAddress());
                softAssertions.assertThat(updatedPlc.getId()).isEqualTo(plcInDb.getId());
                softAssertions.assertAll();
            }

            @Test @DisplayName("reconnects PLC when IP address changes")
            void reconnectsPlcWhenIpAddressChanges(){
                SetBehaviour.correctlyConnectsOverOpcUa(opcuaConnector);

                plcService.update(idOfExistingPlc, plc -> plc.setIpAddress("192.168.0.2"));

                verify(opcuaConnector,Mockito.times(1)).disconnectPlc(plcCaptor.capture());
                assertThat(plcCaptor.getValue().getIpAddress()).isEqualTo("192.168.0.1");
                verify(opcuaConnector,Mockito.times(1)).connectPlc(plcCaptor.capture());
                assertThat(plcCaptor.getValue().getIpAddress()).isEqualTo("192.168.0.2");

            }
        }

        @Test @DisplayName("throws PlcNotFoundException when PLC is not in DB")
        void throwsExceptionWhenIsNotInDb(){
            when(plcRepository.findById(idOfNonExistentPlc)).thenReturn(Optional.empty());
            assertThrows(PlcNotFoundException.class, () -> plcService.update(idOfNonExistentPlc, plc -> {}));
        }
    }
}
