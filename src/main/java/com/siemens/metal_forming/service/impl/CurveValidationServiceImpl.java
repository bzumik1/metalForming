package com.siemens.metal_forming.service.impl;

import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.entity.log.CollisionPoint;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("CurveValidationServiceImpl")
public class CurveValidationServiceImpl implements com.siemens.metal_forming.service.CurveValidationService {
    @Override
    public Set<CollisionPoint> validate(Curve referenceCurve, Curve measuredCurve) {
        return null;
    }
}
