package com.siemens.metal_forming.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value @Builder(toBuilder = true) @AllArgsConstructor
public class AbsoluteToleranceDto implements ToleranceDto{
    float torqueTolerance;
    float speedTolerance;
}
