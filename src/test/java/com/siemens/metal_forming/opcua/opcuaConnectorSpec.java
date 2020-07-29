package com.siemens.metal_forming.opcua;

import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.opcua.impl.OpcuaConnectorImpl;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;


import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("<= OPC UA Connector Specification =>")
public class opcuaConnectorSpec {
    private OpcuaClient opcuaClient;
    private OpcuaClientProvider opcuaClientProvider;
    private OpcuaConnector opcuaConnector;

    @BeforeEach
    void initializeMock(){
        //mock classes
        opcuaClient = Mockito.mock(OpcuaClient.class);
        opcuaClientProvider = Mockito.mock(OpcuaClientProvider.class);

        //tested class
        opcuaConnector = new OpcuaConnectorImpl(opcuaClientProvider);

        //rules
        Mockito.when(opcuaClient.disconnect()).thenReturn(CompletableFuture.completedFuture(null));
        Mockito.when(opcuaClientProvider.createClient("192.168.1.1")).thenReturn(opcuaClient);
    }

    @Test @DisplayName("after adding PLC connects newly created client")
    void connectAfterPlcIsAdded(){
        Plc plc = new Plc();
        plc.setIpAddress("192.168.1.1");
        opcuaConnector.connectPlc(plc);

        Mockito.verify(opcuaClient,Mockito.times(1)).connect();
    }

    @Test @DisplayName("after removing PLC disconnects client")
    void disconnectsAfterPlcIsRemoved(){
        Plc plc = new Plc();
        plc.setIpAddress("192.168.1.1");
        opcuaConnector.connectPlc(plc);
        opcuaConnector.disconnectPlc(plc);

        Mockito.verify(opcuaClient,Mockito.times(1)).disconnect();
    }

    @Test @DisplayName("after removing PLC client is also removed")
    void afterRemovingPlcClientIsAlsoRemoved(){
        Plc plc = new Plc();
        plc.setIpAddress("192.168.1.1");
        opcuaConnector.connectPlc(plc);
        opcuaConnector.disconnectPlc(plc);
        assertThat(opcuaConnector.isEmpty()).isTrue();
    }

    @Test @DisplayName("if client is not already existing then it is created")
    void createsClientIfNotExists(){
        assertThat(opcuaConnector.isEmpty()).isTrue();
        Plc plc = new Plc();
        plc.setIpAddress("192.168.1.1");
        assertThat(opcuaConnector.getClient(plc)).isNotNull();
    }



}
