package com.siemens.metal_forming.service;

import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.exception.exceptions.PlcNotFoundException;
import com.siemens.metal_forming.exception.exceptions.ToolNotFoundException;
import com.siemens.metal_forming.exception.exceptions.ToolUniqueConstrainException;
import com.siemens.metal_forming.repository.PlcRepository;
import com.siemens.metal_forming.repository.ToolRepository;
import com.siemens.metal_forming.service.impl.ToolServiceImpl;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
@ExtendWith({MockitoExtension.class})
@DisplayName("<= TOOL SERVICE SPECIFICATION =>")
class ToolServiceSpec {
    @Captor
    ArgumentCaptor<Consumer<Plc>> plcConsumerCaptor;

    private ToolRepository toolRepository;
    private PlcRepository plcRepository;
    private PlcService plcService;
    private ToolService toolService;

    //VARIABLES USED IN TESTS
    private final long idOfExistingPlc = 1L;
    private final long idOfNonExistentPlc = 2L;

    private final long idOfFirstExistingTool = 1L;
    private final long idOfSecondExistingTool = 2L;
    private final long idOfNonExistentTool = 3L;

    private final int toolNumberOfFirstExistingTool = 1;
    private final int toolNumberOfSecondExistingTool = 2;
    private final int toolNumberOfNonExistentTool = 3;

    private Tool firstToolInDb;
    private Tool secondToolInDb;
    private Plc plcInDb;


    @BeforeEach
    void initialize(){
        //INITIALIZE MOCKS
        toolRepository = Mockito.mock(ToolRepository.class);
        plcRepository = Mockito.mock(PlcRepository.class);
        plcService = Mockito.mock(PlcService.class);
        toolService = new ToolServiceImpl(toolRepository, plcRepository, plcService);

        //INITIALIZE VARIABLES
        firstToolInDb = Tool.builder().id(idOfFirstExistingTool).toolNumber(toolNumberOfFirstExistingTool).name("firstTool").build();
        secondToolInDb = Tool.builder().id(idOfSecondExistingTool).toolNumber(toolNumberOfSecondExistingTool).name("secondTool").build();
        plcInDb = new Plc();
        plcInDb.addTool(firstToolInDb);
        plcInDb.addTool(secondToolInDb);
    }
    @Nested @DisplayName("FIND ALL TOOLS FROM ONE PLC BY PLC ID")
    class FindAllByPlcId{
        @Nested @DisplayName("PLC IS IN DATABASE")
        class PlcIsInDatabase{
            List<Tool> toolsInDb;
            @BeforeEach
            void initializeForPlcIsInDatabase(){
                toolsInDb = List.of(new Tool(),new Tool(),new Tool());
                Mockito.when(plcRepository.existsById(1L)).thenReturn(true);
                Mockito.when(toolRepository.findAllByPlcId(1L)).thenReturn(toolsInDb);
            }

            @Test @DisplayName("triggers ToolRepository.findAllByPlcId()")
            void triggersToolRepository(){
                toolService.findAll(1L);
                Mockito.verify(toolRepository,Mockito.times(1)).findAllByPlcId(1L);
            }

            @Test @DisplayName("returns what was found in database")
            void returnsWhatWasFoundInDatabase(){
                assertThat(toolService.findAll(1L).size()).isEqualTo(toolsInDb.size());
            }
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
            Mockito.when(plcService.update(any(Long.class),ArgumentMatchers.<Consumer<Plc>>any())).thenThrow(new PlcNotFoundException(1L));

            assertThrows(PlcNotFoundException.class, () -> toolService.delete(1L,1L));
        }

        @Test @DisplayName("throws ToolNotFoundException when there was no tool with given ID")
        void throwsToolNotFoundExceptionWhenThereWasNoToolWithGivenId(){
            Mockito.when(plcService.update(any(Long.class),ArgumentMatchers.<Consumer<Plc>>any())).thenThrow(new ToolNotFoundException(1L));

            assertThrows(ToolNotFoundException.class, () -> toolService.delete(1L,1L));
        }

        @ExtendWith(MockitoExtension.class)
        @Nested @DisplayName("PLC IS IN DATABASE")
        class PlcAndToolAreInDatabase{
            @Captor
            ArgumentCaptor<Consumer<Plc>> plcConsumerCaptor;

            @Test @DisplayName("triggers plcService.updateById")
            void triggersPlcServiceUpdateById(){
                toolService.delete(1L,1L);

                Mockito.verify(plcService,Mockito.times(1)).update(anyLong(), ArgumentMatchers.<Consumer<Plc>>any());
            }

            @Test @DisplayName("deletes tool from plc")
            void deletesToolFromPlc(){
                toolService.delete(1L,1L);
                Mockito.verify(plcService).update(anyLong(),plcConsumerCaptor.capture());


                Plc testPlc = Mockito.spy(Plc.builder().build());
                testPlc.addTool(Tool.builder().toolNumber(1).id(1L).build());
                plcConsumerCaptor.getValue().accept(testPlc);

                Mockito.verify(testPlc,Mockito.times(1)).removeTool(any(Tool.class));
            }

            @Test @DisplayName("throws ToolNotFoundException when tool was not found")
            void throwsToolNotFoundExceptionWhenToolWasNotFound(){
                toolService.delete(1L,1L);
                Mockito.verify(plcService).update(anyLong(),plcConsumerCaptor.capture());


                Plc testPlc = Mockito.spy(Plc.builder().build());
                testPlc.addTool(Tool.builder().toolNumber(1).id(2L).build());

                assertThrows(ToolNotFoundException.class, () -> plcConsumerCaptor.getValue().accept(testPlc));
            }
        }

    }


    @Nested @DisplayName("CREATE TOOL")
    class CreateTool{
        @Test @DisplayName("throws PlcNotFoundException when PLC is not in database")
        void throwsPlcNotFoundExceptionWhenPlcIsNotInDatabase(){
            Mockito.when(plcService.update(any(Long.class),ArgumentMatchers.<Consumer<Plc>>any())).thenThrow(new PlcNotFoundException(1L));

            assertThrows(PlcNotFoundException.class,() -> toolService.create(1L,new Tool()));
        }

        @Test @DisplayName("returns updated tool with toolId")
        void returnsUpdatedToolWithToolId(){
            Tool newTool = Tool.builder().toolNumber(1).build(); //must include tool number
            Tool toolToReturn = Tool.builder().id(1L).toolNumber(1).build();
            Plc plcToReturn = new Plc();
            plcToReturn.addTool(toolToReturn);
            Mockito.when(plcService.update(any(Long.class),any())).thenReturn(plcToReturn);

            Tool createdTool = toolService.create(1L,newTool);
            assertThat(createdTool.getId()).isEqualTo(1L);
        }

        @Test @DisplayName("throws ToolUniqueConstrainException when tool with same toolNumber already exists")
        void throwsToolUniqueConstrainExceptionWhenToolAlreadyExists(){
            Plc plcToReturn = new Plc();
            plcToReturn.addTool(Tool.builder().toolNumber(1).build());
            Mockito.when(plcService.update(anyLong(),ArgumentMatchers.<Consumer<Plc>>any())).thenReturn(plcToReturn);
            toolService.create(1L,Tool.builder().toolNumber(1).build());
            Mockito.verify(plcService).update(anyLong(),plcConsumerCaptor.capture());

            Plc testPlc = Mockito.spy(new Plc());
            testPlc.addTool(Tool.builder().toolNumber(1).build());

            assertThrows(ToolUniqueConstrainException.class,() -> plcConsumerCaptor.getValue().accept(testPlc));
        }

        @Test @DisplayName("adds tool when everything is ok")
        void addsToolWhenEverythingIsOk(){
            Plc plcToReturn = new Plc();
            plcToReturn.addTool(Tool.builder().toolNumber(1).build());
            Mockito.when(plcService.update(anyLong(),ArgumentMatchers.<Consumer<Plc>>any())).thenReturn(plcToReturn);
            toolService.create(1L,Tool.builder().toolNumber(1).build());
            Mockito.verify(plcService).update(anyLong(),plcConsumerCaptor.capture());

            Plc testPlc = new Plc();
            plcConsumerCaptor.getValue().accept(testPlc);

            assertThat(testPlc.getTools()).isNotEmpty();
        }
    }

    @Nested @DisplayName("UPDATE TOOL ATTRIBUTES BY PLC ID AND TOOL ID")
    class UpdateToolAttributesByPlcIdAndToolId{
        @Nested @DisplayName("PLC AND TOOL EXIST")
        class PlcAndToolExist{

            @BeforeEach
            void initializeForPlcAndToolExist(){
                Mockito.when(plcService.update(anyLong(),any())).thenReturn(plcInDb);
            }

            @Test @DisplayName("throws PlcUniqueConstrainException when tool number is changed to one which already exists")
            void throwsExceptionWhenToolNumberAlreadyExists(){
                //runs method without error
                toolService.update(idOfExistingPlc, idOfSecondExistingTool, tool -> tool.setToolNumber(toolNumberOfFirstExistingTool));
                //catches update of plc
                Mockito.verify(plcService).update(anyLong(),plcConsumerCaptor.capture());

                //if throws error during updating plc and tool
                assertThrows(ToolUniqueConstrainException.class,() -> plcConsumerCaptor.getValue().accept(plcInDb));
            }

            @Test @DisplayName("updates one attribute")
            void updatesOneAttribute(){
                //runs method without error
                toolService.update(idOfExistingPlc, idOfFirstExistingTool, tool -> tool.setName("newName"));
                //catches update of plc
                Mockito.verify(plcService).update(anyLong(),plcConsumerCaptor.capture());
                //runs update of plc which was caught
                plcConsumerCaptor.getValue().accept(plcInDb);

                assertThat(plcInDb.getTool(idOfFirstExistingTool).getName()).isEqualTo("newName");
            }

            @Test @DisplayName("updates multiple attributes")
            void updatesMultipleAttribute(){
                //runs method without error
                toolService.update(idOfExistingPlc, idOfFirstExistingTool, tool -> {
                    tool.setName("newName");
                    tool.setToolNumber(100);
                });
                //catches update of plc
                Mockito.verify(plcService).update(anyLong(),plcConsumerCaptor.capture());
                //runs update of plc which was caught
                plcConsumerCaptor.getValue().accept(plcInDb);

                SoftAssertions softAssertions = new SoftAssertions();
                softAssertions.assertThat(plcInDb.getTool(idOfFirstExistingTool).getName()).isEqualTo("newName");
                softAssertions.assertThat(plcInDb.getTool(idOfFirstExistingTool).getToolNumber()).isEqualTo(100);
                softAssertions.assertAll();
            }
        }

        @Test @DisplayName("throws PlcNotFoundException when plc with given ID was not found")
        void throwsExceptionWhenPlcWasNotFound(){
            Mockito.when(plcService.update(anyLong(),any())).thenThrow(new PlcNotFoundException(idOfNonExistentPlc));

            assertThrows(PlcNotFoundException.class, () -> toolService.update(idOfNonExistentPlc, idOfFirstExistingTool, tool -> {}));
        }

        @Test @DisplayName("throws ToolNotFoundException when tool with given ID was not found")
        void throwsExceptionWhenToolWasNotFound(){
            Mockito.when(plcService.update(anyLong(),any())).thenReturn(plcInDb);
            assertThrows(ToolNotFoundException.class, () ->toolService.update(idOfExistingPlc,idOfNonExistentTool,tool -> {}));
            Mockito.verify(plcService).update(anyLong(),plcConsumerCaptor.capture());

            assertThrows(ToolNotFoundException.class, () -> plcConsumerCaptor.getValue().accept(plcInDb));
        }
    }
}
