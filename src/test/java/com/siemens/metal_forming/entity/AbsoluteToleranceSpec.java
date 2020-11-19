package com.siemens.metal_forming.entity;

import com.siemens.metal_forming.entity.AbsoluteTolerance;
import com.siemens.metal_forming.entity.abstractSpec.EntitySpec;
import org.junit.jupiter.api.DisplayName;

@DisplayName("<= ABSOLUTE TOLERANCE SPECIFICATION =>")
public class AbsoluteToleranceSpec extends EntitySpec {
    public AbsoluteToleranceSpec() {
        super(AbsoluteTolerance.class);
    }
}
