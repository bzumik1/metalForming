package com.siemens.metal_forming.entity;

import com.siemens.metal_forming.domain.PointOfTorqueAndSpeed;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Getter @Setter @NoArgsConstructor @FieldDefaults(level = AccessLevel.PRIVATE) @EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@DiscriminatorValue("ABSOLUTE")
public class AbsoluteTolerance extends Tolerance {
    @Min(value = 0, message = "Torque tolerance must be greater or equal to 0")
    @Column(nullable = false)
    float torqueTolerance;
    @Min(value = 0, message = "Speed tolerance must be greater or equal to 0")
    @Column(nullable = false)
    float speedTolerance;

    public AbsoluteTolerance(float torqueTolerance, float speedTolerance){
        this.torqueTolerance = torqueTolerance;
        this.speedTolerance = speedTolerance;
    }

    @Override
    public boolean isInTolerance(PointOfTorqueAndSpeed referencePoint, PointOfTorqueAndSpeed pointToBeValidated) {
        //for one of tolerances the validation can not be done with equation of ellipse
        if(speedTolerance == 0 && torqueTolerance ==0) return referencePoint.equals(pointToBeValidated);
        if(speedTolerance ==0)
            return (referencePoint.getSpeed() == pointToBeValidated.getSpeed())
                    &&(Math.abs(referencePoint.getTorque()-pointToBeValidated.getTorque())<=torqueTolerance);
        if(torqueTolerance ==0)
            return (referencePoint.getTorque() == pointToBeValidated.getTorque())
                    &&(Math.abs(referencePoint.getSpeed()-pointToBeValidated.getSpeed())<=speedTolerance);

        //if tolerances are not null the validation is done with ellipse equation
        return Math.pow(pointToBeValidated.getSpeed()-referencePoint.getSpeed(),2)/Math.pow(speedTolerance,2) +
               Math.pow(pointToBeValidated.getTorque()-referencePoint.getTorque(),2)/Math.pow(torqueTolerance,2)
                <=1;
    }
}
