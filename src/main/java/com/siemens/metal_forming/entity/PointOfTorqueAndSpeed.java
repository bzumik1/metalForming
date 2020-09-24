package com.siemens.metal_forming.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE) @EqualsAndHashCode
@MappedSuperclass
public abstract class PointOfTorqueAndSpeed {
    public PointOfTorqueAndSpeed(Float torque,Float speed){
        this.torque = torque;
        this.speed = speed;
    }

    @EqualsAndHashCode.Exclude
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotNull
    @Column(name = "torque",nullable = false)
    float torque;

    @NotNull
    @Column(name = "speed",nullable = false)
    float speed;
}
