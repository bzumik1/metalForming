package com.siemens.metal_forming.opcua;

import org.eclipse.milo.opcua.sdk.client.api.UaClient;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

import java.util.concurrent.CompletableFuture;

public interface OpcuaClient extends UaClient {
    CompletableFuture<String> readSerialNumber();
    CompletableFuture<String> readFirmwareNumber();
    CompletableFuture<String> readToolName();
    CompletableFuture<Integer> readToolNumber();
    CompletableFuture<Integer> readToolMaxSpeedOperation();
    CompletableFuture<Void> immediateStop();
    CompletableFuture<Void> topPositionStop();
}
