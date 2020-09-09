package com.siemens.metal_forming.entity.log;

import com.siemens.metal_forming.entity.PointOfTorqueAndSpeed;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "collision_points")
public class CollisionPoint extends PointOfTorqueAndSpeed {
    public CollisionPoint(Float torque,Float speed){
        super(torque,speed);
    }
}
