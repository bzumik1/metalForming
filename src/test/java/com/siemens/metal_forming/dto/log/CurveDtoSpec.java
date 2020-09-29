package com.siemens.metal_forming.dto.log;

import com.siemens.metal_forming.dto.DtoSpec;
import com.siemens.metal_forming.dto.log.CurveDto;
import org.junit.jupiter.api.DisplayName;

@DisplayName("<= CURVE DTO SPECIFICATION =>")
public class CurveDtoSpec extends DtoSpec {

    public CurveDtoSpec() {
        super(CurveDto.class);
    }
}
