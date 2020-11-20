package com.siemens.metal_forming.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter @Setter @NoArgsConstructor @FieldDefaults(level = AccessLevel.PRIVATE) @EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@DiscriminatorValue("RELATIVE")
public class RelativeTolerance extends Tolerance{
    @Min(value = 0, message = "Torque tolerance in percentage must be greater or equal to 0")
    @Max(value = 100, message = "Torque tolerance in percentage must be smaller or equal to 100")
    float torqueTolerance;
    @Min(value = 0, message = "Speed tolerance in percentage must be greater or equal to 0")
    @Max(value = 100, message = "Speed tolerance in percentage must be smaller or equal to 100")
    float speedTolerance;

    public RelativeTolerance(float torqueTolerance, float speedTolerance){
        this.torqueTolerance = torqueTolerance;
        this.speedTolerance = speedTolerance;
    }

    public AbsoluteTolerance getAbsoluteTolerance(PointOfTorqueAndSpeed point){
        float absoluteTorqueTolerance = point.getTorque() * (torqueTolerance/100f);
        float absoluteSpeedTolerance = point.getSpeed() * (speedTolerance/100f);
        return new AbsoluteTolerance(absoluteTorqueTolerance, absoluteSpeedTolerance);
    }

    @Override
    public boolean validate(PointOfTorqueAndSpeed referencePoint, PointOfTorqueAndSpeed pointToBeValidated) {
        return getAbsoluteTolerance(referencePoint).validate(referencePoint,pointToBeValidated);
    }
}
