package com.siemens.metal_forming.service;

import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.exception.PlcNotFoundException;
import com.siemens.metal_forming.repository.PlcRepository;
import com.siemens.metal_forming.service.impl.PlcServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("<= Plc Service Specification =>")
public class PlcServiceSpec {
    PlcService plcService;
    PlcRepository plcRepository;

    @BeforeEach
    void initialize() {
        plcRepository = Mockito.mock(PlcRepository.class);

        plcService = new PlcServiceImpl(plcRepository);
    }

    @Nested @DisplayName("find PLC by id")
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

    @Nested @DisplayName("change current tool")
    class selectCurrentTool{

        @Test @DisplayName("if plc with given ip address is not found return throws exception")
        void ifPlcIsNotFoundThrowsException(){
            //Mock
            Mockito.when(plcRepository.findByIpAddress("192.168.0.1")).thenReturn(Optional.empty());

            assertThrows(PlcNotFoundException.class, () -> plcService.changeCurrentTool("192.168.0.1",1));
        }
    }


}
