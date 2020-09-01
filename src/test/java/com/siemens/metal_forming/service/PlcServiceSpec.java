package com.siemens.metal_forming.service;

import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.enumerated.ConnectionStatus;
import com.siemens.metal_forming.exception.exceptions.OpcuaConnectionException;
import com.siemens.metal_forming.exception.exceptions.PlcNotFoundException;
import com.siemens.metal_forming.exception.exceptions.PlcUniqueConstrainException;
import com.siemens.metal_forming.opcua.OpcuaClient;
import com.siemens.metal_forming.opcua.OpcuaConnector;
import com.siemens.metal_forming.repository.PlcRepository;
import com.siemens.metal_forming.service.impl.PlcServiceImpl;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("<= PLC SERVICE SPECIFICATION =>")
class PlcServiceSpec {
    private PlcService plcService;
    private PlcRepository plcRepository;
    private OpcuaConnector opcuaConnector;

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
        plcRepository = Mockito.mock(PlcRepository.class);
        opcuaConnector = Mockito.mock(OpcuaConnector.class);
        plcService = new PlcServiceImpl(plcRepository, opcuaConnector);

        //INITIALIZE VARIABLES
        plcInDb = Mockito.spy(Plc.builder().ipAddress(ipOfExistingPlc).name(nameOfExistingPlc).build());
        plcInDb.addTool(Tool.builder().toolNumber(toolNumberOfExistingTool).build());
        newPlc = Plc.builder().ipAddress(ipOfNotExistentPlc).build();

        //PLC REPOSITORY DEFAULT BEHAVIOUR
        Mockito.when(plcRepository.save(any(Plc.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    static class SetBehaviour {
        static void correctlyConnectsOverOpcUa(OpcuaConnector opcuaConnector){
            OpcuaClient client = Mockito.mock(OpcuaClient.class);
            Mockito.when(client.readSerialNumber()).thenReturn(CompletableFuture.completedFuture("SN"));
            Mockito.when(client.readFirmwareNumber()).thenReturn(CompletableFuture.completedFuture("FW"));
            Mockito.when(opcuaConnector.connectPlc(any(Plc.class))).thenReturn(client);
        }
    }

    @Nested @DisplayName("CHECK IF EXISTS OR THROWS EXCEPTION")
    class CheckIfExistsOrThrowsException{
        @Test @DisplayName("throws PlcNotFoundException if PLC does not exist in database")
        void throwsExceptionWhenPlcDoesNotExist(){
            Mockito.when(plcRepository.existsById(idOfNonExistentPlc)).thenReturn(false);

            assertThrows(PlcNotFoundException.class,() -> plcService.checkIfExistsOrThrowException(idOfNonExistentPlc));
        }
    }

    @Nested @DisplayName("UPDATE PLC BY ID")
    class UpdatePlcById{
        @Test @DisplayName("throws PlcNotFoundException if PLC is not found in database")
        void ifPlcIsNotFoundReturnFalse(){
            Mockito.when(plcRepository.findById(idOfNonExistentPlc)).thenReturn(Optional.empty());

            assertThrows(PlcNotFoundException.class,() -> plcService.replace(1L, newPlc));
        }

        @Nested @DisplayName("PLC EXISTS AND CONNECTION OVER OPC UA IS SUCCESSFUL")
        class ConnectionOverOpcUaIsSuccessful {
            @BeforeEach
            void initializeForConnectionOverOpcUaIsSuccessful(){
                SetBehaviour.correctlyConnectsOverOpcUa(opcuaConnector);
                Mockito.when(plcRepository.findById(idOfExistingPlc)).thenReturn(Optional.of(plcInDb));
            }

            @Test @DisplayName("replaces id of updated PLC by the id inputted into the method")
            void idIsReplaced(){
                Plc updatedPlc = plcService.replace(idOfExistingPlc, newPlc);

                assertThat(updatedPlc.getId()).isEqualTo(idOfExistingPlc);
            }

            @Test @DisplayName("saves updated PLCto DB")
            void newPlcIsSavedToDb(){
                plcService.replace(idOfExistingPlc, newPlc);

                Mockito.verify(plcRepository,Mockito.times(1)).save(newPlc);
            }

            @Test @DisplayName("updates client if ip of updated plc is different")
            void updatesClientWhenPlcIsUpdated(){
                plcService.replace(idOfExistingPlc, newPlc);

                Mockito.verify(opcuaConnector,Mockito.times(1)).disconnectPlc(plcInDb);
                Mockito.verify(opcuaConnector,Mockito.times(1)).connectPlc(newPlc);
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
            Mockito.when(plcRepository.findById(idOfExistingPlc)).thenReturn(Optional.of(plcInDb));
            Mockito.when(opcuaConnector.connectPlc(newPlc)).thenThrow(new OpcuaConnectionException());

            plcService.replace(idOfExistingPlc, newPlc);

            assertThat(newPlc.getConnection().getStatus()).isEqualTo(ConnectionStatus.DISCONNECTED);
        }
    }

    @Nested @DisplayName("CHANGE CURRENT TOOL")
    class ChangeCurrentTool{

        @Test @DisplayName("if plc with given ip address is not found return throws exception")
        void ifPlcIsNotFoundThrowsException(){
            //Mock
            Mockito.when(plcRepository.findByIpAddress(ipOfNotExistentPlc)).thenReturn(Optional.empty());

            assertThrows(PlcNotFoundException.class, () -> plcService.changeCurrentTool(ipOfNotExistentPlc, toolNumberOfExistingTool));
        }

        @Nested @DisplayName("PLC IS IN DATABASE")
        class PlcIsInDatabase{
            @BeforeEach
            void initializeForPlcIsInDatabase(){
                Mockito.when(plcRepository.findByIpAddress(ipOfExistingPlc)).thenReturn(Optional.of(plcInDb));
            }

            @Test @DisplayName("set new currentTool")
            void setNewCurrentTool(){
                plcService.changeCurrentTool(ipOfExistingPlc, toolNumberOfExistingTool);

                Mockito.verify(plcInDb,Mockito.times(1)).setCurrentTool(toolNumberOfExistingTool);
            }

            @Test @DisplayName("updates plc in database")
            void updatesPlcInDatabase(){
                plcService.changeCurrentTool(ipOfExistingPlc, toolNumberOfExistingTool);

                Mockito.verify(plcRepository,Mockito.times(1)).save(plcInDb);
            }
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
                plcService.create(newPlc);

                Mockito.verify(plcRepository,Mockito.times(1)).save(newPlc);
            }

            @Test @DisplayName("connects PLC over opcua")
            void connectsPlcOverOpcua(){
                plcService.create(newPlc);

                Mockito.verify(opcuaConnector, Mockito.times(1)).connectPlc(newPlc);
            }

            @Test @DisplayName("sets PLC status to CONNECTED when connection was established")
            void setPlcStatusToConnected(){
                Plc createdPlc = plcService.create(newPlc);

                assertThat(createdPlc.getConnection().getStatus()).isEqualTo(ConnectionStatus.CONNECTED);
            }

            @Nested @DisplayName("UPDATES HARDWARE INFORMATION")
            class UpdatesHardwareInformation{
                @Test @DisplayName("updates serial number")
                void updatesSerialNumber(){
                    Plc createdPlc = plcService.create(newPlc);

                    assertThat(createdPlc.getHardwareInformation().getSerialNumber()).isEqualTo("SN");
                }

                @Test @DisplayName("updates firmware number")
                void updatesFirmwareNumber(){
                    Plc createdPlc = plcService.create(newPlc);

                    assertThat(createdPlc.getHardwareInformation().getFirmwareNumber()).isEqualTo("FW");
                }
            }
        }

        @Nested @DisplayName("UNIQUE VIOLATION")
        class UniqueViolation{
            @Test @DisplayName("throws PlcUniqueConstrainViolationException when PLC has same IP address as one of PLCs in database")
            void throwsExceptionWhenIpAddressIsNotUnique(){
                Mockito.when(plcRepository.existsByIpAddress(ipOfExistingPlc)).thenReturn(true);

                assertThrows(PlcUniqueConstrainException.class,() -> plcService.create(plcInDb));
            }

            @Test @DisplayName("throws PlcUniqueConstrainViolationException when PLC has same name as one of PLCs in database")
            void throwsExceptionWhenNameIsNotUnique(){
                Mockito.when(plcRepository.existsByName(nameOfExistingPlc)).thenReturn(true);

                assertThrows(PlcUniqueConstrainException.class,() -> plcService.create(plcInDb));
            }

            @Test @DisplayName("throws PlcUniqueConstrainViolationException with correct message when PLC has same name and ip as one of PLCs in database")
            void throwsExceptionWhenNameAnIpAreNotUnique(){
                Mockito.when(plcRepository.existsByName(nameOfExistingPlc)).thenReturn(true);
                Mockito.when(plcRepository.existsByIpAddress(ipOfExistingPlc)).thenReturn(true);

                String message = assertThrows(PlcUniqueConstrainException.class, () -> plcService.create(plcInDb)).getMessage();
                assertThat(message).contains("IP address").contains("name");
            }
        }

        @Test @DisplayName("sets PLC status to DISCONNECTED when connection was not established")
        void setPlcStatusToDisconnected(){
            Mockito.when(opcuaConnector.connectPlc(newPlc)).thenThrow(new OpcuaConnectionException());

            plcService.create(newPlc);

            assertThat(newPlc.getConnection().getStatus()).isEqualTo(ConnectionStatus.DISCONNECTED);
        }
    }

    @Nested @DisplayName("DELETE PLC BY ID")
    class DeletePlcById{
        @Nested @DisplayName("PLC EXISTS IN DATABASE")
        class PlcExistsInDatabase{
            @BeforeEach
            void initializeForPlcExistsInDatabase(){
                Mockito.when(plcRepository.findById(idOfExistingPlc)).thenReturn(Optional.of(plcInDb));
                Mockito.when(plcRepository.existsById(idOfExistingPlc)).thenReturn(true);
            }
            @Test @DisplayName("deletes plc from database when exists")
            void deletesPlcFromDatabase(){
                plcService.delete(idOfExistingPlc);

                Mockito.verify(plcRepository,Mockito.times(1)).deleteById(idOfExistingPlc);
            }

            @Test @DisplayName("disconnects PLC from OPC UA when PLC exists")
            void disconnectsPlcFromOpcuaWhenPlcExists(){
                plcService.delete(idOfExistingPlc);

                Mockito.verify(opcuaConnector,Mockito.times(1)).disconnectPlc(plcInDb);
            }
        }

        @Test @DisplayName("throws PlcNotFoundException when PLC doesn't exist")
        void throwsPlcNotFoundExceptionWhenPlcDoesNotExist(){
            Mockito.when(plcRepository.existsById(idOfNonExistentPlc)).thenReturn(false);
            Mockito.when(plcRepository.findById(idOfNonExistentPlc)).thenReturn(Optional.empty());

            assertThrows(PlcNotFoundException.class,() -> plcService.delete(idOfNonExistentPlc));
        }




    }

    @Nested @DisplayName("FIND BY ID")
    class FindById{
        @Test @DisplayName("if PLC is not found in DB returns empty optional")
        void ifPlcIsNotInDbReturnsEmptyOptional(){
            Mockito.when(plcRepository.findById(idOfNonExistentPlc)).thenReturn(Optional.empty());

            assertThat(plcService.find(idOfNonExistentPlc)).isEmpty();

        }

        @Test @DisplayName("if PLC is  in DB returns optional of plc")
        void ifPlcIsInDbReturnsOptionalOfPlc(){
            Mockito.when(plcRepository.findById(idOfExistingPlc)).thenReturn(Optional.of(plcInDb));

            Optional<Plc> foundPlc = plcService.find(1L);
            assertThat(foundPlc).isNotEmpty();
            assertThat(foundPlc.get()).isEqualTo(plcInDb);
        }
    }

    @Nested @DisplayName("FIND BY IP ADDRESS")
    class FindByIpAddress{
        @Test @DisplayName("if PLC is not found in DB returns empty optional")
        void ifPlcIsNotInDbReturnsEmptyOptional(){
            Mockito.when(plcRepository.findByIpAddress(ipOfNotExistentPlc)).thenReturn(Optional.empty());

            assertThat(plcService.find(ipOfNotExistentPlc)).isEmpty();
        }

        @Test @DisplayName("if PLC is  in DB returns optional of plc")
        void ifPlcIsInDbReturnsOptionalOfPlc(){
            Mockito.when(plcRepository.findByIpAddress(ipOfExistingPlc)).thenReturn(Optional.of(plcInDb));

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

            Mockito.verify(plcRepository,Mockito.times(1)).findAll();
        }
    }

    @Nested @DisplayName("UPDATE PLC'S ATTRIBUTE(S) BY IP ADDRESS")
    class UpdatePlcsAttributeByIpAddress{
        @Nested @DisplayName("PLC IS IN DB")
        class PlcIsInDb{
            ArgumentCaptor<Plc> plcCaptor;
            @BeforeEach
            void initializeForPlcIsInDb(){
                Mockito.when(plcRepository.findByIpAddress(ipOfExistingPlc)).thenReturn(Optional.of(plcInDb));

                plcCaptor = ArgumentCaptor.forClass(Plc.class);
            }

            @Test @DisplayName("updates one attribute (connection status) of PLC")
            void updatesOneAttribute(){
                plcService.update(ipOfExistingPlc, plc -> plc.setConnectionStatus(ConnectionStatus.CONNECTED));
                Mockito.verify(plcRepository).save(plcCaptor.capture());

                assertThat(plcCaptor.getValue().getConnection().getStatus()).isEqualTo(ConnectionStatus.CONNECTED);
            }

            @Test @DisplayName("updates multiple attributes (name, connection) of PLC")
            void updatesMultipleAttributes(){

                plcService.update(ipOfExistingPlc, plc -> {
                    plc.setName("newName");
                    plc.markAsConnected();
                });

                Mockito.verify(plcRepository).save(plcCaptor.capture());

                SoftAssertions softAssertions = new SoftAssertions();
                softAssertions.assertThat(plcCaptor.getValue().getName()).isEqualTo("newName");
                softAssertions.assertThat(plcCaptor.getValue().getConnection().getStatus()).isEqualTo(ConnectionStatus.CONNECTED);
                softAssertions.assertAll();
            }

            @Test @DisplayName("stores updated PLC in DB")
            void storesUpdatedPlcInDb(){
                plcService.update(ipOfExistingPlc, plc -> {});

                Mockito.verify(plcRepository,Mockito.times(1)).save(any(Plc.class));
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

                Mockito.verify(opcuaConnector,Mockito.times(1)).disconnectPlc(plcCaptor.capture());
                assertThat(plcCaptor.getValue().getIpAddress()).isEqualTo("192.168.0.1");
                Mockito.verify(opcuaConnector,Mockito.times(1)).connectPlc(plcCaptor.capture());
                assertThat(plcCaptor.getValue().getIpAddress()).isEqualTo("192.168.0.2");
            }
        }

        @Test @DisplayName("throws PlcNotFoundException when PLC is not in DB")
        void throwsExceptionWhenIsNotInDb(){
            Mockito.when(plcRepository.findByIpAddress(ipOfNotExistentPlc)).thenReturn(Optional.empty());
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
                Mockito.when(plcRepository.findById(idOfExistingPlc)).thenReturn(Optional.of(plcInDb));

                plcCaptor = ArgumentCaptor.forClass(Plc.class);
            }

            @Test @DisplayName("updates one attribute (connection status) of PLC")
            void updatesOneAttribute(){
                plcService.update(idOfExistingPlc, plc -> plc.setConnectionStatus(ConnectionStatus.CONNECTED));

                Mockito.verify(plcRepository).save(plcCaptor.capture());

                assertThat(plcCaptor.getValue().getConnection().getStatus()).isEqualTo(ConnectionStatus.CONNECTED);
            }

            @Test @DisplayName("updates multiple attributes (name, connection) of PLC")
            void updatesMultipleAttributes(){

                plcService.update(idOfExistingPlc, plc -> {
                    plc.setName("newName");
                    plc.markAsConnected();
                });

                Mockito.verify(plcRepository).save(plcCaptor.capture());

                SoftAssertions softAssertions = new SoftAssertions();
                softAssertions.assertThat(plcCaptor.getValue().getName()).isEqualTo("newName");
                softAssertions.assertThat(plcCaptor.getValue().getConnection().getStatus()).isEqualTo(ConnectionStatus.CONNECTED);
                softAssertions.assertAll();
            }

            @Test @DisplayName("stores updated PLC in DB")
            void storesUpdatedPlcInDb(){
                plcService.update(idOfExistingPlc, plc -> {});

                Mockito.verify(plcRepository,Mockito.times(1)).save(plcInDb);
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

                Mockito.verify(opcuaConnector,Mockito.times(1)).disconnectPlc(plcCaptor.capture());
                assertThat(plcCaptor.getValue().getIpAddress()).isEqualTo("192.168.0.1");
                Mockito.verify(opcuaConnector,Mockito.times(1)).connectPlc(plcCaptor.capture());
                assertThat(plcCaptor.getValue().getIpAddress()).isEqualTo("192.168.0.2");

            }
        }

        @Test @DisplayName("throws PlcNotFoundException when PLC is not in DB")
        void throwsExceptionWhenIsNotInDb(){
            Mockito.when(plcRepository.findById(idOfNonExistentPlc)).thenReturn(Optional.empty());
            assertThrows(PlcNotFoundException.class, () -> plcService.update(idOfNonExistentPlc, plc -> {}));
        }
    }
}
