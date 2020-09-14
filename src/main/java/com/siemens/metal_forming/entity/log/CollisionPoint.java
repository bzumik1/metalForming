package com.siemens.metal_forming.entity.log;

import com.siemens.metal_forming.entity.PointOfTorqueAndSpeed;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(toBuilder = true)
@Entity
@Table(name = "collision_points")
public final class CollisionPoint extends PointOfTorqueAndSpeed {
    public CollisionPoint(Float torque,Float speed){
        super(torque,speed);
    }
}
