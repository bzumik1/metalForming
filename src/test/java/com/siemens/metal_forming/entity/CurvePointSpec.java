package com.siemens.metal_forming.entity;

import com.siemens.metal_forming.entity.abstractSpec.ImmutableEntitySpec;
import org.junit.jupiter.api.DisplayName;

@DisplayName("<= CURVE POINT SPECIFICATION =>")
public class CurvePointSpec extends ImmutableEntitySpec {
    public CurvePointSpec() {
        super(CurvePoint.class);
    }
}
