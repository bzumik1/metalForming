package com.siemens.metal_forming.service;

import com.siemens.metal_forming.connection.observer.MeasuredCurveObserver;
import com.siemens.metal_forming.connection.observer.ToolNumberObserver;

public interface ReferenceCurveCalculationService extends ToolNumberObserver, MeasuredCurveObserver {
}
