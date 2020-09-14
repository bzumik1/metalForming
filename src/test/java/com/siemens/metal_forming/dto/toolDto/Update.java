package com.siemens.metal_forming.dto.toolDto;

import com.siemens.metal_forming.dto.DtoSpec;
import com.siemens.metal_forming.dto.ToolDto;
import org.junit.jupiter.api.DisplayName;

@DisplayName("<= TOOL-DTO-REQUEST-UPDATE SPECIFICATION =>")
class Update extends DtoSpec {
    @Override
    public Class getTestedClass() {
        return ToolDto.Request.Update.class;
    }
}
