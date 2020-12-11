package com.siemens.metal_forming.connection.observer;

import com.siemens.metal_forming.connection.PlcData;
import com.siemens.metal_forming.enumerated.ConnectionStatus;

public interface ConnectionStatusObserver {
    void onConnectionStatusChange(PlcData plcData);
}
