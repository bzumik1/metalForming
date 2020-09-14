package com.siemens.metal_forming.dto.toolDto;

import com.siemens.metal_forming.dto.DtoSpec;
import com.siemens.metal_forming.dto.ToolDto;
import org.junit.jupiter.api.DisplayName;

@DisplayName("<= TOOL-DTO-REQUEST-CREATE SPECIFICATION =>")
class Create extends DtoSpec {
    @Override
    public Class getTestedClass() {
        return ToolDto.Request.Create.class;
    }
}
