package com.siemens.metal_forming.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siemens.metal_forming.dto.DtoMapper;
import com.siemens.metal_forming.dto.PlcDto;
import com.siemens.metal_forming.dto.ToolDto;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.enumerated.StopReactionType;
import com.siemens.metal_forming.exception.exceptions.PlcNotFoundException;
import com.siemens.metal_forming.exception.exceptions.PlcUniqueConstrainException;
import com.siemens.metal_forming.exception.exceptions.ToolNotFoundException;
import com.siemens.metal_forming.exception.exceptions.ToolUniqueConstrainException;
import com.siemens.metal_forming.service.ToolService;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ToolController.class)
@DisplayName("<= TOOL CONTROLLER SPECIFICATION =>")
public class ToolControllerSpec {
    @Autowired
    MockMvc mvc;

    @MockBean
    ToolService toolService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    DtoMapper dtoMapper;

    private final String path = "/plcs/{plcId}/tools";

    @BeforeEach
    void initialize(){
        Mockito.reset(toolService);
        Mockito.reset(dtoMapper);
    }

    @Nested @DisplayName("FIND ALL TOOLS FROM ONE PLC")
    class FindAllToolsFromOnePlc{
        @Test @DisplayName("triggers toolService.findAll")
        void triggersToolService() throws Exception {
            mvc.perform(get(path,1L));
            Mockito.verify(toolService,Mockito.times(1)).findAll(1L);
        }

        @Test @DisplayName("returns 200 Ok when everything is ok")
        void returnsOkWhenEverythingIsOk() throws Exception {
            mvc.perform(get(path,1L)).andExpect(status().isOk());
        }

       @Test @DisplayName("returns 404 Not found when PLC with given ID was not in database")
        void returnsNotFoundWhenPlcWasNotInDatabase() throws Exception {
            Mockito.when(toolService.findAll(1L)).thenThrow(new PlcNotFoundException(1L));

            mvc.perform(get(path,1L)).andExpect(status().isNotFound());
       }

       @Test @DisplayName("triggers dtoMapper and transforms found tools to proper DTOs")
        void triggersDtoMapperAndTransformsToolsIntoDtos() throws Exception {
            List<Tool> tools = List.of(new Tool(),new Tool(), new Tool());
            Mockito.when(toolService.findAll(1L)).thenReturn(tools);

            mvc.perform(get(path,1L));

            Mockito.verify(dtoMapper,Mockito.times(tools.size())).toToolDtoOverview(any(Tool.class));
       }
    }

    @Nested @DisplayName("DELETE TOOL BY PLC ID AND TOOL ID")
    class DeleteToolByPlcIdAndToolId{
        private final String deletePath = path+"/{toolId}";

        @Test @DisplayName("returns 404 Not found when PLC with given ID was not in database")
        void returnsNotFoundWhenPlcWithGivenIdWasNotInDatabase() throws Exception {
            Mockito.doThrow(new PlcNotFoundException(1L)).when(toolService).delete(1L,1L);

            mvc.perform(delete(deletePath,1L,1L)).andExpect(status().isNotFound());
        }

        @Test @DisplayName("returns 404 Not found when tool with given ID was not in database")
        void returnsNotFoundWhenToolWithGivenIdWasNotInDatabase() throws Exception {
            Mockito.doThrow(new ToolNotFoundException(1L)).when(toolService).delete(1L,1L);

            mvc.perform(delete(deletePath,1L,1L)).andExpect(status().isNotFound());
        }

        @Test @DisplayName("triggers toolService.deleteByPlcIdAndToolId")
        void triggersToolService() throws Exception {
            mvc.perform(delete(deletePath,1L,1L));

            Mockito.verify(toolService,Mockito.times(1)).delete(1L,1L);
        }

        @Test @DisplayName("returns 200 Ok when everything was ok")
        void returnsOkWhenEverythingWasOk() throws Exception {
            mvc.perform(delete(deletePath,1L,1L)).andExpect(status().isOk());
        }
    }

    @Nested @DisplayName("CREATE TOOL BY PLC ID")
    class CreateTool{
        ToolDto.Request.Create validToolDto;

        @BeforeEach
        void initialize(){
            validToolDto = ToolDto.Request.Create.builder().toolNumber(1).numberOfReferenceCycles(10).stopReaction(StopReactionType.IMMEDIATE).automaticMonitoring(true).build();
        }

        @Nested @DisplayName("VALID TOOL")
        class ValidTool{
            String validToolDtoJson;

            @BeforeEach
            void initializeForValidTool() throws JsonProcessingException {
                validToolDtoJson = objectMapper.writeValueAsString(validToolDto);
                Mockito.when(dtoMapper.toTool(any(ToolDto.Request.Create.class))).thenReturn(Tool.builder()
                        .toolNumber(1)
                        .numberOfReferenceCycles(10)
                        .stopReaction(StopReactionType.IMMEDIATE)
                        .automaticMonitoring(true)
                        .build());
            }

            @Test @DisplayName("triggers toolService.createByPlcId() when tool was valid")
            void triggersToolServiceWhenValid() throws Exception {
                mvc.perform(post(path,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validToolDtoJson));

                Mockito.verify(toolService,Mockito.times(1)).create(anyLong(),any(Tool.class));
            }

            @Test @DisplayName("returns 201 Created when everything was ok")
            void returnsCreatedWhenEverythingWasOk() throws Exception {
                mvc.perform(post(path,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validToolDtoJson))
                        .andExpect(status().isCreated());
            }

            @Test @DisplayName("triggers DtoMapper and transforms incoming DTO to correct tool")
            void transformsIncomingDtoToProperTool() throws Exception {
                mvc.perform(post(path,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validToolDtoJson));

                Mockito.verify(dtoMapper,times(1)).toTool(any(ToolDto.Request.Create.class));
            }

            @Test @DisplayName("returns 409 Conflict when tool with same tool number already exists")
            void returnsConflictWhenToolWithSameToolNumberAlreadyExists() throws Exception {
                Mockito.when(toolService.create(anyLong(),any(Tool.class))).thenThrow(new ToolUniqueConstrainException(1));

                mvc.perform(post(path,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validToolDtoJson))
                        .andExpect(status().isConflict());
            }

            @Test @DisplayName("returns correct body with api exception when tool already exists")
            void returnsCorrectBodyWithApiExceptionWhenToolAlreadyExists() throws Exception {
                Mockito.when(toolService.create(anyLong(),any(Tool.class))).thenThrow(new ToolUniqueConstrainException(1));

                MvcResult mvcResult = mvc.perform(post(path,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validToolDtoJson))
                        .andReturn();

                assertThat(mvcResult.getResponse().getContentAsString())
                        .contains("already exists")
                        .contains("CONFLICT");
            }

            @Test @DisplayName("triggers DtoMapper and transforms newly created tool to proper DTO")
            void triggersDtoMapperAndTransformsToolToDto() throws Exception {
                Tool toolToBeReturn = dtoMapper.toTool(validToolDto).toBuilder().id(1L).build();
                Mockito.when(toolService.create(anyLong(),any(Tool.class))).thenReturn(toolToBeReturn);


                mvc.perform(post(path,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validToolDtoJson));

                Mockito.verify(dtoMapper,times(1)).toToolDtoOverview(any(Tool.class));
            }
        }

        @Nested @DisplayName("INVALID TOOL")
        class InvalidTool{
            ToolDto.Request.Create invalidToolDto;
            String invalidToolJson;

            @BeforeEach
            void initializeForInvalidTool() throws JsonProcessingException {
                invalidToolDto = validToolDto.toBuilder().toolNumber(null).build();
                invalidToolJson = objectMapper.writeValueAsString(invalidToolDto);
            }
            @Test @DisplayName("returns 400 Bad Request when tool is invalid")
            void returnsBadRequestWhenToolIsInvalid() throws Exception {
                mvc.perform(post(path,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidToolJson))
                        .andExpect(status().isBadRequest());
            }

            @Test @DisplayName("returns correct body with api exception when tool is invalid")
            void returnsCorrectBodyWithApiExceptionWhenToolIsInvalid() throws Exception {
                MvcResult mvcResult = mvc.perform(post(path,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidToolJson))
                        .andReturn();

                assertThat(mvcResult.getResponse().getContentAsString())
                        .contains("Tool number must be filled")
                        .contains("BAD_REQUEST");
            }
        }
    }

    @Nested @DisplayName("UPDATE TOOL BY PLC ID AND TOOL ID")
    class UpdateTool{
        ToolDto.Request.Update validToolDto;
        final String updatePath = path+"/{id}";

        @BeforeEach
        void initialize(){
            validToolDto = ToolDto.Request.Update.builder()
                    .toolNumber(1)
                    .name("toolName")
                    .automaticMonitoring(true)
                    .numberOfReferenceCycles(10)
                    .stopReaction(StopReactionType.IMMEDIATE)
                    .build();
        }

        @Nested @DisplayName("VALID TOOL DTO")
        class ValidTool{
            String validToolDtoJson;

            @BeforeEach
            void initializeForValidTool() throws JsonProcessingException {
                validToolDtoJson = objectMapper.writeValueAsString(validToolDto);
            }

            @Test @DisplayName("triggers toolService when tool was valid")
            void triggersToolServiceWhenValid() throws Exception {
                mvc.perform(put(updatePath,1L,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validToolDtoJson));

                Mockito.verify(toolService, times(1))
                        .update(anyLong(),anyLong(),any());
            }

            @Test @DisplayName("returns 200 Ok when everything was ok")
            void returnsCreatedWhenEverythingWasOk() throws Exception {
                mvc.perform(put(updatePath,1L,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validToolDtoJson))
                        .andExpect(status().isOk());
            }

            @Test @DisplayName("returns 409 Conflict when tool with same tool number already exists")
            void returnsConflictWhenToolWithSameToolNumberAlreadyExists() throws Exception {
                Mockito.when(toolService.update(anyLong(),anyLong(),any())).thenThrow(new ToolUniqueConstrainException(validToolDto.getToolNumber()));

                mvc.perform(put(updatePath,1L,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validToolDtoJson))
                        .andExpect(status().isConflict());
            }

            @Test @DisplayName("returns 404 Not found when tool with given id was not found")
            void returnsNotFoundWhenToolDoesNotExist() throws Exception {
                Mockito.when(toolService.update(anyLong(),anyLong(),any())).thenThrow(new ToolNotFoundException(validToolDto.getToolNumber()));

                mvc.perform(put(updatePath,1L,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validToolDtoJson))
                        .andExpect(status().isNotFound());
            }

            @Test @DisplayName("returns 404 Not found when plc with given id was not found")
            void returnsNotFoundWhenPlcDoesNotExist() throws Exception {
                Mockito.when(toolService.update(anyLong(),anyLong(),any())).thenThrow(new PlcNotFoundException(2L));

                mvc.perform(put(updatePath,1L,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validToolDtoJson))
                        .andExpect(status().isNotFound());
            }

            @Test @DisplayName("returns correct body with api exception when tool already exists")
            void returnsCorrectBodyWithApiExceptionWhenToolAlreadyExists() throws Exception {
                Mockito.when(toolService.update(anyLong(),anyLong(),any())).thenThrow(new ToolUniqueConstrainException(validToolDto.getToolNumber()));

                MvcResult mvcResult = mvc.perform(put(updatePath,1L,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validToolDtoJson))
                        .andReturn();

                assertThat(mvcResult.getResponse().getContentAsString())
                        .contains("already exists")
                        .contains("CONFLICT");
            }

            @Test @DisplayName("triggers DtoMapper and transforms updated tool to proper DTO")
            void triggersDtoMapperAndTransformsUpdatedToolToDto() throws Exception {
                Tool toolToReturn = new Tool();
                Mockito.when(toolService.update(anyLong(),anyLong(),any())).thenReturn(toolToReturn);


                mvc.perform(put(updatePath,1L,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validToolDtoJson));

                Mockito.verify(dtoMapper,times(1)).toToolDtoOverview(any());
            }
        }


        @Nested @DisplayName("INVALID TOOL DTO")
        class InvalidPlc{
            String invalidToolJson;
            @BeforeEach
            void initializeForInvalidTool() throws JsonProcessingException {
                ToolDto.Request.Update invalidToolDto = validToolDto.toBuilder().toolNumber(null).build();
                invalidToolJson = objectMapper.writeValueAsString(invalidToolDto);
            }

            @Test @DisplayName("returns 400 Bad Request when tool is invalid")
            void returnsBadRequestWhenToolIsInvalid() throws Exception {
                mvc.perform(put(updatePath,1L,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidToolJson))
                        .andExpect(status().isBadRequest());
            }

            @Test @DisplayName("returns correct body with api exception when PLC is invalid")
            void returnsCorrectBodyWithApiExceptionWhenPlcIsInvalid() throws Exception {

                MvcResult mvcResult = mvc.perform(put(updatePath,1L,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidToolJson))
                        .andReturn();

                assertThat(mvcResult.getResponse().getContentAsString())
                        .contains("Tool number must be filled")
                        .contains("BAD_REQUEST");
            }
        }
    }
}