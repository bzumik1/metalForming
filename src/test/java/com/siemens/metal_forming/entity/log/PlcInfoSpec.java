package com.siemens.metal_forming.entity.log;

import com.siemens.metal_forming.entity.ImmutableEntitySpec;
import org.junit.jupiter.api.DisplayName;

@DisplayName("<= PLC INFO SPECIFICATION =>")
class PlcInfoSpec extends ImmutableEntitySpec {
    @Override
    public Class<?> getTestedClass() {
        return PlcInfo.class;
    }
}
