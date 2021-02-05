package com.siemens.metal_forming.service;

import com.siemens.metal_forming.dto.DtoMapper;
import com.siemens.metal_forming.dto.RelativeToleranceDto;
import com.siemens.metal_forming.dto.ToolDto;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.entity.RelativeTolerance;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.enumerated.StopReactionType;
import com.siemens.metal_forming.enumerated.ToolStatusType;
import com.siemens.metal_forming.exception.exceptions.PlcNotFoundException;
import com.siemens.metal_forming.exception.exceptions.ToolNotFoundException;
import com.siemens.metal_forming.exception.exceptions.ToolNumberUpdateException;
import com.siemens.metal_forming.exception.exceptions.ToolUniqueConstrainException;
import com.siemens.metal_forming.repository.PlcRepository;
import com.siemens.metal_forming.repository.ToolRepository;
import com.siemens.metal_forming.service.impl.ToolServiceImpl;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
@DisplayName("<= TOOL SERVICE SPECIFICATION =>")
class ToolServiceSpec {
    private ToolService toolService;

    @Mock
    private ToolRepository toolRepository;
    @Mock
    private PlcRepository plcRepository;
    @Mock
    private DtoMapper dtoMapper;

    @Captor
    ArgumentCaptor<Plc> plcCaptor;

    @BeforeEach
    void initialize(){
        toolService = new ToolServiceImpl(toolRepository, plcRepository, dtoMapper);
    }

    @Nested @DisplayName("FIND ALL TOOLS FROM ONE PLC BY PLC ID")
    class FindAllByPlcId{
        @Nested @DisplayName("PLC IS IN DATABASE")
        class PlcIsInDatabase{
            @Test @DisplayName("returns what was found in database")
            void returnsWhatWasFoundInDatabase(){
                List<Tool> toolsInDatabase = List.of(
                        Tool.builder().nickName("tool1").build(),
                        Tool.builder().nickName("tool2").build()
                );
                List<ToolDto.Response.Overview> toolsToReturn = List.of(
                        ToolDto.Response.Overview.builder().name("tool1").build(),
                        ToolDto.Response.Overview.builder().name("tool2").build()
                );

                when(plcRepository.existsById(1L)).thenReturn(true);
                when(toolRepository.findAllByPlcId(1L)).thenReturn(toolsInDatabase);
                when(dtoMapper.toToolDtoOverview(toolsInDatabase.get(0))).thenReturn(toolsToReturn.get(0));
                when(dtoMapper.toToolDtoOverview(toolsInDatabase.get(1))).thenReturn(toolsToReturn.get(1));

                assertThat(toolService.findAll(1L)).containsExactlyElementsOf(toolsToReturn);
            }
        }

        @Nested @DisplayName("PLC IS NOT IN DATABASE")
        class PlcIsNotInDatabase{
            @Test @DisplayName("throws PlcNotFoundException when PLC with given ID was not found in database")
            void throwsExceptionWhenPlcDoesNotExistInDatabase(){
                when(plcRepository.existsById(1L)).thenReturn(false);

                assertThrows(PlcNotFoundException.class,() -> toolService.findAll(1L));
            }
        }
    }

    @Nested @DisplayName("FIND ALL TOOLS")
    class FindAllTools{
        @Test @DisplayName("returns what was found in database")
        void returnsWhatWasFoundInDatabase(){
            List<Tool> toolsInDatabase = List.of(
                    Tool.builder().nickName("tool1").build(),
                    Tool.builder().nickName("tool2").build()
            );
            List<ToolDto.Response.Overview> toolsToReturn = List.of(
                    ToolDto.Response.Overview.builder().name("tool1").build(),
                    ToolDto.Response.Overview.builder().name("tool2").build()
            );

            when(toolRepository.findAll()).thenReturn(toolsInDatabase);
            when(dtoMapper.toToolDtoOverview(toolsInDatabase.get(0))).thenReturn(toolsToReturn.get(0));
            when(dtoMapper.toToolDtoOverview(toolsInDatabase.get(1))).thenReturn(toolsToReturn.get(1));

            assertThat(toolService.findAll()).containsExactlyElementsOf(toolsToReturn);
        }
    }

    @Nested @DisplayName("DELETE ONE TOOL FROM PLC BY PLC ID AND TOOL ID")
    class DeleteOneToolFromPlcByPlcIdAndToolId{
        @Nested @DisplayName("PLC IS IN DATABASE")
        class PlcAndToolAreInDatabase{

            @Test @DisplayName("deletes tool from plc")
            void deletesToolFromPlc(){
                Plc plcInDb = Plc.builder()
                        .addTool(
                                Tool.builder().toolNumber(1).id(1L).build())
                        .build();

                when(plcRepository.findByIdFetchTools(1L)).thenReturn(Optional.of(plcInDb));

                toolService.delete(1L,1L);

                verify(plcRepository, times(1)).save(plcCaptor.capture());
                assertThat(plcCaptor.getValue().getTools()).isEmpty();
            }

            @Test @DisplayName("throws ToolNotFoundException when there was no tool with given ID")
            void throwsToolNotFoundExceptionWhenThereWasNoToolWithGivenId(){
                Plc plcInDb = Plc.builder().addTool(Tool.builder().toolNumber(2).id(2L).build()).build();

                when(plcRepository.findByIdFetchTools(1L)).thenReturn(Optional.of(plcInDb));

                assertThrows(ToolNotFoundException.class, () -> toolService.delete(1L,1L));
            }
        }

        @Test @DisplayName("throws PlcNotFoundException when there was no PLC with given ID")
        void throwsPlcNotFoundExceptionWhenThereWasNoPlcWithGivenId(){
            when(plcRepository.findByIdFetchTools(1L)).thenReturn(Optional.empty());

            assertThrows(PlcNotFoundException.class, () -> toolService.delete(1L,1L));
        }
    }

    @Nested @DisplayName("CREATE TOOL")
    class CreateTool{
        @Test @DisplayName("throws PlcNotFoundException when PLC is not in database")
        void throwsPlcNotFoundExceptionWhenPlcIsNotInDatabase(){
            ToolDto.Request.Create toolDto = ToolDto.Request.Create.builder().name("tool").build();
            when(plcRepository.findByIdFetchTools(1L)).thenReturn(Optional.empty());

            assertThrows(PlcNotFoundException.class,() -> toolService.create(1L,toolDto));
        }

        @Test @DisplayName("returns updated tool with toolId")
        void returnsUpdatedToolWithToolId(){
            ToolDto.Request.Create toolDto = ToolDto.Request.Create.builder().toolNumber(1).name("toolName").build();
            Tool newTool = Tool.builder().toolNumber(1).nickName("toolName").build();
            ToolDto.Response.Overview toolDtoToReturn = ToolDto.Response.Overview.builder().toolNumber(1).name("toolName").build();
            Plc plcInDb = Plc.builder().build();

            when(plcRepository.findByIdFetchTools(1L)).thenReturn(Optional.of(plcInDb));
            when(plcRepository.save(any())).then(returnsFirstArg());
            when(dtoMapper.toTool(toolDto)).thenReturn(newTool);
            when(dtoMapper.toToolDtoOverview(newTool)).thenReturn(toolDtoToReturn);

            assertThat(toolService.create(1L,toolDto)).isEqualTo(toolDtoToReturn);
        }

        @Test @DisplayName("throws ToolUniqueConstrainException when tool with same toolNumber already exists")
        void throwsToolUniqueConstrainExceptionWhenToolAlreadyExists(){
            Plc plcInDB = Plc.builder()
                    .id(1L)
                    .addTool(Tool.builder().toolNumber(1).id(1L).build())
                    .build();
            ToolDto.Request.Create toolDto = ToolDto.Request.Create.builder().toolNumber(1).build();
            Tool newTool = Tool.builder().toolNumber(1).build();

            when(plcRepository.findByIdFetchTools(1L)).thenReturn(Optional.of(plcInDB));
            when(dtoMapper.toTool(toolDto)).thenReturn(newTool);

            assertThrows(ToolUniqueConstrainException.class,() ->toolService.create(1L, toolDto));
        }

        @Test @DisplayName("adds tool when everything is ok")
        void addsToolWhenEverythingIsOk(){
            Plc plcInDB = Plc.builder()
                    .id(1L)
                    .addTool(Tool.builder().toolNumber(1).id(1L).build())
                    .build();
            ToolDto.Request.Create toolDto = ToolDto.Request.Create.builder().toolNumber(2).build();
            Tool newTool = Tool.builder().toolNumber(2).build();
            ToolDto.Response.Overview toolDtoToReturn = ToolDto.Response.Overview.builder().toolNumber(2).id(2L).build();

            when(plcRepository.findByIdFetchTools(1L)).thenReturn(Optional.of(plcInDB));
            when(dtoMapper.toTool(toolDto)).thenReturn(newTool);
            when(plcRepository.save(any())).then(returnsFirstArg());
            when(dtoMapper.toToolDtoOverview(newTool)).thenReturn(toolDtoToReturn);

            assertThat(toolService.create(1L, toolDto)).isEqualTo(toolDtoToReturn);
        }
    }

    @Nested @DisplayName("UPDATE TOOL BY PLC ID AND TOOL ID")
    class UpdateToolAttributesByPlcIdAndToolId{
        @Nested @DisplayName("PLC AND TOOL EXIST")
        class PlcAndToolExist{

            @Test @DisplayName("throws ToolUniqueConstrainException when tool number was changed to one which already exists")
            void throwsExceptionWhenToolNumberAlreadyExists(){
                Plc plcInDb = Plc.builder()
                        .id(1L)
                        .addTool(Tool.builder().toolNumber(1).id(1L).build())
                        .addTool(Tool.builder().toolNumber(2).id(2L).build())
                        .build();
                ToolDto.Request.Update toolDto = ToolDto.Request.Update.builder().toolNumber(1).build();
                Tool updatedTool = Tool.builder().toolNumber(1).build();

                when(plcRepository.findByIdFetchTools(1L)).thenReturn(Optional.of(plcInDb));
                when(dtoMapper.toTool(toolDto)).thenReturn(updatedTool);

                assertThrows(ToolUniqueConstrainException.class,() ->toolService.update(1L,2L, toolDto));
            }

            @Test @DisplayName("throws ToolNumberUpdateException when tool number was updated for autodetected tool")
            void throwsExceptionWhenToolNumberWasUpdatedForAutodetectedTool(){
                Plc plcInDb = Plc.builder()
                        .id(1L)
                        .addTool(Tool.builder().toolStatus(ToolStatusType.AUTODETECTED).toolNumber(1).id(1L).build())
                        .build();
                ToolDto.Request.Update toolDto = ToolDto.Request.Update.builder().toolNumber(2).build();
                Tool updatedTool = Tool.builder().toolNumber(2).build();

                when(plcRepository.findByIdFetchTools(1L)).thenReturn(Optional.of(plcInDb));
                when(dtoMapper.toTool(toolDto)).thenReturn(updatedTool);

                assertThrows(ToolNumberUpdateException.class, () -> toolService.update(1L,1L,toolDto));
            }


            @Test @DisplayName("updates tool's attributes")
            void updatesToolsAttributes(){
                Plc plcInDb = Plc.builder()
                        .id(1L)
                        .addTool(Tool.builder().toolStatus(ToolStatusType.AUTODETECTED).toolNumber(1).id(1L).build())
                        .build();
                ToolDto.Request.Update toolDto = ToolDto.Request.Update.builder()
                        .toolNumber(1)
                        .automaticMonitoring(true)
                        .calculateReferenceCurve(true)
                        .name("newName")
                        .numberOfReferenceCycles(10)
                        .stopReaction(StopReactionType.IMMEDIATE)
                        .tolerance(new RelativeToleranceDto(10,10))
                        .build();
                Tool updatedTool = Tool.builder()
                        .toolNumber(1)
                        .automaticMonitoring(true)
                        .calculateReferenceCurve(true)
                        .nickName("newName")
                        .numberOfReferenceCycles(10)
                        .stopReaction(StopReactionType.IMMEDIATE)
                        .tolerance(new RelativeTolerance(10,10))
                        .build();
                ToolDto.Response.Overview toolDtoToReturn = ToolDto.Response.Overview.builder()
                        .toolNumber(1)
                        .automaticMonitoring(true)
                        .calculateReferenceCurve(true)
                        .name("newName")
                        .numberOfReferenceCycles(10)
                        .stopReaction(StopReactionType.IMMEDIATE)
                        .tolerance(new RelativeToleranceDto(10,10))
                        .build();

                when(plcRepository.findByIdFetchTools(1L)).thenReturn(Optional.of(plcInDb));
                when(dtoMapper.toTool(toolDto)).thenReturn(updatedTool);
                when(dtoMapper.toToolDtoOverview(any())).thenReturn(toolDtoToReturn);
                when(plcRepository.save(any())).then(returnsFirstArg());


                assertThat(toolService.update(1L,1L, toolDto)).isEqualTo(toolDtoToReturn);
                verify(plcRepository, times(1)).save(plcCaptor.capture());

                Tool updatedToolStoredToDb = plcCaptor.getValue().getToolById(1L);

                SoftAssertions softAssertions = new SoftAssertions();
                softAssertions.assertThat(updatedToolStoredToDb.getToolNumber()).isEqualTo(updatedTool.getToolNumber());
                softAssertions.assertThat(updatedToolStoredToDb.getAutomaticMonitoring()).isEqualTo(updatedTool.getAutomaticMonitoring());
                softAssertions.assertThat(updatedToolStoredToDb.getCalculateReferenceCurve()).isEqualTo(updatedTool.getCalculateReferenceCurve());
                softAssertions.assertThat(updatedToolStoredToDb.getNickName()).isEqualTo(updatedTool.getNickName());
                softAssertions.assertThat(updatedToolStoredToDb.getNumberOfReferenceCycles()).isEqualTo(updatedTool.getNumberOfReferenceCycles());
                softAssertions.assertThat(updatedToolStoredToDb.getStopReaction()).isEqualTo(updatedTool.getStopReaction());
                softAssertions.assertThat(updatedToolStoredToDb.getTolerance()).isEqualTo(updatedTool.getTolerance());
                softAssertions.assertAll();
            }
        }

        @Test @DisplayName("throws PlcNotFoundException when plc with given ID was not found")
        void throwsExceptionWhenPlcWasNotFound(){
            ToolDto.Request.Update toolDto = ToolDto.Request.Update.builder().build();

            when(plcRepository.findByIdFetchTools(1L)).thenReturn(Optional.empty());

            assertThrows(PlcNotFoundException.class, () -> toolService.update(1L, 1L, toolDto));
        }

        @Test @DisplayName("throws ToolNotFoundException when tool with given ID was not found")
        void throwsExceptionWhenToolWasNotFound(){
            Plc plcInDb = Plc.builder()
                    .id(1L)
                    .addTool(Tool.builder().toolNumber(1).id(1L).build())
                    .build();
            ToolDto.Request.Update toolDto = ToolDto.Request.Update.builder().toolNumber(2).build();

            when(plcRepository.findByIdFetchTools(1L)).thenReturn(Optional.of(plcInDb));

            assertThrows(ToolNotFoundException.class, () -> toolService.update(1L,2L, toolDto));
        }
    }
//
//    @Nested @DisplayName("UPDATES REFERENCE CURVE")
//    class UpdatesReferenceCurve {
//        @Test @DisplayName("throws ToolNotFoundException when tool was not found in database")
//        void throwsToolNotFoundExceptionWhenToolWasNotFoundInDatabase(){
//            Curve newReferenceCurve = new TestCurveBuilder().randomPoints(360).build();
//
//            when(toolRepository.findById(1L)).thenReturn(Optional.empty());
//
//            assertThrows(ToolNotFoundException.class, () -> toolService.updateReferenceCurve(1L, newReferenceCurve));
//        }
//
//        @Test @DisplayName("sets new ReferenceCurve value and sets calculateReferenceCurve to false and saves it to db")
//        void setsNewReferenceCurveValueAndSetsCalculateReferenceCurveToFalseAndSavesItToDb(){
//            Curve newReferenceCurve = new TestCurveBuilder().randomPoints(360).build();
//            Tool toolInDb = new TestToolBuilder().id(1L).calculateReferenceCurve(true).build();
//
//            when(toolRepository.findById(1L)).thenReturn(Optional.of(toolInDb));
//
//            toolService.updateReferenceCurve(1L, newReferenceCurve);
//
//            verify(toolRepository, times(1)).save(toolCaptor.capture());
//
//            SoftAssertions softAssertions = new SoftAssertions();
//            softAssertions.assertThat(toolCaptor.getValue().getReferenceCurve()).isEqualTo(newReferenceCurve);
//            softAssertions.assertThat(toolCaptor.getValue().getCalculateReferenceCurve()).isFalse();
//            softAssertions.assertAll();
//        }
//    }
}
