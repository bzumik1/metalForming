package com.siemens.metal_forming.connection.observer;

public interface FirmwareNumberSource {
    void registerFirmwareNumberObserver(FirmwareNumberObserver firmwareNumberObserver);
    void removeFirmwareNumberObserver(FirmwareNumberObserver firmwareNumberObserver);
    void notifyFirmwareNumberObservers();
}
