package com.siemens.metal_forming.opcua;

import org.eclipse.milo.opcua.sdk.client.api.UaClient;

import java.util.concurrent.CompletableFuture;

public interface OpcuaClient extends UaClient {
    CompletableFuture<String> readSerialNumber();
    CompletableFuture<String> readFirmwareNumber();
    CompletableFuture<Void> subscribeAll();
}
