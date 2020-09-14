package com.siemens.metal_forming.entity;

import org.junit.jupiter.api.DisplayName;

@DisplayName("<= CURVE ENVELOPE SPECIFICATION =>")
public class CurveEnvelopeSpec extends ImmutableEntitySpec{

    @Override
    public Class getTestedClass() {
        return CurveEnvelope.class;
    }

}
