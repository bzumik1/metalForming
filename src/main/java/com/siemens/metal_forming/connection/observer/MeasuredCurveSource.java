package com.siemens.metal_forming.connection.observer;

public interface MeasuredCurveSource {
    void registerMeasuredCurveObserver(MeasuredCurveObserver measuredCurveObserver);
    void removeMeasuredCurveObserver(MeasuredCurveObserver measuredCurveObserver);
    void notifyMeasuredCurveObservers();
}
