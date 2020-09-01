package com.siemens.metal_forming.dto;

import com.siemens.metal_forming.enumerated.ToolStatusType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("<= TOOL DTO SPECIFICATION =>")
public class ToolDtoSpec {
    @Nested @DisplayName("CREATE")
    class ToolDtoRequestCreate{
        @Test @DisplayName("is created with tool status MANUALLY_ADDED")
        void isCreatedWithToolStatusManuallyAdded(){
            ToolDto.Request.Create toolDto = ToolDto.Request.Create.builder().build();

            assertThat(toolDto.getToolStatus()).isEqualTo(ToolStatusType.MANUALLY_ADDED);
        }
    }
}
