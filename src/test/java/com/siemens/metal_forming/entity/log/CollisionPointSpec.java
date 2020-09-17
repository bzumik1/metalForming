package com.siemens.metal_forming.entity.log;

import com.siemens.metal_forming.entity.abstractSpec.ImmutableEntitySpec;
import org.junit.jupiter.api.DisplayName;

@DisplayName("<= COLLISION POINT SPECIFICATION =>")
public class CollisionPointSpec extends ImmutableEntitySpec {

    public CollisionPointSpec() {
        super(CollisionPoint.class);
    }
}
