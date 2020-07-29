package com.siemens.metal_forming.entity;

import javax.persistence.*;
import java.util.List;

@Entity @Table(name = "curve")
public class Curve {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @OneToMany(orphanRemoval = true,cascade = CascadeType.ALL)
    @JoinColumn(name = "curve_id", nullable = false)
    List<PointOfTorqueAndSpeed> point;
}
