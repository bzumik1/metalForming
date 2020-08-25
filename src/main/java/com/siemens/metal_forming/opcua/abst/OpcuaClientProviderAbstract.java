package com.siemens.metal_forming.opcua.abst;

import com.siemens.metal_forming.exception.exceptions.OpcuaClientException;
import com.siemens.metal_forming.exception.exceptions.OpcuaConnectionException;
import com.siemens.metal_forming.opcua.OpcuaClient;
import com.siemens.metal_forming.opcua.OpcuaClientProvider;
import com.siemens.metal_forming.opcua.configuration.OpcuaConfiguration;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig;
import org.eclipse.milo.opcua.stack.client.DiscoveryClient;
import org.eclipse.milo.opcua.stack.client.UaStackClient;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.structured.ApplicationDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

@Getter @Setter @Slf4j
public abstract class OpcuaClientProviderAbstract implements OpcuaClientProvider {

    private OpcuaConfiguration opcuaConfiguration;

    @Override
    public OpcuaClient createClient(String ipAddress){
        //This will get all endpoints
        List<EndpointDescription> endpoints;
        try {
            endpoints = DiscoveryClient.getEndpoints(createUrl(ipAddress)).get();
        } catch (InterruptedException | ExecutionException e){
            log.warn("OPC UA connection could not be established: " + e.getMessage());
            throw new OpcuaConnectionException();
        }

        try{
            //Select endpoint
            Optional<EndpointDescription> endpointWithoutSecurity = endpoints.stream().filter(endpointDescription -> endpointDescription.getSecurityMode().equals(MessageSecurityMode.None)).findFirst();
            if(endpointWithoutSecurity.isPresent()){
                //select endpoint and configure connection
                OpcUaClientConfig opcUaConfiguration = OpcUaClientConfig.builder()
                        .setEndpoint(endpointWithoutSecurity.get())
                        .setApplicationName(LocalizedText.english(opcuaConfiguration.getApplicationName()))
                        .setRequestTimeout(uint(2_000))
                        .setKeepAliveFailuresAllowed(uint(0)) //Number of possible failures
                        .setKeepAliveInterval(uint(2_000)) //Time after which is session (connection) marked as disconnected
                        .build();

                UaStackClient stackClient = UaStackClient.create(opcUaConfiguration);
                return createNewClient(opcUaConfiguration,stackClient,ipAddress);
            } else {
                throw new OpcuaClientException("Client could not be created, there was no endpoint without security");
            }

        }catch (UaException e){
            log.warn("OPC UA client for plc with ip {} could not be created: {}",ipAddress,e.getMessage());
            e.printStackTrace();
            throw new OpcuaClientException("Client could not be created");
        }

    }

    public abstract String createUrl(String ipAddress);
    public abstract OpcuaClient createNewClient(OpcUaClientConfig config, UaStackClient stackClient, String ipAddress);
}
