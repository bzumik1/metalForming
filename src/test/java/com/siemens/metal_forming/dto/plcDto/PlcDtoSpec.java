package com.siemens.metal_forming.dto.plcDto;

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
            @Override
            public Class getTestedClass() {
                return PlcDto.Request.Create.class;
            }
        }

        @Nested @DisplayName("UPDATE")
        class Update extends DtoSpec{
            @Override
            public Class getTestedClass() {
                return PlcDto.Request.Update.class;
            }
        }
    }

    @Nested @DisplayName("RESPONSE")
    class Response{
        @Nested @DisplayName("OVERVIEW")
        class Overview extends DtoSpec{
            @Override
            public Class getTestedClass() {
                return PlcDto.Response.Overview.class;
            }
        }
    }
}
