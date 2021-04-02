package com.siemens.metal_forming.service;

import com.siemens.metal_forming.connection.observer.*;

public interface AutomaticUpdateService extends ConnectionStatusObserver, FirmwareNumberObserver, SerialNumberObserver, ToolDataObserver, MotorCurveObserver{
}
