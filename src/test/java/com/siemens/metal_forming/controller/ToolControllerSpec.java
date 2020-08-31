package com.siemens.metal_forming.controller;

import com.siemens.metal_forming.dto.DtoMapper;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.exception.exceptions.PlcNotFoundException;
import com.siemens.metal_forming.exception.exceptions.ToolNotFoundException;
import com.siemens.metal_forming.service.ToolService;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ToolController.class)
@DisplayName("<= TOOL CONTROLLER SPECIFICATION =>")
public class ToolControllerSpec {
    @Autowired
    MockMvc mvc;

    @MockBean
    ToolService toolService;

    @MockBean
    DtoMapper dtoMapper;

    @BeforeEach
    void initialize(){
        Mockito.reset(toolService);
    }

    private final String path = "/plcs/{plcId}/tools";

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
            Mockito.doThrow(new PlcNotFoundException(1L)).when(toolService).deleteByPlcIdAndToolId(1L,1L);

            mvc.perform(delete(deletePath,1L,1L)).andExpect(status().isNotFound());
        }

        @Test @DisplayName("returns 404 Not found when tool with given ID was not in database")
        void returnsNotFoundWhenToolWithGivenIdWasNotInDatabase() throws Exception {
            Mockito.doThrow(new ToolNotFoundException(1L)).when(toolService).deleteByPlcIdAndToolId(1L,1L);

            mvc.perform(delete(deletePath,1L,1L)).andExpect(status().isNotFound());
        }

        @Test @DisplayName("triggers toolService.deleteByPlcIdAndToolId")
        void triggersToolService() throws Exception {
            mvc.perform(delete(deletePath,1L,1L));

            Mockito.verify(toolService,Mockito.times(1)).deleteByPlcIdAndToolId(1L,1L);
        }

        @Test @DisplayName("returns 200 Ok when everything was ok")
        void returnsOkWhenEverythingWasOk() throws Exception {
            mvc.perform(delete(deletePath,1L,1L)).andExpect(status().isOk());
        }
    }
}
