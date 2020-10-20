package com.siemens.metal_forming.service.mock;

import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.entity.log.CollisionPoint;
import com.siemens.metal_forming.service.CurveValidationService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("CurveValidationServiceMock")
public class CurveValidationServiceMock implements CurveValidationService {
    @Override
    public Set<CollisionPoint> validate(Curve referenceCurve, Curve measuredCurve) {
        Set<CollisionPoint> collisionPoints = new HashSet<>();
        if(Math.random()<0.2){
            int numberOfCollisions = ThreadLocalRandom.current().nextInt(1, 3);
            return Stream.generate(() -> measuredCurve.getPoints().get(ThreadLocalRandom.current().nextInt(0,360)))
                    .limit(numberOfCollisions)
                    .map(curvePoint -> new CollisionPoint(curvePoint.getTorque(), curvePoint.getSpeed()))
                    .collect(Collectors.toSet());
        } else {
            return collisionPoints;
        }
    }
}
