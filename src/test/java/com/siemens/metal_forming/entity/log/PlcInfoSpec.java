package com.siemens.metal_forming.entity.log;

import com.siemens.metal_forming.entity.abstractSpec.ImmutableEntitySpec;
import org.junit.jupiter.api.DisplayName;

@DisplayName("<= PLC INFO SPECIFICATION =>")
class PlcInfoSpec extends ImmutableEntitySpec {
    public PlcInfoSpec() {
        super(PlcInfo.class);
    }
}
