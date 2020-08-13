package com.siemens.metal_forming.opcua;

import org.eclipse.milo.opcua.sdk.client.api.UaClient;

public interface OpcuaClient extends UaClient {
    String readSerialNumber();
}
