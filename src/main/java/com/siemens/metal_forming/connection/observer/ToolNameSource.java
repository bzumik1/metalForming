package com.siemens.metal_forming.connection.observer;

public interface ToolNameSource {
    void registerToolNameObserver(ToolNameObserver toolNameObserver);
    void removeToolNameObserver(ToolNameObserver toolNameObserver);
    void notifyToolNameObservers();
}
