package com.siemens.metal_forming.connection.observer;

public interface SerialNumberSource {
    void registerSerialNumberObserver(SerialNumberObserver serialNumberObserver);
    void removeSerialNumberObserver(SerialNumberObserver serialNumberObserver);
    void notifySerialNumberObservers();
}
