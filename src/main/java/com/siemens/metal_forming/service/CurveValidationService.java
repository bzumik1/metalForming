package com.siemens.metal_forming.service;

import com.siemens.metal_forming.connection.observer.MeasuredCurveObserver;
import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.entity.Tolerance;
import com.siemens.metal_forming.entity.log.CollisionPoint;

import java.util.Set;

public interface CurveValidationService extends MeasuredCurveObserver {
    Set<CollisionPoint>  validate(Tolerance tolerance, Curve referenceCurve, Curve measuredCurve);
}
