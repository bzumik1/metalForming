package com.siemens.metal_forming.connection.observer;

import com.siemens.metal_forming.connection.PlcData;

public interface ToolNameObserver {
    void onToolNameChange(PlcData plcData);
}
