package com.siemens.metal_forming.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Min;

@Value @Builder(toBuilder = true) @AllArgsConstructor
public class AbsoluteToleranceDto implements ToleranceDto{
    @Min(value = 0, message = "Torque tolerance must be greater or equal to 0")
    float torqueTolerance;
    @Min(value = 0, message = "Speed tolerance must be greater or equal to 0")
    float speedTolerance;
}
