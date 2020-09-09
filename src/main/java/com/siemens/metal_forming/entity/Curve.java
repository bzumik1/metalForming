package com.siemens.metal_forming.entity;

import com.siemens.metal_forming.entity.log.Log;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter @Setter @FieldDefaults(level = AccessLevel.PRIVATE)
@Entity @Table(name = "curves")
public class Curve {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @OneToMany(orphanRemoval = true,cascade = CascadeType.ALL, mappedBy = "motorCurve")
    Set<Log> LogsWithMotorCurve = new HashSet<>();

    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "curve_id", nullable = false) //ToDo should be nullable=false but different point should be created
    List<CurvePoint> points = new ArrayList<>();
}
