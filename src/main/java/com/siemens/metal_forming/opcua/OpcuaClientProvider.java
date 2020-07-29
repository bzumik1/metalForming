package com.siemens.metal_forming.opcua;

import org.eclipse.milo.opcua.stack.core.UaException;

import java.util.concurrent.ExecutionException;

public interface OpcuaClientProvider {
    OpcuaClient createClient(String ipAddress);
}
