package com.siemens.metal_forming.entity.log;

import com.siemens.metal_forming.entity.ImmutableEntitySpec;
import org.junit.jupiter.api.DisplayName;

@DisplayName("<= COLLISION POINT SPECIFICATION =>")
public class CollisionPointSpec extends ImmutableEntitySpec {
    @Override
    public Class<?> getTestedClass() {
        return CollisionPoint.class;
    }
}
