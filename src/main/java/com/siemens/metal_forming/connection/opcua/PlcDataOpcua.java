package com.siemens.metal_forming.connection.opcua;

import com.siemens.metal_forming.connection.PlcData;
import com.siemens.metal_forming.connection.opcua.configuration.OpcuaConfiguration;
import com.siemens.metal_forming.connection.opcua.structure.CurveStructure;
import com.siemens.metal_forming.connection.opcua.structure.ToolDataStructure;
import com.siemens.metal_forming.domain.Curve;
import com.siemens.metal_forming.enumerated.ConnectionStatus;
import com.siemens.metal_forming.enumerated.StopReactionType;
import com.siemens.metal_forming.exception.exceptions.OpcuaClientException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.SessionActivityListener;
import org.eclipse.milo.opcua.sdk.client.api.UaSession;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscription;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscriptionManager;
import org.eclipse.milo.opcua.sdk.client.subscriptions.ManagedDataItem;
import org.eclipse.milo.opcua.sdk.client.subscriptions.ManagedSubscription;
import org.eclipse.milo.opcua.stack.client.DiscoveryClient;
import org.eclipse.milo.opcua.stack.client.UaStackClient;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.*;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j @SuperBuilder
public class PlcDataOpcua extends PlcData {
    OpcUaClient client;
    final OpcuaConfiguration configuration;
    boolean reconnecting = true;
    int reconnectionNumber = 0;

    public PlcDataOpcua(OpcuaConfiguration configuration, String ipAddress){
        super(ipAddress);
        this.configuration = configuration;
        createClient();
    }

    @Override
    public void notifyPlcAboutCollision(StopReactionType stopReactionType) {

    }

    @Override
    public void disconnect() {
        removeAllObservers();
        if(client != null){
            client.disconnect();
        } else {
            reconnecting = false;
        }
    }


    private void createClient(){
        try {
            // This will get all endpoints
            List<EndpointDescription> endpoints = DiscoveryClient.getEndpoints(createUrl(ipAddress)).get();
            // Select endpoint
            Optional<EndpointDescription> endpointWithoutSecurity = endpoints.stream().filter(endpointDescription -> endpointDescription.getSecurityMode().equals(MessageSecurityMode.None)).findFirst();

            if(endpointWithoutSecurity.isPresent()){
                // Needed only when it is redirected, replaces invalid url with correct one
                EndpointDescription endpoint = endpointWithoutSecurity.get().toBuilder().endpointUrl(createUrl(ipAddress)).build(); //ToDo

                // Sets configuration
                OpcUaClientConfig opcUaConfiguration = OpcUaClientConfig.builder()
                        .setEndpoint(endpoint)
                        .setApplicationName(LocalizedText.english(configuration.getApplicationName()))
                        .build();

                UaStackClient stackClient = UaStackClient.create(opcUaConfiguration);

                // Creates client
                OpcUaClient uaClient = new OpcUaClient(opcUaConfiguration, stackClient);

                // Adds activity listener
                uaClient.addSessionActivityListener(new SessionActivityListener(){
                    @Override
                    public void onSessionActive(UaSession session) {
                        log.info("Connecting PLC with IP address {}",ipAddress);
                        setConnectionStatus(ConnectionStatus.CONNECTED);
                    }

                    @Override
                    public void onSessionInactive(UaSession session) {
                        log.info("Disconnecting PLC with IP address {}",ipAddress);
                        setConnectionStatus(ConnectionStatus.DISCONNECTED);
                    }
                });

                //Adds subscription listener
                uaClient.getSubscriptionManager().addSubscriptionListener(new UaSubscriptionManager.SubscriptionListener() {
                    @Override
                    public void onSubscriptionTransferFailed(UaSubscription subscription, StatusCode statusCode) {
                        log.warn("Subscription transfer for PLC with IP address failed: {}, clearing old subscriptions and resubscribing again", statusCode);
                        uaClient.getSubscriptionManager().clearSubscriptions();
                        subscribeAll();
                    }
                });

                // Register codecs
                registerCustomCodecs(uaClient);

                // Connect client
                this.client = uaClient;
                client.connect().get();
                subscribeAll().get();

                log.info("PLC with IP address {} was successfully connected over OPC UA", ipAddress);
            } else {
                throw new OpcuaClientException("Client could not be created, there was no endpoint without security");
            }
        } catch (InterruptedException | ExecutionException e){
            log.debug("OPC UA connection for PLC with IP {} could not be established: {}",ipAddress, e.getMessage());
            setConnectionStatus(ConnectionStatus.DISCONNECTED);
            if(reconnecting){
                CompletableFuture.runAsync(this::reconnect);
            }
        } catch (UaException e){
            log.warn("OPC UA client for plc with ip {} could not be created: {}",ipAddress,e.getMessage());
            throw new OpcuaClientException("Client could not be created");
        }
    }

    private String createUrl(String ipAddress) {
        return "opc.tcp://"+ipAddress+":"+ configuration.getPort();
    }

    private void reconnect(){
        reconnectionNumber++;
        log.debug("Attempt to connect PLC with IP address {}: {}s",ipAddress,2*reconnectionNumber);
        try {
            Thread.sleep(2000);
            createClient();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    private CompletableFuture<DataValue> subscribe(NodeId nodeToSubscribe, double samplingInterval, Consumer<DataValue> onChangeDo){
        CompletableFuture<DataValue> result = new CompletableFuture<>();
        try{
            //sets parameters of subscription
            ManagedSubscription subscription = ManagedSubscription.create(client, samplingInterval);
            subscription.setDefaultSamplingInterval(samplingInterval);
            subscription.setDefaultQueueSize(uint(10));

            //adds "onChange" action
            ManagedDataItem managedDataItem = subscription.createDataItem(nodeToSubscribe, item -> item.addDataValueListener(onChangeDo));

            //wait till first value was read
            ManagedDataItem.DataValueListener listener = managedDataItem.addDataValueListener(result::complete);
            result.whenComplete((v,e) -> managedDataItem.removeDataValueListener(listener));
        } catch (UaException e){
            result.completeExceptionally(e);
        }

        return result;
    }

    private CompletableFuture<Void> subscribeAll(){
        CompletableFuture<Void> result = CompletableFuture.allOf(
                subscribe(configuration.getPlcSerialNumber().getNode(), 1000,this::onSerialNumberChange),
                subscribe(configuration.getPlcFirmwareNumber().getNode(),1000,this::onFirmwareNumberChange),
                subscribe(configuration.getToolData().getNode(),1,this::onToolDataChange),
                subscribe(configuration.getMeasuredCurve().getNode(),1,this::onMeasuredCurveChange),
                subscribe(configuration.getMotorCurve().getNode(),1000, this::onMotorCurveChange));

                result.whenComplete((data,ex) -> {
                    if(ex != null){
                        log.error("Subscription over OPC UA to plc with IP address {} was not successful",ipAddress);
                        throw new RuntimeException("SUBSCRIPTION ERROR"); //TODO create own exception
                    } else {
                        log.info("Subscription over OPC UA to plc with IP address {} was successful",ipAddress);
                    }
                });
        return result;
    }

    private void onSerialNumberChange(DataValue value) {
        setSerialNumber((String)value.getValue().getValue());
    }

    private void onFirmwareNumberChange(DataValue value) {
        setFirmwareNumber((String)value.getValue().getValue());
    }

    private void onToolDataChange(DataValue value) {
        Variant variant = value.getValue();
        ExtensionObject xo = (ExtensionObject) variant.getValue();
        ToolDataStructure toolDataStructure = (ToolDataStructure) xo.decode(client.getSerializationContext());
        setToolData(new ToolData(toolDataStructure.getToolNumber(), toolDataStructure.getToolName()));
    }

    private void onMeasuredCurveChange(DataValue value) {
        Variant variant = value.getValue();
        ExtensionObject xo = (ExtensionObject) variant.getValue();
        CurveStructure curveStructure = (CurveStructure) xo.decode(client.getSerializationContext());
        setMeasuredCurve(new Curve(curveStructure.getTorque(), curveStructure.getSpeed()));
    }

    private void onMotorCurveChange(DataValue value) {
        Variant variant = value.getValue();
        ExtensionObject xo = (ExtensionObject) variant.getValue();
        CurveStructure curveStructure = (CurveStructure) xo.decode(client.getSerializationContext());
        setMotorCurve(new Curve(curveStructure.getTorque(), curveStructure.getSpeed()));
    }

    private void registerCustomCodecs(OpcUaClient client) {
        NodeId curveBinaryEncodingId = CurveStructure.BINARY_ENCODING_ID
                .toNodeId(client.getNamespaceTable())
                .orElseThrow(() -> new IllegalStateException("namespace not found"));

        NodeId toolBinaryEncodingId = ToolDataStructure.BINARY_ENCODING_ID
                .toNodeId(client.getNamespaceTable())
                .orElseThrow(() -> new IllegalStateException("namespace not found"));

        // Register codec with the client DataTypeManager instance
        client.getDataTypeManager().registerCodec(curveBinaryEncodingId, new CurveStructure.Codec().asBinaryCodec());
        client.getDataTypeManager().registerCodec(toolBinaryEncodingId, new ToolDataStructure.Codec().asBinaryCodec());
    }
}
