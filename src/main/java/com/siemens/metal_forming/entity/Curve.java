package com.siemens.metal_forming.entity;

import com.siemens.metal_forming.entity.log.Log;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter @Setter @FieldDefaults(level = AccessLevel.PRIVATE) @NoArgsConstructor @AllArgsConstructor @Builder(toBuilder = true)
@EqualsAndHashCode
@Entity @Table(name = "curves")
public class Curve {
    @EqualsAndHashCode.Exclude
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "curve_id", nullable = false) //ToDo should be nullable=false but different point should be created
    List<CurvePoint> points = new ArrayList<>();
}
