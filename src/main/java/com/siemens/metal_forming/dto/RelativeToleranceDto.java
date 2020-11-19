package com.siemens.metal_forming.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Value @Builder(toBuilder = true)
public class RelativeToleranceDto {
    @Min(value = 0, message = "Torque tolerance in percentage must be greater or equal to 0")
    @Max(value = 100, message = "Torque tolerance in percentage must be smaller or equal to 100")
    float torqueTolerance;
    @Min(value = 0, message = "Speed tolerance in percentage must be greater or equal to 0")
    @Max(value = 100, message = "Speed tolerance in percentage must be smaller or equal to 100")
    float speedTolerance;
}
