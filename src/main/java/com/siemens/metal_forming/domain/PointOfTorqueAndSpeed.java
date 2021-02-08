package com.siemens.metal_forming.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter @AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true) @EqualsAndHashCode
@ToString
public class PointOfTorqueAndSpeed {
    float torque;
    float speed;
}
