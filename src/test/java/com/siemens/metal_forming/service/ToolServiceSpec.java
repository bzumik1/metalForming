package com.siemens.metal_forming.service;

import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.repository.ToolRepository;
import com.siemens.metal_forming.service.impl.ToolServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("<= TOOL SERVICE SPECIFICATION =>")
class ToolServiceSpec {
    private ToolRepository toolRepository;
    private ToolService toolService;

    @BeforeEach
    void initialize(){
        toolRepository = Mockito.mock(ToolRepository.class);
        toolService = new ToolServiceImpl(toolRepository);
    }
    @Nested @DisplayName("FIND ALL BY PLC ID")
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
    }
}
