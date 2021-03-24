package com.siemens.metal_forming.connection.observer;

import com.siemens.metal_forming.connection.PlcData;

public interface ToolDataObserver {
    void onToolDataChange(PlcData plcData);
}
