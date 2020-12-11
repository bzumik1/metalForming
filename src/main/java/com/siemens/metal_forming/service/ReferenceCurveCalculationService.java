package com.siemens.metal_forming.service;

import com.siemens.metal_forming.connection.observer.MeasuredCurveObserver;
import com.siemens.metal_forming.connection.observer.ToolNumberObserver;
import com.siemens.metal_forming.domain.ReferenceCurveCalculation;
import com.siemens.metal_forming.entity.Curve;

import java.util.Optional;

public interface ReferenceCurveCalculationService extends ToolNumberObserver, MeasuredCurveObserver {
}
