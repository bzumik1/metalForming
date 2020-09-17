package com.siemens.metal_forming.dto;

import com.siemens.metal_forming.dto.DtoSpec;
import com.siemens.metal_forming.dto.PlcDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

@DisplayName("<= PLC DTO SPEC =>")
class PlcDtoSpec {
    @Nested @DisplayName("REQUEST")
    class Request{
        @Nested @DisplayName("CREATE")
        class Create extends DtoSpec{
            public Create() {
                super(PlcDto.Request.Create.class);
            }
        }

        @Nested @DisplayName("UPDATE")
        class Update extends DtoSpec{
            public Update() {
                super(PlcDto.Request.Update.class);
            }
        }
    }

    @Nested @DisplayName("RESPONSE")
    class Response{
        @Nested @DisplayName("OVERVIEW")
        class Overview extends DtoSpec{
            public Overview() {
                super(PlcDto.Response.Overview.class);
            }
        }
    }
}
