package com.siemens.metal_forming.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siemens.metal_forming.dto.RestDtoMapper;
import com.siemens.metal_forming.dto.log.LogDto;
import com.siemens.metal_forming.entity.log.Log;
import com.siemens.metal_forming.exception.exceptions.LogNotFoundException;
import com.siemens.metal_forming.service.LogService;
import com.siemens.metal_forming.testBuilders.TestLogBuilder;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("<= LOG CONTROLLER SPECIFICATION =>")
@WebMvcTest(controllers = LogController.class)
public class LogControllerSpec {
    @Autowired
    MockMvc mvc;

    @MockBean
    LogService logService;

    @MockBean
    RestDtoMapper dtoMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Captor
    ArgumentCaptor<Set<Long>> idsCaptor;

    final String path = "/api/logs";

    @BeforeEach
    void initialize(){
        Mockito.reset(dtoMapper, logService);
    }

    @Nested @DisplayName("GET ALL LOGS FOR ONE TOOL")
    class GetAllLogsForOneToolId{

        @Test @DisplayName("returns 200 Ok when everything is ok")
        void returnsOkWhenEverythingIsOk() throws Exception {
            mvc.perform(get(path)
                    .param("tool-id", "1"))
                    .andExpect(status().isOk());
        }

        @Test @DisplayName("returns found logs as DTO")
        void triggersDtoMapper() throws Exception {
            Log log1 = new TestLogBuilder().id(1L).build();
            Log log2 = new TestLogBuilder().id(2L).build();
            LogDto.Response.Overview logDto1 = LogDto.Response.Overview.builder().id(1L).build();
            LogDto.Response.Overview logDto2 = LogDto.Response.Overview.builder().id(2L).build();

            when(logService.findAllByToolId(1L)).thenReturn(List.of(log1, log2));
            when(dtoMapper.toLogDtoOverview(log1)).thenReturn(logDto1);
            when(dtoMapper.toLogDtoOverview(log2)).thenReturn(logDto2);

            MvcResult mvcResult = mvc.perform(get(path)
                    .param("tool-id","1")).andReturn();

            assertThat(mvcResult.getResponse().getContentAsString())
                    .contains(objectMapper.writeValueAsString(logDto1))
                    .contains(objectMapper.writeValueAsString(logDto2));
        }

        @ParameterizedTest
        @ValueSource(strings = {"http://localhost:4200", "http://localhost:4201","http://random-page.com"})
        @DisplayName("allows CORS from all origins")
        void allowsCorsFromAllOrigins(String origin) throws Exception {
            mvc.perform(options(path)
                    .header("Access-Control-Request-Method", "GET")
                    .header("Origin", origin))
                    .andExpect(status().isOk()); //when cors doesn't work returns 403 Forbidden
        }
    }

    @Nested @DisplayName("GET LOG BY ID")
    class GetLogById{

        @Test @DisplayName("returns 200 Ok when everything is ok")
        void returnsOkWhenEverythingIsOk() throws Exception {
            mvc.perform(get(path +"/{id}",1L))
                    .andExpect(status().isOk());
        }

        @Test @DisplayName("returns found log as DTO")
        void triggersDtoMapper() throws Exception {
            Log log1 = new TestLogBuilder().id(1L).build();
            LogDto.Response.Detail logDto1 = LogDto.Response.Detail.builder().id(1L).build();

            when(logService.findById(1L)).thenReturn(log1);
            when(dtoMapper.toLogDtoDetail(log1)).thenReturn(logDto1);

            MvcResult mvcResult = mvc.perform(get(path + "/{id}",1L))
                    .andReturn();

            assertThat(mvcResult.getResponse().getContentAsString())
                    .contains(objectMapper.writeValueAsString(logDto1));
        }

        @Test @DisplayName("returns 404 not found when log with given id was not found")
        void returnsNotFoundWhenLogDoesNotExist() throws Exception {
            Mockito.when(logService.findById(1L)).thenThrow(new LogNotFoundException(1L));

            mvc.perform(get(path + "/{id}",1L))
                    .andExpect(status().isNotFound());
        }

        @Test @DisplayName("returns correct body when log was not found")
        void returnsCorrectBodyWhenLogWasNotFound() throws Exception {
            Mockito.when(logService.findById(1L)).thenThrow(new LogNotFoundException(1L));

            MvcResult mvcResult = mvc.perform(get(path + "/{id}",1L))
                    .andReturn();

            assertThat(mvcResult.getResponse().getContentAsString())
                    .contains("not found")
                    .contains("1");
        }

        @ParameterizedTest
        @ValueSource(strings = {"http://localhost:4200", "http://localhost:4201","http://random-page.com"})
        @DisplayName("allows CORS from all origins")
        void allowsCorsFromAllOrigins(String origin) throws Exception {
            mvc.perform(options(path + "/{id}",1L)
                    .header("Access-Control-Request-Method", "GET")
                    .header("Origin", origin))
                    .andExpect(status().isOk()); //when cors doesn't work returns 403 Forbidden
        }
    }

    @Nested @DisplayName("DELETE ALL LOGS WITH GIVEN IDS")
    class DeleteAllLogsWithGivenIds{
        @Test @DisplayName("triggers logService.delete(ids)")
        void triggersLogService() throws Exception {
            Set<Long> idsToDelete = Set.of(1L, 2L, 3L);
            String idsToDeleteJson = objectMapper.writeValueAsString(idsToDelete);

            mvc.perform(delete(path)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(idsToDeleteJson))
                    .andExpect(status().isOk());

            verify(logService,times(1)).delete(idsToDelete);
        }

        @Test @DisplayName("returns 200 Ok when everything was ok")
        void returnsOkWhenEverythingWasOk() throws Exception {
            Set<Long> idsToDelete = Set.of(1L, 2L, 3L);
            String idsToDeleteJson = objectMapper.writeValueAsString(idsToDelete);

            mvc.perform(delete(path)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(idsToDeleteJson))
                    .andExpect(status().isOk());
        }

        @Test @DisplayName("returns 404 Not Found when at least one Log which should be deleted was not found")
        void returnsNotFoundWhenLogWhichShouldBeDeletedWasNotFound() throws Exception {
            Set<Long> idsToDelete = Set.of(1L, 2L, 3L);
            String idsToDeleteJson = objectMapper.writeValueAsString(idsToDelete);

            doThrow(new LogNotFoundException(Set.of(1L, 3L))).when(logService).delete(idsToDelete);

            mvc.perform(delete(path)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(idsToDeleteJson))
                    .andExpect(status().isNotFound());
        }

        @Test @DisplayName("returns proper api exception when PLC which should be deleted was not found")
        void returnsProperApiExceptionWhenPlcWhichShouldBeDeletedWasNotFound() throws Exception {
            Set<Long> idsToDelete = Set.of(1L, 2L, 3L);
            String idsToDeleteJson = objectMapper.writeValueAsString(idsToDelete);

            doThrow(new LogNotFoundException(Set.of(1L, 3L))).when(logService).delete(idsToDelete);

            MvcResult mvcResult = mvc.perform(delete(path)
                                                    .contentType(MediaType.APPLICATION_JSON)
                                                    .content(idsToDeleteJson))
                                        .andReturn();

            assertThat(mvcResult.getResponse().getContentAsString()).contains("were not found").contains("1").contains("3");
        }

        @Test @DisplayName("ignores duplicate ids")
        void ignoresDuplicateIds() throws Exception {
            mvc.perform(delete(path)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("[1,2,3,3,2,4]"))
                    .andExpect(status().isOk());

            verify(logService).delete(idsCaptor.capture());

            assertThat(idsCaptor.getValue()).isEqualTo(Set.of(1L,2L,3L,4L));
        }

        @ParameterizedTest
        @ValueSource(strings = {"http://localhost:4200", "http://localhost:4201","http://random-page.com"})
        @DisplayName("allows CORS from all origins")
        void allowsCorsFromAllOrigins(String origin) throws Exception {
            mvc.perform(options(path)
                    .header("Access-Control-Request-Method", "DELETE")
                    .header("Origin", origin))
                    .andExpect(status().isOk()); //when cors doesn't work returns 403 Forbidden
        }
    }

    @Nested @DisplayName("UPDATE COMMENT OF LOG")
    class UpdateCommentOfLog{
        @Test @DisplayName("triggers logService.updateComment")
        void triggersLogService() throws Exception {
            LogDto.Request.Update logDto = LogDto.Request.Update.builder().comment("new comment").build();
            String logDtoJson = objectMapper.writeValueAsString(logDto);

            mvc.perform(put(path + "/{id}",1L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(logDtoJson));

            Mockito.verify(logService, times(1))
                    .updateComment(1L,"new comment");
        }

        @Test @DisplayName("returns 200 ok when everything was ok")
        void returnsCreatedWhenEverythingWasOk() throws Exception {
            LogDto.Request.Update logDto = LogDto.Request.Update.builder().comment("new comment").build();
            String logDtoJson = objectMapper.writeValueAsString(logDto);

            mvc.perform(put(path + "/{id}",1L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(logDtoJson))
                    .andExpect(status().isOk());
        }


        @Test @DisplayName("returns 404 not found when log with given id was not found")
        void returnsNotFoundWhenLogDoesNotExist() throws Exception {
            LogDto.Request.Update logDto = LogDto.Request.Update.builder().comment("new comment").build();
            String logDtoJson = objectMapper.writeValueAsString(logDto);

            Mockito.when(logService.updateComment(1L,"new comment")).thenThrow(new LogNotFoundException(1L));

            mvc.perform(put(path + "/{id}",1L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(logDtoJson))
                    .andExpect(status().isNotFound());
        }

        @Test @DisplayName("returns correct body when log was not found")
        void returnsCorrectBodyWhenLogWasNotFound() throws Exception {
            LogDto.Request.Update logDto = LogDto.Request.Update.builder().comment("new comment").build();
            String logDtoJson = objectMapper.writeValueAsString(logDto);

            Mockito.when(logService.updateComment(1L,"new comment")).thenThrow(new LogNotFoundException(1L));

            MvcResult mvcResult = mvc.perform(put(path + "/{id}",1L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(logDtoJson))
                    .andReturn();

            assertThat(mvcResult.getResponse().getContentAsString())
                    .contains("not found")
                    .contains("1");
        }

        @Test @DisplayName("returns updated log")
        void returnsUpdatedLog() throws Exception{
            LogDto.Request.Update logDto = LogDto.Request.Update.builder().comment("new comment").build();
            String logDtoJson = objectMapper.writeValueAsString(logDto);
            Log logAfterUpdating = Log.builder().id(1L).comment("new comment").build();
            LogDto.Response.Overview logDtoWhichShouldBeReturned = LogDto.Response.Overview.builder().id(1L).comment("new comment").build();

            when(logService.updateComment(1L,"new comment")).thenReturn(logAfterUpdating);
            when(dtoMapper.toLogDtoOverview(logAfterUpdating)).thenReturn(logDtoWhichShouldBeReturned);

            MvcResult mvcResult = mvc.perform(put(path + "/{id}", 1L)
                                                    .contentType(MediaType.APPLICATION_JSON)
                                                    .content(logDtoJson))
                                        .andReturn();

            assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(logDtoWhichShouldBeReturned));

        }

        @ParameterizedTest
        @ValueSource(strings = {"http://localhost:4200", "http://localhost:4201","http://random-page.com"})
        @DisplayName("allows CORS from all origins")
        void allowsCorsFromAllOrigins(String origin) throws Exception {
            mvc.perform(options(path + "/{id}",1L)
                    .header("Access-Control-Request-Method", "PUT")
                    .header("Origin", origin))
                    .andExpect(status().isOk()); //when cors doesn't work returns 403 Forbidden
        }
    }
}
