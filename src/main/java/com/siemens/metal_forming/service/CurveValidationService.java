package com.siemens.metal_forming.service;

import com.siemens.metal_forming.domain.Curve;
import com.siemens.metal_forming.domain.PointOfTorqueAndSpeed;
import com.siemens.metal_forming.entity.Tolerance;

import java.util.Set;

public interface CurveValidationService {
    Set<PointOfTorqueAndSpeed> validate(Tolerance tolerance, Curve referenceCurve, Curve measuredCurve);
}
