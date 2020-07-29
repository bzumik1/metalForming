package com.siemens.metal_forming.opcua.impl;

import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.opcua.OpcuaClient;
import com.siemens.metal_forming.opcua.OpcuaClientProvider;
import com.siemens.metal_forming.opcua.OpcuaConnector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;

@Component @Slf4j
public class OpcuaConnectorImpl implements OpcuaConnector {
    private final OpcuaClientProvider clientProvider;
    private final HashMap<String, OpcuaClient> opcuaClients = new HashMap<>();

    public OpcuaConnectorImpl(@Autowired OpcuaClientProvider clientProvider) {
        this.clientProvider = clientProvider;
    }

    public OpcuaClient connectPlc(Plc plc){
        String ipAddress = plc.getIpAddress();
            OpcuaClient opcuaClient = clientProvider.createClient(ipAddress);
            opcuaClient.connect();
            opcuaClients.put(ipAddress, opcuaClient);
            return opcuaClient;
    }

    public void disconnectPlc(Plc plc){
        OpcuaClient opcuaClient = opcuaClients.get(plc.getIpAddress());
        opcuaClient.disconnect().thenRun(() -> opcuaClients.remove(plc.getIpAddress()));
    }

    public OpcuaClient getClient(Plc plc){
        Optional<OpcuaClient> opcuaClient = Optional.ofNullable(opcuaClients.get(plc.getIpAddress()));
        return opcuaClient.orElseGet(() -> connectPlc(plc));
    }

    public boolean isEmpty(){
        return opcuaClients.isEmpty();
    }
}
