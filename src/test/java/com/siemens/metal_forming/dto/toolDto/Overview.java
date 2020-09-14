package com.siemens.metal_forming.dto.toolDto;

import com.siemens.metal_forming.dto.DtoSpec;
import com.siemens.metal_forming.dto.ToolDto;
import org.junit.jupiter.api.DisplayName;

@DisplayName("<= TOOL-DTO-RESPONSE-OVERVIEW SPECIFICATION =>")
class Overview extends DtoSpec {
    @Override
    public Class getTestedClass() {
        return ToolDto.Response.Overview.class;
    }
}
