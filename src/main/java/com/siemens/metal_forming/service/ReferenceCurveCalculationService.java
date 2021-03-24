package com.siemens.metal_forming.service;

import com.siemens.metal_forming.connection.observer.MeasuredCurveObserver;
import com.siemens.metal_forming.connection.observer.ToolDataObserver;

public interface ReferenceCurveCalculationService extends ToolDataObserver, MeasuredCurveObserver {
}
