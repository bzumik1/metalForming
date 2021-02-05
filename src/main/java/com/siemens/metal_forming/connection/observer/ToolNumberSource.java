package com.siemens.metal_forming.connection.observer;

public interface ToolNumberSource {
    void registerToolNumberObserver(ToolNumberObserver toolNumberObserver);
    void removeToolNumberObserver(ToolNumberObserver toolNumberObserver);
    void notifyToolNumberObservers();
}
