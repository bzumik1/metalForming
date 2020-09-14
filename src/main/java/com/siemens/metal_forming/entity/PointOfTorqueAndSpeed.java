package com.siemens.metal_forming.entity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter @NoArgsConstructor @FieldDefaults(level = AccessLevel.PRIVATE) @EqualsAndHashCode
@MappedSuperclass
public abstract class PointOfTorqueAndSpeed {
    @EqualsAndHashCode.Exclude
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotNull
    @Column(name = "torque",nullable = false)
    float torque;

    @NotNull
    @Column(name = "speed",nullable = false)
    float speed;

    public PointOfTorqueAndSpeed(Float torque,Float speed){
        this.torque = torque;
        this.speed = speed;
    }
}
