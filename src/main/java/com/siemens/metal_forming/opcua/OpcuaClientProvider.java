package com.siemens.metal_forming.opcua;

import com.siemens.metal_forming.exception.OpcuaConnectionException;
import org.eclipse.milo.opcua.stack.core.UaException;

import java.util.concurrent.ExecutionException;

public interface OpcuaClientProvider {
    OpcuaClient createClient(String ipAddress);
}
