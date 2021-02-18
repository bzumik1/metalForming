package com.siemens.metal_forming.service;

import com.siemens.metal_forming.connection.observer.*;

public interface PlcAutomaticUpdateService extends ConnectionStatusObserver, FirmwareNumberObserver, SerialNumberObserver, ToolNameObserver, ToolNumberObserver, MotorCurveObserver{
}
