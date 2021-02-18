package com.siemens.metal_forming.connection.observer;

public interface MotorCurveSource {
    void registerMotorCurveObserver(MotorCurveObserver motorCurveObserver);
    void removeMotorCurveObserver(MotorCurveObserver motorCurveObserver);
    void notifyMotorCurveObservers();
}
