package com.siemens.metal_forming.connection.opcua;

import com.siemens.metal_forming.connection.*;
import com.siemens.metal_forming.domain.Curve;
import com.siemens.metal_forming.enumerated.ConnectionStatus;
import com.siemens.metal_forming.exception.exceptions.OpcuaClientException;
import com.siemens.metal_forming.connection.opcua.configuration.OpcuaConfiguration;
import com.siemens.metal_forming.connection.opcua.structure.HmiTrend;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.SessionActivityListener;
import org.eclipse.milo.opcua.sdk.client.api.UaSession;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaMonitoredItem;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscription;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscriptionManager;
import org.eclipse.milo.opcua.stack.client.DiscoveryClient;
import org.eclipse.milo.opcua.stack.client.UaStackClient;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.*;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoringParameters;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j @SuperBuilder
public class PlcDataOpcua extends PlcData {
    OpcUaClient client;
    final OpcuaConfiguration configuration;

    public PlcDataOpcua(OpcuaConfiguration configuration, String ipAddress){
        super(ipAddress);
        this.configuration = configuration;
        createClient();
    }

    @Override
    public void immediateStop() {

    }

    @Override
    public void topPositionStop() {

    }

    @Override
    public void disconnect() {
        client.disconnect();
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
                        .setRequestTimeout(uint(5_000))
                        .setKeepAliveFailuresAllowed(uint(0)) //Number of possible failures
                        .setKeepAliveInterval(uint(5_000)) //Time after which is session (connection) marked as disconnected
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

                // Register codecs
                registerHmiTrendCodec(uaClient);

                // Connect client
                connectClient(uaClient);
                log.info("PLC with IP address {} was successfully connected over OPC UA", ipAddress);
            } else {
                throw new OpcuaClientException("Client could not be created, there was no endpoint without security");
            }
        } catch (InterruptedException | ExecutionException e){
            log.warn("OPC UA connection for PLC with IP {} could not be established: {}",ipAddress, e.getMessage());
            setConnectionStatus(ConnectionStatus.DISCONNECTED);
            CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(this::reconnect);
        } catch (UaException e){
            log.warn("OPC UA client for plc with ip {} could not be created: {}",ipAddress,e.getMessage());
            e.printStackTrace();
            throw new OpcuaClientException("Client could not be created");
        }
    }

    private String createUrl(String ipAddress) {
        return "opc.tcp://"+ipAddress+":"+ configuration.getPort();
    }

    private void reconnect(){
        try {
            Thread.sleep(2000);
            createClient();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    private void connectClient(OpcUaClient client){
        try {
            this.client = client;
            //Connects client and subscribe to given variables
            client.connect().get();
            subscribeAll().get();
            Thread.sleep(2000); //ToDo -> solve it somehow, that it would wait till all values were read
        } catch (InterruptedException| ExecutionException e) {
            log.error("There was problem with creation of client: {}",e.getMessage());
        }
    }

    private CompletableFuture<Void> subscribe(NodeId nodeToSubscribe, double samplingInterval, Consumer<DataValue> onChangeDo) {
        Function<UaSubscription, List<MonitoredItemCreateRequest>> createRequests = subscription ->{
            // what to read
            ReadValueId readValueId = new ReadValueId(nodeToSubscribe, AttributeId.Value.uid(), null, null);

            // monitoring parameters
            UInteger clientHandle = subscription.nextClientHandle();
            MonitoringParameters parameters = new MonitoringParameters(clientHandle, samplingInterval, null, uint(10), true);

            // creation request
            MonitoredItemCreateRequest request = new MonitoredItemCreateRequest(readValueId, MonitoringMode.Reporting, parameters);

            return List.of(request);
        };

        // setting the consumer after the subscription creation
        BiConsumer<UaMonitoredItem, Integer> onItemCreated = (monitoredItem, id) -> monitoredItem.setValueConsumer(onChangeDo);


        UaSubscriptionManager manager = client.getSubscriptionManager();
        manager.addSubscriptionListener(new UaSubscriptionManager.SubscriptionListener() {
            // resubscribe when subscription could not be transferred after connection interrupt
            // (this happens usually when server is restarted)
            @Override
            public void onSubscriptionTransferFailed(UaSubscription subscription, StatusCode statusCode) {
                log.debug("Unable to transfer subscription, resubscribing to "+ nodeToSubscribe.toString());
                subscribe(nodeToSubscribe,samplingInterval,onChangeDo);
            }
        });

        return manager
                .createSubscription(10.0)
                .thenCompose(subscription -> subscription.createMonitoredItems(TimestampsToReturn.Both, createRequests.apply(subscription),onItemCreated))
                .thenApply(object -> null);
    }

    private CompletableFuture<Void> subscribeAll(){

        return subscribe(configuration.getPlcSerialNumber().getNode(), 1000,this::onSerialNumberChange)
                .thenCompose(nothing -> subscribe(configuration.getPlcFirmwareNumber().getNode(),1000,this::onFirmwareNumberChange))
                .thenCompose(nothing -> subscribe(configuration.getToolNumber().getNode(),1,this::onToolNumberChange))
                .thenCompose(nothing -> subscribe(configuration.getToolName().getNode(),1,this::onToolNameChange))
                .thenCompose(nothing -> subscribe(configuration.getHmiTrend().getNode(),1,this::onHmiTrendChange))

                .whenComplete((nothing,ex) -> {
                    if(ex != null){
                        log.error("Subscription over OPC UA to plc with IP address {} was not successful",ipAddress);
                        throw new RuntimeException("SUBSCRIPTION ERROR"); //TODO create own exception
                    } else {
                        log.info("Subscription over OPC UA to plc with IP address {} was successful",ipAddress);
                    }
                });
    }

    private void onSerialNumberChange(DataValue value) {
        setSerialNumber((String)value.getValue().getValue());
        log.debug("Serial number of PLC with IP address {} has changed to \"{}\"",ipAddress,getSerialNumber());
    }

    private void onFirmwareNumberChange(DataValue value) {
        setFirmwareNumber((String)value.getValue().getValue());
        log.debug("Firmware number of PLC with IP address {} has changed to \"{}\"",ipAddress,getFirmwareNumber());
    }

    private void onToolNumberChange(DataValue value) {
        setToolNumber((int)value.getValue().getValue());
        log.debug("Tool number of PLC with IP address {} has changed to \"{}\"",ipAddress, getToolNumber());
    }

    private void onToolNameChange(DataValue value) {
        setToolName((String)value.getValue().getValue());
        log.debug("Tool name of PLC with IP address {} has changed to \"{}\"",ipAddress, getToolName());
    }

    private void onHmiTrendChange(DataValue value) {
        Variant variant = value.getValue();
        ExtensionObject xo = (ExtensionObject) variant.getValue();
        HmiTrend hmiTrend = (HmiTrend) xo.decode(client.getSerializationContext());
        setMeasuredCurve(new Curve(hmiTrend.getTorque(), hmiTrend.getSpeed()));

        log.debug("Measured curve of PLC with IP address {} has changed to:\n\tTorque:{}\n\tSpeed: {}", ipAddress, hmiTrend.getTorque(), hmiTrend.getSpeed());
    }

    private void registerHmiTrendCodec(OpcUaClient client) {
        NodeId binaryEncodingId = HmiTrend.BINARY_ENCODING_ID
                .local(client.getNamespaceTable())
                .orElseThrow(() -> new IllegalStateException("namespace not found"));

        // Register codec with the client DataTypeManager instance
        client.getDataTypeManager().registerCodec(binaryEncodingId, new HmiTrend.Codec().asBinaryCodec());
    }
}
