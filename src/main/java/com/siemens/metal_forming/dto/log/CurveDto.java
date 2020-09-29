package com.siemens.metal_forming.dto.log;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value @Builder
public class CurveDto {
    List<PointOfTorqueAndSpeedDto> points;
}
