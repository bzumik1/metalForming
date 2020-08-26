package com.siemens.metal_forming.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @FieldDefaults(level = AccessLevel.PRIVATE)
@Entity @Table(name = "curves")
public class Curve {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "curve_id", nullable = false)
    List<PointOfTorqueAndSpeed> points = new ArrayList<>();
}
