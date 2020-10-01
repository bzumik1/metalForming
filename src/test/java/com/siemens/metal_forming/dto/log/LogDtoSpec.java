package com.siemens.metal_forming.dto.log;

import com.siemens.metal_forming.dto.DtoSpec;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

@DisplayName("<= LOG REPOSITORY SPEC =>")
public class LogDtoSpec {
    @Nested @DisplayName("REQUEST")
    class Request{
        @Nested @DisplayName("COMMENT")
        class Update extends DtoSpec {
            public Update() {
                super(LogDto.Request.Update.class);
            }
        }
    }

    @Nested @DisplayName("RESPONSE")
    class Response{
        @Nested @DisplayName("OVERVIEW")
        class Overview extends DtoSpec{
            public Overview() {
                super(LogDto.Response.Overview.class);
            }
        }
    }
}
