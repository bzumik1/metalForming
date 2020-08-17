package com.siemens.metal_forming.opcua.impl;

import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.exception.OpcuaConnectionException;
import com.siemens.metal_forming.opcua.OpcuaClient;
import com.siemens.metal_forming.opcua.OpcuaClientProvider;
import com.siemens.metal_forming.opcua.OpcuaConnector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Component @Slf4j
public class OpcuaConnectorImpl implements OpcuaConnector {
    private final OpcuaClientProvider clientProvider;
    private final HashMap<String, OpcuaClient> opcuaClients = new HashMap<>();

    public OpcuaConnectorImpl(@Autowired OpcuaClientProvider clientProvider) {
        this.clientProvider = clientProvider;
    }

    public OpcuaClient connectPlc(Plc plc){
        String ipAddress = plc.getIpAddress();

        Optional<OpcuaClient> oldOpcuaClient = Optional.ofNullable(opcuaClients.get(ipAddress));

        if(oldOpcuaClient.isEmpty()){
            OpcuaClient opcuaClient = clientProvider.createClient(ipAddress); //Throws OpcuaConnectionException
            try {
                opcuaClient.connect().get();
                opcuaClient.subscribeAll(); //TODO add test for this!
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                throw new OpcuaConnectionException();
            }
            opcuaClients.put(ipAddress, opcuaClient);
            return opcuaClient;
        } else {
            log.warn("OPC UA client for plc with IP address {} already exist, existing one is returned", plc.getIpAddress());
            return oldOpcuaClient.get();
        }
    }


    public void disconnectPlc(Plc plc){
        Optional<OpcuaClient> oldOpcuaClient = Optional.ofNullable(opcuaClients.get(plc.getIpAddress()));

        if(oldOpcuaClient.isPresent()){
            try {
                oldOpcuaClient.get().disconnect().get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            opcuaClients.remove(plc.getIpAddress());
        } else {
            log.warn("OPC UA client for plc with IP address {} did not exist",plc.getIpAddress());
        }
    }

    public OpcuaClient getClient(Plc plc){
        Optional<OpcuaClient> opcuaClient = Optional.ofNullable(opcuaClients.get(plc.getIpAddress()));
        return opcuaClient.orElseGet(() -> connectPlc(plc));
    }

    public boolean isEmpty(){
        return opcuaClients.isEmpty();
    }
}
