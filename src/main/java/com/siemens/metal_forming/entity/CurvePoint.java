package com.siemens.metal_forming.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor(access = AccessLevel.PROTECTED) @SuperBuilder
@Entity
@Table(name = "curve_points") @ToString(callSuper=true)
public final class CurvePoint extends PointOfTorqueAndSpeed {
    public CurvePoint(Float torque,Float speed){
        super(torque,speed);
    }
}
