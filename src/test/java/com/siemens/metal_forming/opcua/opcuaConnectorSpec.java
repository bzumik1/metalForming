//package com.siemens.metal_forming.opcua;
//
//import com.siemens.metal_forming.entity.Plc;
//import com.siemens.metal_forming.exception.exceptions.OpcuaConnectionException;
//import com.siemens.metal_forming.opcua.impl.OpcuaConnectorImpl;
//import org.junit.jupiter.api.*;
//import org.mockito.Mockito;
//
//
//import java.util.concurrent.CompletableFuture;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@DisplayName("<= OPC UA CONNECTOR SPECIFICATION =>")
//public class opcuaConnectorSpec {
//    private OpcuaClient opcuaClient;
//    private OpcuaClientProvider opcuaClientProvider;
//    private Plc plc;
//    final private String mockIpAddress = "192.168.1.1";
//    private OpcuaConnector opcuaConnector;
//
//    @BeforeEach
//    void initialize(){
//        //mock classes
//        opcuaClient = Mockito.mock(OpcuaClient.class);
//        opcuaClientProvider = Mockito.mock(OpcuaClientProvider.class);
//        plc = Mockito.mock(Plc.class);
//
//        //tested class
//        opcuaConnector = new OpcuaConnectorImpl(opcuaClientProvider);
//
//        //rules
//        Mockito.when(plc.getIpAddress()).thenReturn(mockIpAddress);
//        Mockito.when(opcuaClient.connect()).thenReturn(CompletableFuture.completedFuture(null));
//    }
//
//    @Nested @DisplayName("CONNECTING PLC")
//    class connectingPlc{
//
//        @Test @DisplayName("when PLC is already connected then new client is not created")
//        void whenPlcIsAlreadyConnectedNewClientIsNotCreated(){
//            Mockito.when(opcuaClientProvider.createClient(mockIpAddress)).thenReturn(opcuaClient);
//
//            opcuaConnector.connectPlc(plc);
//            opcuaConnector.connectPlc(plc);
//
//            Mockito.verify(opcuaClientProvider,Mockito.times(1)).createClient(mockIpAddress);
//        }
//
//        @Test @DisplayName("throws error when connection over OPC UA could not be established")
//        void throwsErrorWhenConnectionOverOpcUaCouldNotBeEstablished(){
//            Mockito.when(opcuaClientProvider.createClient(mockIpAddress)).thenThrow(new OpcuaConnectionException());
//
//            assertThrows(OpcuaConnectionException.class,() -> opcuaConnector.connectPlc(plc));
//        }
//    }
//
//    @Nested @DisplayName("DISCONNECTING PLC")
//    class disconnectingPlc{
//        @BeforeEach
//        void initializeForDisconnectingPlc(){
//            Mockito.when(opcuaClientProvider.createClient(mockIpAddress)).thenReturn(opcuaClient);
//            Mockito.when(opcuaClient.disconnect()).thenReturn(CompletableFuture.completedFuture(null));
//        }
//
//        @Test @DisplayName("after disconnecting PLC disconnects client")
//        void disconnectsAfterPlcIsRemoved(){
//            opcuaConnector.connectPlc(plc);
//            opcuaConnector.disconnectPlc(plc);
//
//            Mockito.verify(opcuaClient,Mockito.times(1)).disconnect();
//        }
//
//        @Test @DisplayName("after disconnecting PLC client is removed")
//        void afterRemovingPlcClientIsAlsoRemoved(){
//            opcuaConnector.connectPlc(plc);
//            opcuaConnector.disconnectPlc(plc);
//
//            assertThat(opcuaConnector.isEmpty()).isTrue();
//        }
//
//        @Test @DisplayName("when disconnecting PLC which was not connected before nothing happens")
//        void disconnectingPlcWhichWasNotConnected(){
//            opcuaConnector.disconnectPlc(plc);
//
//            assertThat(opcuaConnector.isEmpty()).isTrue();
//        }
//    }
//
//    @Nested @DisplayName("GETTING OPC UA CLIENT")
//    class gettingOpcuaClient{
//
//        @Test @DisplayName("if client is not already existing then it is created")
//        void createsClientIfNotExists(){
//            Mockito.when(opcuaClientProvider.createClient(mockIpAddress)).thenReturn(opcuaClient);
//
//            assertThat(opcuaConnector.isEmpty()).isTrue();
//            assertThat(opcuaConnector.getClient(plc)).isNotNull();
//        }
//
//        @Test @DisplayName("throws error when client doesn't exist and new can not be created")
//        void throwsErrorWhenClientDoesNotExistAndNewCanNotBeCreated(){
//            Mockito.when(opcuaClientProvider.createClient(mockIpAddress)).thenThrow(new OpcuaConnectionException());
//
//            assertThrows(OpcuaConnectionException.class,() -> opcuaConnector.getClient(plc));
//        }
//    }
//
//
//
//
//
//
//
//
//
//
//}
