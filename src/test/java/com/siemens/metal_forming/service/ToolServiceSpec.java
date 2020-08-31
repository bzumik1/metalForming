package com.siemens.metal_forming.service;

import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.exception.exceptions.PlcNotFoundException;
import com.siemens.metal_forming.exception.exceptions.ToolNotFoundException;
import com.siemens.metal_forming.repository.PlcRepository;
import com.siemens.metal_forming.repository.ToolRepository;
import com.siemens.metal_forming.service.impl.ToolServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("<= TOOL SERVICE SPECIFICATION =>")
class ToolServiceSpec {
    private ToolRepository toolRepository;
    private PlcRepository plcRepository;
    private ToolService toolService;


    @BeforeEach
    void initialize(){
        toolRepository = Mockito.mock(ToolRepository.class);
        plcRepository = Mockito.mock(PlcRepository.class);
        toolService = new ToolServiceImpl(toolRepository, plcRepository);
    }
    @Nested @DisplayName("FIND ALL TOOLS FROM ONE PLC BY PLC ID")
    class FindAllByPlcId{
        @Test @DisplayName("triggers ToolRepository.findAllByPlcId()")
        void triggersToolRepository(){
            toolService.findAll(1L);
            Mockito.verify(toolRepository,Mockito.times(1)).findAllByPlcId(1L);
        }

        @Test @DisplayName("returns what was found in database")
        void returnsWhatWasFoundInDatabase(){
            List<Tool> toolsToReturn = List.of(new Tool(),new Tool(),new Tool());
            Mockito.when(toolRepository.findAllByPlcId(1L)).thenReturn(toolsToReturn);

            assertThat(toolService.findAll(1L).size()).isEqualTo(toolsToReturn.size());
        }

        @Test @DisplayName("throws PlcNotFoundException when PLC with given ID was not found in database")
        void throwsExceptionWhenPlcDoesNotExistInDatabase(){
            Mockito.when(plcRepository.existsById(1L)).thenReturn(false);

            assertThrows(PlcNotFoundException.class,() -> toolService.findAll(1L));
        }
    }

    @Nested @DisplayName("DELETE ONE TOOL FROM PLC BY PLC ID AND TOOL ID")
    class DeleteOneToolFromPlcByPlcIdAndToolId{
        @Test @DisplayName("throws PlcNotFoundException when there was no PLC with given ID")
        void throwsPlcNotFoundExceptionWhenThereWasNoPlcWithGivenId(){
            Mockito.when(plcRepository.findById(1L)).thenReturn(Optional.empty());

            assertThrows(PlcNotFoundException.class, () -> toolService.deleteByPlcIdAndToolId(1L,1L));
        }

        @Test @DisplayName("throws ToolNotFoundException when there was no tool with given ID")
        void throwsToolNotFoundExceptionWhenThereWasNoToolWithGivenId(){
            Plc plcInDb = Plc.builder().id(1L).build();
            plcInDb.getTools().add(Tool.builder().id(1L).build());
            Mockito.when(plcRepository.findById(1L)).thenReturn(Optional.of(plcInDb));

            assertThrows(ToolNotFoundException.class, () -> toolService.deleteByPlcIdAndToolId(1L,2L));
        }

        @Nested @DisplayName("PLC AND TOOL ARE IN DATABASE")
        class PlcAndToolAreInDatabase{
            @BeforeEach
            void initializeForPlcAndToolAreInDatabase(){
                Plc plcInDatabase = Plc.builder().id(1L).build();
                plcInDatabase.getTools().add(Tool.builder().id(1L).build());
                Mockito.when(plcRepository.findById(1L)).thenReturn(Optional.of(plcInDatabase));
            }

            @Test @DisplayName("saves changes in plc to database")
            void savesChangesToDatabase(){
                toolService.deleteByPlcIdAndToolId(1L,1L);

                Mockito.verify(plcRepository,Mockito.times(1)).save(any(Plc.class));
            }

            @Test @DisplayName("deletes tool from plc")
            void deletesToolFromPlc(){
                toolService.deleteByPlcIdAndToolId(1L,1L);
                ArgumentCaptor<Plc> plcCaptor = ArgumentCaptor.forClass(Plc.class);
                Mockito.verify(plcRepository).save(plcCaptor.capture());


                assertThat(plcCaptor.getValue().getTools().stream().filter(plc -> plc.getId().equals(1L)).findFirst()).isEmpty();
            }
        }

    }
}
