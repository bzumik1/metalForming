package com.siemens.metal_forming.connection.observer;

import com.siemens.metal_forming.connection.PlcData;
import com.siemens.metal_forming.entity.Curve;

public interface MeasuredCurveObserver {
    void onMeasuredCurveChange(PlcData plcData);
}
