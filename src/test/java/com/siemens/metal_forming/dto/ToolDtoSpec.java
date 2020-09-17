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

    }

    @Nested @DisplayName("REQUEST")
    class Request{
        @Nested @DisplayName("CREATE")
        class Create extends DtoSpec{
            public Create() {
                super(ToolDto.Request.Create.class);
            }

            @Test @DisplayName("is created with tool status MANUALLY_ADDED")
            void isCreatedWithToolStatusManuallyAdded(){
                ToolDto.Request.Create toolDto = ToolDto.Request.Create.builder().build();

                assertThat(toolDto.getToolStatus()).isEqualTo(ToolStatusType.MANUALLY_ADDED);
            }
        }

        @Nested @DisplayName("UPDATE")
        class Update extends DtoSpec{
            public Update() {
                super(ToolDto.Request.Update.class);
            }
        }
    }

    @Nested @DisplayName("RESPONSE")
    class Response{
        @Nested @DisplayName("OVERVIEW")
        class Overview extends DtoSpec{
            public Overview() {
                super(ToolDto.Response.Overview.class);
            }
        }
    }
}
