package com.siemens.metal_forming.dto;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.DisplayName;

@DisplayName("<= ABSOLUTE TOLERANCE DTO SPECIFICATION =>")
public class AbsoluteToleranceDtoSpec extends DtoSpec {
    public AbsoluteToleranceDtoSpec() {
        super(AbsoluteToleranceDto.class);
    }
}
