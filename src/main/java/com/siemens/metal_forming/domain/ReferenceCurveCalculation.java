package com.siemens.metal_forming.domain;

import com.siemens.metal_forming.exception.exceptions.IncompatibleCurvesException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Setter @Getter @FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class ReferenceCurveCalculation {
    public final int NUMBER_OF_CYCLES;
    private final CalculationStatus status;
    final List<Curve> curves = new ArrayList<>();

    public ReferenceCurveCalculation(int numberOfCycles) {
        NUMBER_OF_CYCLES = numberOfCycles;
        status = new CalculationStatus(numberOfCycles);
    }

    public Optional<Curve> calculate(Curve curve){
        if(curves.size() < NUMBER_OF_CYCLES){
            if(curves.size() != 0 && curves.get(0).getPoints().size()!= curve.getPoints().size()){
                throw new IncompatibleCurvesException();
            }
            curves.add(curve);
            status.increase();
            log.debug("Calculating reference curve remaining cycles: {}", status);
        }
        if(curves.size() == NUMBER_OF_CYCLES){
            List<PointOfTorqueAndSpeed> referencePointOfTorqueAndSpeeds = new ArrayList<>();
            for(int i=0; i<curves.get(0).getPoints().size(); i++){
                float referenceTorque = 0f;
                float referenceSpeed = 0f;
                for(Curve c: curves){
                    PointOfTorqueAndSpeed curvePoint = c.getPoints().get(i);
                    referenceTorque += curvePoint.getTorque();
                    referenceSpeed += curvePoint.getSpeed();
                }
                referenceTorque = referenceTorque/NUMBER_OF_CYCLES;
                referenceSpeed = referenceSpeed/NUMBER_OF_CYCLES;

                referencePointOfTorqueAndSpeeds.add(new PointOfTorqueAndSpeed(referenceTorque, referenceSpeed));
            }
            log.debug("Reference curve was calculated");
            log.debug("Torque: {}", referencePointOfTorqueAndSpeeds.stream().map(PointOfTorqueAndSpeed::getTorque).collect(Collectors.toList()));
            log.debug("Speed: {}", referencePointOfTorqueAndSpeeds.stream().map(PointOfTorqueAndSpeed::getSpeed).collect(Collectors.toList()));
            return Optional.of(Curve.builder().points(referencePointOfTorqueAndSpeeds).build());
        }
        return Optional.empty();
    }
}
