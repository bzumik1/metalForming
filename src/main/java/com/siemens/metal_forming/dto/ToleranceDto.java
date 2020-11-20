package com.siemens.metal_forming.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.siemens.metal_forming.entity.AbsoluteTolerance;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type",
        defaultImpl = AbsoluteTolerance.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AbsoluteToleranceDto.class, name = "ABSOLUTE"),
        @JsonSubTypes.Type(value = RelativeToleranceDto.class, name = "RELATIVE")
})
public interface ToleranceDto {
}
