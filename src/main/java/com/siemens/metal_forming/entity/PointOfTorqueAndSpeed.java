package com.siemens.metal_forming.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class PointOfTorqueAndSpeed {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotNull
    @Column(name = "torque",nullable = false)
    float torque;

    @NotNull
    @Column(name = "speed",nullable = false)
    float speed;
}
