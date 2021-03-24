package com.siemens.metal_forming.connection.observer;

public interface ToolDataSource {
    void registerToolDataObserver(ToolDataObserver toolDataObserver);
    void removeToolDataObserver(ToolDataObserver toolDataObserver);
    void notifyToolDataObservers();
}
