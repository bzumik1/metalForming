package com.siemens.metal_forming.entity;

import org.junit.jupiter.api.DisplayName;

@DisplayName("<= Point of Torque and Speed Specification =>")
class PointOfTorqueAndSpeedSpec extends EntitySpec{
    @Override
    public Class getTestedClass() {
        return PointOfTorqueAndSpeed.class;
    }

}
