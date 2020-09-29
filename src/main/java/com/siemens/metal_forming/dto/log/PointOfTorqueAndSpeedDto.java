package com.siemens.metal_forming.dto.log;

import lombok.Builder;
import lombok.Value;

@Value @Builder
public class PointOfTorqueAndSpeedDto {
    float torque;
    float speed;
}
