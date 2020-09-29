package com.siemens.metal_forming.dto.log;

import com.siemens.metal_forming.dto.DtoSpec;
import com.siemens.metal_forming.dto.log.LogDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

@DisplayName("<= LOG REPOSITORY SPEC =>")
public class LogDtoSpec {
    @Nested @DisplayName("REQUEST")
    class Request{
        @Nested @DisplayName("COMMENT")
        class Comment extends DtoSpec {
            public Comment() {
                super(LogDto.Request.Comment.class);
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
