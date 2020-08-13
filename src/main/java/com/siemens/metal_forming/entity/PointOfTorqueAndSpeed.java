package com.siemens.metal_forming.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter @NoArgsConstructor @FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class PointOfTorqueAndSpeed {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotNull
    @Column(name = "torque",nullable = false)
    Float torque;

    @NotNull
    @Column(name = "speed",nullable = false)
    Float speed;

    public PointOfTorqueAndSpeed(Float torque,Float speed){
        this.torque = torque;
        this.speed = speed;
    }
}
