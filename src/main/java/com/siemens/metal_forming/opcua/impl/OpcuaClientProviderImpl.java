package com.siemens.metal_forming.opcua.impl;

import com.siemens.metal_forming.opcua.OpcuaClient;
import com.siemens.metal_forming.opcua.OpcuaClientProvider;
import com.siemens.metal_forming.opcua.OpcuaConfiguration;
import com.siemens.metal_forming.opcua.impl.OpcuaClientImpl;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig;
import org.eclipse.milo.opcua.stack.client.DiscoveryClient;
import org.eclipse.milo.opcua.stack.client.UaStackClient;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

@Component @Slf4j
public class OpcuaClientProviderImpl implements OpcuaClientProvider {
    private final OpcuaConfiguration opcuaConfiguration;

    public OpcuaClientProviderImpl(@Autowired OpcuaConfiguration opcuaConfiguration) {
        this.opcuaConfiguration = opcuaConfiguration;
    }

    @Override
    public OpcuaClient createClient(String ipAddress){
        try{
            //This will get all endpoints
            List<EndpointDescription> endpoints = DiscoveryClient.getEndpoints(createUrl(ipAddress)).get();

            //Select endpoint
            EndpointDescription oneOfEndpoints = endpoints.stream().filter(endpointDescription -> endpointDescription.getSecurityMode().equals(MessageSecurityMode.None)).findFirst().get();

            //select endpoint and configure connection
            OpcUaClientConfig opcUaConfiguration = OpcUaClientConfig.builder()
                    .setEndpoint(oneOfEndpoints)
                    .setApplicationName(LocalizedText.english(opcuaConfiguration.getApplicationName()))
                    .setRequestTimeout(uint(opcuaConfiguration.getPort()))
                    .build();

            UaStackClient stackClient = UaStackClient.create(opcUaConfiguration);
            return new OpcuaClientImpl(opcUaConfiguration,stackClient,ipAddress);
        }catch (InterruptedException | ExecutionException | UaException e){
            log.warn("OPC UA client for plc with ip {} could not be created: {}",ipAddress,e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Something went wrong");
        }

    }

    private String createUrl(String ipAddress){
        return "opc.tcp://"+ipAddress+":"+ opcuaConfiguration.getPort();
    }
}
