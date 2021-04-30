package com.siemens.metal_forming.entity.log;

import com.siemens.metal_forming.domain.Curve;
import com.siemens.metal_forming.domain.PointOfTorqueAndSpeed;
import com.siemens.metal_forming.entity.converter.CollisionPointsConverter;
import com.siemens.metal_forming.entity.converter.CurveConverter;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Set;

@Getter @Setter @FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor @Builder(toBuilder = true)
@EqualsAndHashCode
@Entity
@Table(name = "logs")
public final class Log {
    @EqualsAndHashCode.Exclude
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @EqualsAndHashCode.Exclude
    @NotNull
    @Column(nullable = false, updatable = false)
    final Timestamp createdOn = new Timestamp(System.currentTimeMillis());

    @NotNull
    @Column(name = "measured_curve", length = 10000)
    @Basic(fetch = FetchType.LAZY)
    @Convert(converter = CurveConverter.class)
    Curve measuredCurve;

    @NotNull
    @Column(name = "motor_curve", length = 10000)
    @Basic(fetch = FetchType.LAZY)
    @Convert(converter = CurveConverter.class)
    Curve motorCurve;

    @NotNull
    @Column(name = "reference_curve", length = 10000)
    @Basic(fetch = FetchType.LAZY)
    @Convert(converter = CurveConverter.class)
    Curve referenceCurve;

    @NotNull
    @Column(name = "collision_points", length = 10000)
    @Basic(fetch = FetchType.LAZY)
    @Convert(converter = CollisionPointsConverter.class)
    Set<PointOfTorqueAndSpeed> collisionPoints;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "plc_information_id",nullable = false, updatable = false)
    PlcInfo plcInformation;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tool_information_id", nullable = false, updatable = false)
    ToolInfo toolInformation;

    @NonFinal
    @Column(name = "comment", length = 1000)
    String comment;
}
