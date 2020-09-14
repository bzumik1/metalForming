package com.siemens.metal_forming.entity.log;

import com.siemens.metal_forming.entity.Curve;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Set;

@Getter @Setter @FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor @Builder(toBuilder = true)
@Entity
public final class Log {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotNull
    @Column(nullable = false, updatable = false)
    final Timestamp createdOn = new Timestamp(System.currentTimeMillis());

    @NotNull
    @OneToOne(cascade={CascadeType.ALL})
    @JoinColumn(name = "measured_curve_id", nullable = false, updatable = false)
    Curve measuredCurve;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "motor_curve_id", nullable = false, updatable = false)
    Curve motorCurve;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reference_curve_id", nullable = false, updatable = false)
    Curve referenceCurve;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "log_id",updatable = false, nullable = false)
    Set<CollisionPoint> collisionPoints;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "plc_information_id",nullable = false, updatable = false)
    PlcInfo plcInformation;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tool_information_id", nullable = false, updatable = false)
    ToolInfo toolInformation;

    @NonFinal
    String comment;
}