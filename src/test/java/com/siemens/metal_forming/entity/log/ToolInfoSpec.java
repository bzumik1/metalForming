package com.siemens.metal_forming.entity.log;

import com.siemens.metal_forming.entity.abstractSpec.ImmutableEntitySpec;
import org.junit.jupiter.api.DisplayName;

@DisplayName("<= TOOL INFO SPECIFICATION =>")
public class ToolInfoSpec extends ImmutableEntitySpec {
    public ToolInfoSpec() {
        super(ToolInfo.class);
    }
}
