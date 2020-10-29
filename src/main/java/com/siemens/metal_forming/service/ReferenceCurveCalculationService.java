package com.siemens.metal_forming.service;

import com.siemens.metal_forming.domain.ReferenceCurveCalculation;
import com.siemens.metal_forming.entity.Curve;

import java.util.Optional;

public interface ReferenceCurveCalculationService {
    void removeCalculation(long toolId);
    void addCalculation(long toolId, int numberOfReferenceCycles);
    Optional<Curve> calculate(long toolId, Curve newCurveForCalculation);
    Optional<ReferenceCurveCalculation> getReferenceCurveCalculation(long toolId);
}
