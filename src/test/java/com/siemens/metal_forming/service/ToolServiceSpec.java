package com.siemens.metal_forming.service;

import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.enumerated.ToolStatusType;
import com.siemens.metal_forming.exception.exceptions.PlcNotFoundException;
import com.siemens.metal_forming.exception.exceptions.ToolNotFoundException;
import com.siemens.metal_forming.exception.exceptions.ToolNumberUpdateException;
import com.siemens.metal_forming.exception.exceptions.ToolUniqueConstrainException;
import com.siemens.metal_forming.repository.PlcRepository;
import com.siemens.metal_forming.repository.ToolRepository;
import com.siemens.metal_forming.service.impl.ToolServiceImpl;
import com.siemens.metal_forming.testBuilders.TestCurveBuilder;
import com.siemens.metal_forming.testBuilders.TestPlcBuilder;
import com.siemens.metal_forming.testBuilders.TestToolBuilder;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.OneToOne;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
@DisplayName("<= TOOL SERVICE SPECIFICATION =>")
class ToolServiceSpec {
    @Captor
    ArgumentCaptor<Consumer<Plc>> plcConsumerCaptor;

    @Captor
    ArgumentCaptor<Tool> toolCaptor;

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
        firstToolInDb = Tool.builder().id(idOfFirstExistingTool).toolNumber(toolNumberOfFirstExistingTool).nameFromPlc("firstTool").build();
        secondToolInDb = Tool.builder().id(idOfSecondExistingTool).toolNumber(toolNumberOfSecondExistingTool).nameFromPlc("secondTool").build();
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
                when(plcRepository.existsById(1L)).thenReturn(true);
                when(toolRepository.findAllByPlcId(1L)).thenReturn(toolsInDb);
            }

            @Test @DisplayName("triggers ToolRepository.findAllByPlcId()")
            void triggersToolRepository(){
                toolService.findAll(1L);
                verify(toolRepository, times(1)).findAllByPlcId(1L);
            }

            @Test @DisplayName("returns what was found in database")
            void returnsWhatWasFoundInDatabase(){
                assertThat(toolService.findAll(1L).size()).isEqualTo(toolsInDb.size());
            }
        }


        @Test @DisplayName("throws PlcNotFoundException when PLC with given ID was not found in database")
        void throwsExceptionWhenPlcDoesNotExistInDatabase(){
            when(plcRepository.existsById(1L)).thenReturn(false);

            assertThrows(PlcNotFoundException.class,() -> toolService.findAll(1L));
        }
    }

    @Nested @DisplayName("FIND ALL TOOLS")
    class FindAllTools{
        @Test @DisplayName("triggers toolRepository.findAll()")
        void triggersToolRepository(){
            toolService.findAll();
            verify(toolRepository, times(1)).findAll();
        }

        @Test @DisplayName("returns what was found in database")
        void returnsWhatWasFoundInDataBase(){
            List<Tool> listToReturn = List.of(new Tool(),new Tool(), new Tool());
            when(toolRepository.findAll()).thenReturn(listToReturn);

            assertThat(toolService.findAll().size()).isEqualTo(listToReturn.size());
        }
    }

    @Nested @DisplayName("DELETE ONE TOOL FROM PLC BY PLC ID AND TOOL ID")
    class DeleteOneToolFromPlcByPlcIdAndToolId{
        @Test @DisplayName("throws PlcNotFoundException when there was no PLC with given ID")
        void throwsPlcNotFoundExceptionWhenThereWasNoPlcWithGivenId(){
            when(plcService.update(any(Long.class),ArgumentMatchers.<Consumer<Plc>>any())).thenThrow(new PlcNotFoundException(1L));

            assertThrows(PlcNotFoundException.class, () -> toolService.delete(1L,1L));
        }

        @Test @DisplayName("throws ToolNotFoundException when there was no tool with given ID")
        void throwsToolNotFoundExceptionWhenThereWasNoToolWithGivenId(){
            when(plcService.update(any(Long.class),ArgumentMatchers.<Consumer<Plc>>any())).thenThrow(new ToolNotFoundException(1L));

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

                verify(plcService, times(1)).update(anyLong(), ArgumentMatchers.<Consumer<Plc>>any());
            }

            @Test @DisplayName("deletes tool from plc")
            void deletesToolFromPlc(){
                toolService.delete(1L,1L);
                verify(plcService).update(anyLong(),plcConsumerCaptor.capture());


                Plc testPlc = Mockito.spy(Plc.builder().build());
                testPlc.addTool(Tool.builder().toolNumber(1).id(1L).build());
                plcConsumerCaptor.getValue().accept(testPlc);

                verify(testPlc, times(1)).removeTool(any(Tool.class));
            }

            @Test @DisplayName("throws ToolNotFoundException when tool was not found")
            void throwsToolNotFoundExceptionWhenToolWasNotFound(){
                toolService.delete(1L,1L);
                verify(plcService).update(anyLong(),plcConsumerCaptor.capture());


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
            when(plcService.update(any(Long.class),ArgumentMatchers.<Consumer<Plc>>any())).thenThrow(new PlcNotFoundException(1L));

            assertThrows(PlcNotFoundException.class,() -> toolService.create(1L,new Tool()));
        }

        @Test @DisplayName("returns updated tool with toolId")
        void returnsUpdatedToolWithToolId(){
            Tool newTool = Tool.builder().toolNumber(1).build(); //must include tool number
            Tool toolToReturn = Tool.builder().id(1L).toolNumber(1).build();
            Plc plcToReturn = new Plc();
            plcToReturn.addTool(toolToReturn);
            when(plcService.update(any(Long.class),any())).thenReturn(plcToReturn);

            Tool createdTool = toolService.create(1L,newTool);
            assertThat(createdTool.getId()).isEqualTo(1L);
        }

        @Test @DisplayName("throws ToolUniqueConstrainException when tool with same toolNumber already exists")
        void throwsToolUniqueConstrainExceptionWhenToolAlreadyExists(){
            Plc plcToReturn = new Plc();
            plcToReturn.addTool(Tool.builder().toolNumber(1).build());
            when(plcService.update(anyLong(),ArgumentMatchers.<Consumer<Plc>>any())).thenReturn(plcToReturn);
            toolService.create(1L,Tool.builder().toolNumber(1).build());
            verify(plcService).update(anyLong(),plcConsumerCaptor.capture());

            Plc testPlc = Mockito.spy(new Plc());
            testPlc.addTool(Tool.builder().toolNumber(1).build());

            assertThrows(ToolUniqueConstrainException.class,() -> plcConsumerCaptor.getValue().accept(testPlc));
        }

        @Test @DisplayName("adds tool when everything is ok")
        void addsToolWhenEverythingIsOk(){
            Plc plcToReturn = new Plc();
            plcToReturn.addTool(Tool.builder().toolNumber(1).build());
            when(plcService.update(anyLong(),ArgumentMatchers.<Consumer<Plc>>any())).thenReturn(plcToReturn);
            toolService.create(1L,Tool.builder().toolNumber(1).build());
            verify(plcService).update(anyLong(),plcConsumerCaptor.capture());

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
                when(plcService.update(anyLong(),any())).thenReturn(plcInDb);
            }

            @Test @DisplayName("throws PlcUniqueConstrainException when tool number is changed to one which already exists")
            void throwsExceptionWhenToolNumberAlreadyExists(){
                //runs method without error
                toolService.update(idOfExistingPlc, idOfSecondExistingTool, tool -> tool.setToolNumber(toolNumberOfFirstExistingTool));
                //catches update of plc
                verify(plcService).update(anyLong(),plcConsumerCaptor.capture());

                //if throws error during updating plc and tool
                assertThrows(ToolUniqueConstrainException.class,() -> plcConsumerCaptor.getValue().accept(plcInDb));
            }

            @Test @DisplayName("throws ToolNumberUpdateException when tool number was updated for autodetected tool") @Disabled("needs refactoring of main code")
            void throwsExceptionWhenToolNumberWasUpdatedForAutodetectedTool(){
                Tool toolInDatabase = new TestToolBuilder().id(1L).toolStatus(ToolStatusType.AUTODETECTED).build();
                Plc plcInDatabase = new TestPlcBuilder().id(1L).build();
                plcInDatabase.addTool(toolInDatabase);

                when(plcRepository.findById(1L)).thenReturn(Optional.of(plcInDatabase));

                assertThrows(ToolNumberUpdateException.class, ()-> toolService.update(1L,1L, tool -> tool.setToolNumber(1)));
            }

            @Test @DisplayName("updates one attribute")
            void updatesOneAttribute(){
                //runs method without error
                toolService.update(idOfExistingPlc, idOfFirstExistingTool, tool -> tool.setNameFromPlc("newName"));
                //catches update of plc
                verify(plcService).update(anyLong(),plcConsumerCaptor.capture());
                //runs update of plc which was caught
                plcConsumerCaptor.getValue().accept(plcInDb);

                assertThat(plcInDb.getToolById(idOfFirstExistingTool).getNameFromPlc()).isEqualTo("newName");
            }

            @Test @DisplayName("updates multiple attributes")
            void updatesMultipleAttribute(){
                //runs method without error
                toolService.update(idOfExistingPlc, idOfFirstExistingTool, tool -> {
                    tool.setNameFromPlc("newName");
                    tool.setToolNumber(100);
                });
                //catches update of plc
                verify(plcService).update(anyLong(),plcConsumerCaptor.capture());
                //runs update of plc which was caught
                plcConsumerCaptor.getValue().accept(plcInDb);

                SoftAssertions softAssertions = new SoftAssertions();
                softAssertions.assertThat(plcInDb.getToolById(idOfFirstExistingTool).getNameFromPlc()).isEqualTo("newName");
                softAssertions.assertThat(plcInDb.getToolById(idOfFirstExistingTool).getToolNumber()).isEqualTo(100);
                softAssertions.assertAll();
            }
        }

        @Test @DisplayName("throws PlcNotFoundException when plc with given ID was not found")
        void throwsExceptionWhenPlcWasNotFound(){
            when(plcService.update(anyLong(),any())).thenThrow(new PlcNotFoundException(idOfNonExistentPlc));

            assertThrows(PlcNotFoundException.class, () -> toolService.update(idOfNonExistentPlc, idOfFirstExistingTool, tool -> {}));
        }

        @Test @DisplayName("throws ToolNotFoundException when tool with given ID was not found")
        void throwsExceptionWhenToolWasNotFound(){
            when(plcService.update(anyLong(),any())).thenReturn(plcInDb);
            assertThrows(ToolNotFoundException.class, () ->toolService.update(idOfExistingPlc,idOfNonExistentTool,tool -> {}));
            verify(plcService).update(anyLong(),plcConsumerCaptor.capture());

            assertThrows(ToolNotFoundException.class, () -> plcConsumerCaptor.getValue().accept(plcInDb));
        }
    }

    @Nested @DisplayName("UPDATES REFERENCE CURVE")
    class UpdatesReferenceCurve {
        @Test @DisplayName("throws ToolNotFoundException when tool was not found in database")
        void throwsToolNotFoundExceptionWhenToolWasNotFoundInDatabase(){
            Curve newReferenceCurve = new TestCurveBuilder().randomPoints(360).build();

            when(toolRepository.findById(1L)).thenReturn(Optional.empty());

            assertThrows(ToolNotFoundException.class, () -> toolService.updateReferenceCurve(1L, newReferenceCurve));
        }

        @Test @DisplayName("sets new ReferenceCurve value and sets calculateReferenceCurve to false and saves it to db")
        void setsNewReferenceCurveValueAndSetsCalculateReferenceCurveToFalseAndSavesItToDb(){
            Curve newReferenceCurve = new TestCurveBuilder().randomPoints(360).build();
            Tool toolInDb = new TestToolBuilder().id(1L).calculateReferenceCurve(true).build();

            when(toolRepository.findById(1L)).thenReturn(Optional.of(toolInDb));

            toolService.updateReferenceCurve(1L, newReferenceCurve);

            verify(toolRepository, times(1)).save(toolCaptor.capture());

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(toolCaptor.getValue().getReferenceCurve()).isEqualTo(newReferenceCurve);
            softAssertions.assertThat(toolCaptor.getValue().getCalculateReferenceCurve()).isFalse();
            softAssertions.assertAll();
        }
    }
}
