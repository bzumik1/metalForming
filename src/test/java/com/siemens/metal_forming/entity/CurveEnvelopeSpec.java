package com.siemens.metal_forming.entity;

import com.siemens.metal_forming.entity.abstractSpec.ImmutableEntitySpec;
import org.junit.jupiter.api.DisplayName;

@DisplayName("<= CURVE ENVELOPE SPECIFICATION =>")
public class CurveEnvelopeSpec extends ImmutableEntitySpec {

    CurveEnvelopeSpec(){
        super(CurveEnvelope.class);
    }

}
