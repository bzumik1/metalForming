package com.siemens.metal_forming.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter @Setter @NoArgsConstructor @FieldDefaults(level = AccessLevel.PRIVATE) @EqualsAndHashCode @ToString
@Entity
public class RelativeTolerance {
    @EqualsAndHashCode.Exclude
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Min(value = 0, message = "Torque tolerance in percentage must be greater or equal to 0")
    @Max(value = 100, message = "Torque tolerance in percentage must be smaller or equal to 100")
    int torqueTolerance;
    @Min(value = 0, message = "Speed tolerance in percentage must be greater or equal to 0")
    @Max(value = 100, message = "Speed tolerance in percentage must be smaller or equal to 100")
    int speedTolerance;

    public RelativeTolerance(int torqueTolerance, int speedTolerance){
        this.torqueTolerance = torqueTolerance;
        this.speedTolerance = speedTolerance;
    }

    public AbsoluteTolerance getAbsoluteTolerance(PointOfTorqueAndSpeed point){
        float absoluteTorqueTolerance = point.getTorque() * (torqueTolerance/100f);
        float absoluteSpeedTolerance = point.getSpeed() * (speedTolerance/100f);
        return new AbsoluteTolerance(absoluteTorqueTolerance, absoluteSpeedTolerance);
    }
}
