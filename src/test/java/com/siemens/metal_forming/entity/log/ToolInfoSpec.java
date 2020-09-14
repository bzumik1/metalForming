package com.siemens.metal_forming.entity.log;

import com.siemens.metal_forming.entity.ImmutableEntitySpec;
import org.junit.jupiter.api.DisplayName;

@DisplayName("<= TOOL INFO SPECIFICATION =>")
public class ToolInfoSpec extends ImmutableEntitySpec {
    @Override
    public Class<?> getTestedClass() {
        return ToolInfo.class;
    }
}
