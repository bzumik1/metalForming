package com.siemens.metal_forming.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.lang.annotation.Annotation;

@Getter @Setter @NoArgsConstructor @FieldDefaults(level = AccessLevel.PRIVATE) @EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@DiscriminatorValue("ABSOLUTE")
public class AbsoluteTolerance extends Tolerance {
    @Column(nullable = false)
    float torqueTolerance;
    @Column(nullable = false)
    float speedTolerance;

    public AbsoluteTolerance(float torqueTolerance, float speedTolerance){
        this.torqueTolerance = torqueTolerance;
        this.speedTolerance = speedTolerance;
    }

    @Override
    public boolean validate(PointOfTorqueAndSpeed referencePoint, PointOfTorqueAndSpeed pointToBeValidated) {
        return false;
    }
}
