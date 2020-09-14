package com.siemens.metal_forming.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Immutable;

import javax.persistence.*;
import java.util.Set;

@Getter @FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE) @Builder(toBuilder = true)

@Entity
public final class CurveEnvelope {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "boundary_curve_id", updatable = false)
    Set<Curve> boundaryCurves;
}
