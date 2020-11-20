package com.siemens.metal_forming.service.impl;

import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.entity.Tolerance;
import com.siemens.metal_forming.entity.log.CollisionPoint;
import com.siemens.metal_forming.exception.exceptions.IncompatibleCurvesException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service("CurveValidationServiceImpl")
@Slf4j
public class CurveValidationServiceImpl implements com.siemens.metal_forming.service.CurveValidationService {
    @Override
    public Set<CollisionPoint> validate(Tolerance tolerance, Curve referenceCurve, Curve measuredCurve) {
        log.info("starting curve validation");
        if(referenceCurve.getPoints().size() != measuredCurve.getPoints().size()) throw new IncompatibleCurvesException();

        Set<CollisionPoint> collisionPoints =  IntStream.range(0,referenceCurve.getPoints().size())
                .filter(i -> !tolerance.isInTolerance(referenceCurve.getPoints().get(i), measuredCurve.getPoints().get(i)))
                .mapToObj(i -> new CollisionPoint(measuredCurve.getPoints().get(i).getTorque(), measuredCurve.getPoints().get(i).getSpeed()))
                .collect(Collectors.toSet());

        if(collisionPoints.isEmpty())
            log.debug("No collision points were found during validation");
        else
            log.info("Curve is not valid, it contains {} collision points",collisionPoints.size());

        return collisionPoints;
    }
}
