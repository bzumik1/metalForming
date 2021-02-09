package com.siemens.metal_forming.service.impl;

import com.siemens.metal_forming.domain.Curve;
import com.siemens.metal_forming.domain.PointOfTorqueAndSpeed;
import com.siemens.metal_forming.entity.Tolerance;
import com.siemens.metal_forming.exception.exceptions.IncompatibleCurvesException;
import com.siemens.metal_forming.service.CurveValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j @Service
public class CurveValidationServiceImpl implements CurveValidationService {

    @Override
    public Set<PointOfTorqueAndSpeed> validate(Tolerance tolerance, Curve referenceCurve, Curve measuredCurve) {
        log.info("starting curve validation");
        if(referenceCurve.getPoints().size() != measuredCurve.getPoints().size()) throw new IncompatibleCurvesException();

        Set<PointOfTorqueAndSpeed> collisionPoints =  IntStream.range(0,referenceCurve.getPoints().size())
                .filter(i -> !tolerance.isInTolerance(referenceCurve.getPoints().get(i), measuredCurve.getPoints().get(i)))
                .mapToObj(i -> new PointOfTorqueAndSpeed(measuredCurve.getPoints().get(i).getTorque(), measuredCurve.getPoints().get(i).getSpeed()))
                .collect(Collectors.toSet());

        if(collisionPoints.isEmpty())
            log.debug("No collision points were found during validation");
        else
            log.info("Curve is not valid, it contains {} collision points",collisionPoints.size());

        return collisionPoints;
    }
}
