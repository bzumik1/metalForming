package com.siemens.metal_forming.service;

import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.enumerated.ConnectionStatus;
import com.siemens.metal_forming.exception.exceptions.OpcuaConnectionException;
import com.siemens.metal_forming.exception.exceptions.PlcNotFoundException;
import com.siemens.metal_forming.exception.exceptions.PlcUniqueConstrainException;
import com.siemens.metal_forming.opcua.OpcuaConnector;
import com.siemens.metal_forming.repository.PlcRepository;
import com.siemens.metal_forming.service.impl.PlcServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("<= PLC SERVICE SPECIFICATION =>")
class PlcServiceSpec {
    private PlcService plcService;
    private PlcRepository plcRepository;
    private OpcuaConnector opcuaConnector;

    @BeforeEach
    void initialize() {
        plcRepository = Mockito.mock(PlcRepository.class);
        opcuaConnector = Mockito.mock(OpcuaConnector.class);

        plcService = new PlcServiceImpl(plcRepository, opcuaConnector);
    }

    @Nested @DisplayName("UPDATE PLC BY ID")
    class updatePlcById{
        Plc upgradedPlc;
        Plc oldPlc;
        @BeforeEach
        void initializeForUpdatePlcById(){
            upgradedPlc = Plc.builder().ipAddress("192.168.0.2").build();
            oldPlc = Plc.builder().ipAddress("192.168.0.1").build();
        }

        @Test @DisplayName("throws PlcNotFoundException if PLC is not found in database")
        void ifPlcIsNotFoundReturnFalse(){
            //Mock
            Mockito.when(plcRepository.findById(1L)).thenReturn(Optional.empty());

            Plc updatedPlc = Plc.builder().ipAddress("192.168.1.1").build();
            assertThrows(PlcNotFoundException.class,() -> plcService.updateById(1L, updatedPlc));
        }

        @Test @DisplayName("replaces id of updated PLC by the id inputted into the method")
        void idIsReplaced(){
            Mockito.when(plcRepository.findById(1L)).thenReturn(Optional.of(oldPlc));

            plcService.updateById(1L,upgradedPlc);
            assertThat(upgradedPlc.getId()).isEqualTo(1L);
        }

        @Test @DisplayName("saves updated PLCto DB")
        void newPlcIsSavedToDb(){
            Mockito.when(plcRepository.findById(1L)).thenReturn(Optional.of(oldPlc));

            plcService.updateById(1L,upgradedPlc);
            Mockito.verify(plcRepository,Mockito.times(1)).save(upgradedPlc);
        }

        @Test @DisplayName("updates client if ip of updated plc is different")
        void updatesClientWhenPlcIsUpdated(){
            Mockito.when(plcRepository.findById(1L)).thenReturn(Optional.of(oldPlc));

            plcService.updateById(1L, upgradedPlc);

            Mockito.verify(opcuaConnector,Mockito.times(1)).disconnectPlc(oldPlc);
            Mockito.verify(opcuaConnector,Mockito.times(1)).connectPlc(upgradedPlc);
        }

        @Test @DisplayName("sets PLC status to DISCONNECTED when OPC UA connection can not be established")
        void setsCorrectConnectionStatusWhenConnectionCouldNotBeEstablished(){
            Mockito.when(plcRepository.findById(1L)).thenReturn(Optional.of(oldPlc));

            plcService.updateById(1L,upgradedPlc);

            assertThat(upgradedPlc.getConnection().getStatus()).isEqualTo(ConnectionStatus.CONNECTED);
        }

        @Test @DisplayName("sets PLC status to CONNECTED when OPC UA connection was established")
        void setsCorrectConnectionStatusWhenConnectionWasEstablished(){
            upgradedPlc.markAsConnected();
            Mockito.when(plcRepository.findById(1L)).thenReturn(Optional.of(oldPlc));
            Mockito.when(opcuaConnector.connectPlc(upgradedPlc)).thenThrow(new OpcuaConnectionException());

            plcService.updateById(1L,upgradedPlc);

            assertThat(upgradedPlc.getConnection().getStatus()).isEqualTo(ConnectionStatus.DISCONNECTED);
        }
    }

    @Nested @DisplayName("CHANGE CURRENT TOOL")
    class changeCurrentTool{

        @Test @DisplayName("if plc with given ip address is not found return throws exception")
        void ifPlcIsNotFoundThrowsException(){
            //Mock
            Mockito.when(plcRepository.findByIpAddress("192.168.0.1")).thenReturn(Optional.empty());

            assertThrows(PlcNotFoundException.class, () -> plcService.changeCurrentTool("192.168.0.1",1));
        }

        @Test @DisplayName("set new currentTool")
        void setNewCurrentTool(){
            String ipAddress = "192.168.0.1";
            int newCurrentToolId = 1;
            //Mock
            Optional<Plc> plcInDb = Optional.of(Mockito.mock(Plc.class));
            Mockito.when(plcRepository.findByIpAddress(ipAddress)).thenReturn(plcInDb);


            plcService.changeCurrentTool("192.168.0.1",newCurrentToolId);
            Mockito.verify(plcInDb.get(),Mockito.times(1)).setCurrentTool(newCurrentToolId);
        }

        @Test @DisplayName("updates plc in database")
        void updatesPlcInDatabase(){
            String ipAddress = "192.168.0.1";
            //Mock
            Optional<Plc> plcInDb = Optional.of(Mockito.mock(Plc.class));
            Mockito.when(plcRepository.findByIpAddress(ipAddress)).thenReturn(plcInDb);


            plcService.changeCurrentTool("192.168.0.1",1);
            Mockito.verify(plcRepository,Mockito.times(1)).save(plcInDb.get());
        }
    }

    @Nested @DisplayName("CREATE PLC")
    class createPlc{
        Plc newPlc;

        @BeforeEach
        void initializeForCreatePlc(){
            newPlc = new Plc();
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
            plcService.create(newPlc);

            assertThat(newPlc.getConnection().getStatus()).isEqualTo(ConnectionStatus.CONNECTED);
        }

        @Test @DisplayName("sets PLC status to DISCONNECTED when connection was not established")
        void setPlcStatusToDisconnected(){
            Mockito.when(opcuaConnector.connectPlc(newPlc)).thenThrow(new OpcuaConnectionException());

            plcService.create(newPlc);

            assertThat(newPlc.getConnection().getStatus()).isEqualTo(ConnectionStatus.DISCONNECTED);
        }

        @Test @DisplayName("throws PlcUniqueConstrainViolationException when PLC has same IP address as one of PLCs in database")
        void throwsExceptionWhenIpAddressIsNotUnique(){
            Plc plc = Plc.builder().ipAddress("192.168.0.1").build();
            Mockito.when(plcRepository.existsByIpAddress(plc.getIpAddress())).thenReturn(true);

            assertThrows(PlcUniqueConstrainException.class,() -> plcService.create(plc));
        }

        @Test @DisplayName("throws PlcUniqueConstrainViolationException when PLC has same name as one of PLCs in database")
        void throwsExceptionWhenNameIsNotUnique(){
            Plc plc = Plc.builder().ipAddress("192.168.0.1").name("name").build();
            Mockito.when(plcRepository.existsByName(plc.getName())).thenReturn(true);

            assertThrows(PlcUniqueConstrainException.class,() -> plcService.create(plc));
        }

        @Test @DisplayName("throws PlcUniqueConstrainViolationException with correct message when PLC has same name and ip as one of PLCs in database")
        void throwsExceptionWhenNameAnIpAreNotUnique(){
            Plc plc = Plc.builder().ipAddress("192.168.0.1").name("name").build();
            Mockito.when(plcRepository.existsByName(plc.getName())).thenReturn(true);
            Mockito.when(plcRepository.existsByIpAddress(plc.getIpAddress())).thenReturn(true);

            try {
                plcService.create(plc);
            } catch (PlcUniqueConstrainException ex){
                assertThat(ex.getMessage()).contains("IP address").contains("name");
            }
        }
    }

    @Nested @DisplayName("DELETE PLC BY ID")
    class deletePlcById{
        @Test @DisplayName("deletes plc from database when exists")
        void deletesPlcFromDatabase(){
            Plc oldPlc = new Plc();
            Mockito.when(plcRepository.findById(1L)).thenReturn(Optional.of(oldPlc));
            Mockito.when(plcRepository.existsById(1L)).thenReturn(true);

            plcService.deleteById(1L);

            Mockito.verify(plcRepository,Mockito.times(1)).deleteById(1L);
        }

        @Test @DisplayName("throws PlcNotFoundException when PLC doesn't exist")
        void throwsPlcNotFoundExceptionWhenPlcDoesNotExist(){
            Mockito.when(plcRepository.existsById(1L)).thenReturn(false);
            Mockito.when(plcRepository.findById(1L)).thenReturn(Optional.empty());
            Mockito.doThrow(new EmptyResultDataAccessException(1)).when(plcRepository).deleteById(1L);

            assertThrows(PlcNotFoundException.class,() -> plcService.deleteById(1L));
        }

        @Test @DisplayName("disconnects PLC from OPC UA when PLC exists")
        void disconnectsPlcFromOpcuaWhenPlcExists(){
            Plc oldPlc = new Plc();
            Mockito.when(plcRepository.findById(1L)).thenReturn(Optional.of(oldPlc));

            plcService.deleteById(1L);

            Mockito.verify(opcuaConnector,Mockito.times(1)).disconnectPlc(oldPlc);
        }


    }

    @Nested @DisplayName("FIND BY ID")
    class findById{
        @Test @DisplayName("if PLC is not found in DB returns empty optional")
        void ifPlcIsNotInDbReturnsEmptyOptional(){
            Mockito.when(plcRepository.findById(1L)).thenReturn(Optional.empty());

            assertThat(plcService.findById(1L)).isEmpty();

        }

        @Test @DisplayName("if PLC is  in DB returns optional of plc")
        void ifPlcIsInDbReturnsOptionalOfPlc(){
            Plc plc = new Plc();
            Mockito.when(plcRepository.findById(1L)).thenReturn(Optional.of(plc));

            Optional<Plc> plcInDb = plcService.findById(1L);
            assertThat(plcInDb).isNotEmpty();
            assertThat(plcInDb.get()).isEqualTo(plc);
        }


    }

    @Nested @DisplayName("FIND BY IP ADDRESS")
    class findByIpAddress{
        private final static String mockIpAddress = "192.168.0.1";
        @Test @DisplayName("if PLC is not found in DB returns empty optional")
        void ifPlcIsNotInDbReturnsEmptyOptional(){
            Mockito.when(plcRepository.findByIpAddress(mockIpAddress)).thenReturn(Optional.empty());

            assertThat(plcService.findByIpAddress(mockIpAddress)).isEmpty();

        }

        @Test @DisplayName("if PLC is  in DB returns optional of plc")
        void ifPlcIsInDbReturnsOptionalOfPlc(){
            Plc plc = new Plc();
            Mockito.when(plcRepository.findByIpAddress(mockIpAddress)).thenReturn(Optional.of(plc));

            Optional<Plc> plcInDb = plcService.findByIpAddress(mockIpAddress);
            assertThat(plcInDb).isNotEmpty();
            assertThat(plcInDb.get()).isEqualTo(plc);
        }


    }

    @Nested @DisplayName("FIND ALL")
    class findAll{
        @Test @DisplayName("triggers plcRepository.findAll()")
        void triggersPlcRepository(){
            plcService.findAll();

            Mockito.verify(plcRepository,Mockito.times(1)).findAll();
        }
    }


}
