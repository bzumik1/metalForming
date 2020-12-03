package com.siemens.metal_forming.domain;

import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.entity.CurvePoint;
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
import java.util.stream.IntStream;

@Setter @Getter @FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class ReferenceCurveCalculation {
    public final int NUMBER_OF_CYCLES;
    final List<Curve> curves = new ArrayList<>();

    public ReferenceCurveCalculation(int numberOfCycles) {
        NUMBER_OF_CYCLES = numberOfCycles;
    }

    public Optional<Curve> calculate(Curve curve){
        if(curves.size() < NUMBER_OF_CYCLES){
            if(curves.size() != 0 && curves.get(0).getPoints().size()!= curve.getPoints().size()){
                throw new IncompatibleCurvesException();
            }
            curves.add(curve);
            log.debug("Calculating reference curve remaining cycles: {}/{}", curves.size(), NUMBER_OF_CYCLES);
        }
        if(curves.size() == NUMBER_OF_CYCLES){
            List<CurvePoint> referenceCurvePoints = new ArrayList<>();
            for(int i=0; i<curves.get(0).getPoints().size(); i++){
                float referenceTorque = 0f;
                float referenceSpeed = 0f;
                for(Curve c: curves){
                    CurvePoint curvePoint = c.getPoints().get(i);
                    referenceTorque += curvePoint.getTorque();
                    referenceSpeed += curvePoint.getSpeed();
                }
                referenceTorque = referenceTorque/NUMBER_OF_CYCLES;
                referenceSpeed = referenceSpeed/NUMBER_OF_CYCLES;

                referenceCurvePoints.add(new CurvePoint(referenceTorque, referenceSpeed));
            }
            log.debug("Reference curve was calculated");
            log.debug("Torque: {}", referenceCurvePoints.stream().map(CurvePoint::getTorque).collect(Collectors.toList()));
            log.debug("Speed: {}", referenceCurvePoints.stream().map(CurvePoint::getSpeed).collect(Collectors.toList()));
            return Optional.of(Curve.builder().points(referenceCurvePoints).build());
        }
        return Optional.empty();
    }
}
