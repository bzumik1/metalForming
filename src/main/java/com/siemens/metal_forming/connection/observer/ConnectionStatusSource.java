package com.siemens.metal_forming.connection.observer;

public interface ConnectionStatusSource {
    void registerConnectionStatusObserver(ConnectionStatusObserver connectionStatusObserver);
    void removeConnectionStatusObserver(ConnectionStatusObserver connectionStatusObserver);
    void notifyConnectionStatusObservers();
}
