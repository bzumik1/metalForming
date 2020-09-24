package com.siemens.metal_forming.entity;

import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

@SuperBuilder
@Entity
@Table(name = "curve_points")
public class CurvePoint extends PointOfTorqueAndSpeed {
    public CurvePoint(Float torque,Float speed){
        super(torque,speed);
    }
}
