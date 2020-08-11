package com.siemens.metal_forming.service;

import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.enumerated.ConnectionStatus;
import com.siemens.metal_forming.exception.OpcuaConnectionException;
import com.siemens.metal_forming.exception.PlcNotFoundException;
import com.siemens.metal_forming.opcua.OpcuaConnector;
import com.siemens.metal_forming.repository.PlcRepository;
import com.siemens.metal_forming.service.impl.PlcServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("<= PLC SERVICE SPECIFICATION =>")
public class PlcServiceSpec {
    PlcService plcService;
    PlcRepository plcRepository;
    OpcuaConnector opcuaConnector;

    @BeforeEach
    void initialize() {
        plcRepository = Mockito.mock(PlcRepository.class);
        opcuaConnector = Mockito.mock(OpcuaConnector.class);

        plcService = new PlcServiceImpl(plcRepository, opcuaConnector);
    }

    @Nested @DisplayName("FIND PLC BY ID")
    class findPlcById{

        @Test @DisplayName("if PLC is not found in database returns false")
        void ifPlcIsNotFoundReturnFalse(){
            //Mock
            Mockito.when(plcRepository.findById(1L)).thenReturn(Optional.empty());

            Plc updatedPlc = Plc.builder().ipAddress("192.168.1.1").build();
            assertThat(plcService.updatePlcById(1L,updatedPlc)).isFalse();
        }

        @Test @DisplayName("id of upgraded PLC is replaced by the id inputted into the method")
        void idIsReplaced(){
            //Mock
            Plc upgradedPlcMock = Mockito.mock(Plc.class);
           Mockito.when(plcRepository.findById(1L)).thenReturn(Optional.of(new Plc()));

            plcService.updatePlcById(1L,upgradedPlcMock);
            Mockito.verify(upgradedPlcMock,Mockito.times(1)).setId(1L);
        }

        @Test @DisplayName("upgraded PLC is saved to DB")
        void newPlcIsSavedToDb(){
            //Mock
            Plc upgradedPlcMock = Mockito.mock(Plc.class);
            Mockito.when(plcRepository.findById(1L)).thenReturn(Optional.of(new Plc()));

            plcService.updatePlcById(1L,upgradedPlcMock);
            Mockito.verify(plcRepository,Mockito.times(1)).save(upgradedPlcMock);
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
    }

    @Nested @DisplayName("DELETE PLC BY ID")
    class deletePlcById{
        @Test @DisplayName("deletes plc from database") @Disabled
        void deletesPlcFromDatabase(){

        }
    }


}
