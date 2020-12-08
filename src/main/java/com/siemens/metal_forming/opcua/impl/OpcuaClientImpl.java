package com.siemens.metal_forming.opcua.impl;

import com.siemens.metal_forming.SpringContext;
import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.entity.CurvePoint;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.opcua.OpcuaClient;
import com.siemens.metal_forming.opcua.configuration.OpcuaConfigurationImpl;
import com.siemens.metal_forming.opcua.structure.HmiTrend;
import com.siemens.metal_forming.service.PlcService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.SessionActivityListener;
import org.eclipse.milo.opcua.sdk.client.api.UaSession;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig;
import org.eclipse.milo.opcua.sdk.client.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaMonitoredItem;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscription;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscriptionManager;
import org.eclipse.milo.opcua.stack.client.UaStackClient;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.*;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoringParameters;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;


@Slf4j @FieldDefaults(level = AccessLevel.PRIVATE)
public class OpcuaClientImpl extends OpcUaClient implements OpcuaClient {
    String ipAddress;
    static final OpcuaConfigurationImpl configuration = SpringContext.getBean(OpcuaConfigurationImpl.class);
    final PlcService plcService = SpringContext.getBean(PlcService.class);

    static final NodeId serialNumberNode = configuration.getPlcSerialNumber().getNode();
    static final NodeId firmwareNumberNode = configuration.getPlcFirmwareNumber().getNode();

    static final NodeId toolNameNode = configuration.getToolName().getNode();
    static final NodeId toolNumberNode = configuration.getToolNumber().getNode();
    static final NodeId toolMaxSpeedOperationNode = configuration.getToolMaxSpeedOperation().getNode();

    static final NodeId immediateStopIndicatorNode = configuration.getImmediateStopIndicator().getNode();
    static final NodeId topPositionStopIndicatorNode = configuration.getTopPositionStopIndicator().getNode();

    static final NodeId hmiTrendNode = configuration.getHmiTrend().getNode();


    public OpcuaClientImpl(OpcUaClientConfig config, UaStackClient stackClient, String ipAddress) {
        super(config, stackClient);
        this.ipAddress = ipAddress;
        registerHmiTrendCodec(this);
        this.addSessionActivityListener(new SessionActivityListener(){
            private boolean firstRun = true;
            @Override
            public void onSessionActive(UaSession session) {
                if(firstRun){
                    firstRun = false;
                } else {
                    log.info("Connecting PLC with IP address {}",ipAddress);
                    plcService.update(ipAddress, Plc::markAsConnected);
                }

            }

            @Override
            public void onSessionInactive(UaSession session) {
                log.info("Disconnecting PLC with IP address {}",ipAddress);
                plcService.update(ipAddress, Plc::markAsDisconnected);
            }
        });

        try {
            this.connect().thenCompose(client -> subscribeAll()).get(); //Connects client and subscribe to given variables
        } catch (InterruptedException| ExecutionException e) {
            log.error("There was problem with creation of client: {}",e.getMessage());
        }
    }










    /////////////////////////////////MANUAL READ
    @Override
    public CompletableFuture<String> readSerialNumber() {
        return readString(serialNumberNode);
    }

    @Override
    public CompletableFuture<String> readFirmwareNumber() {
        return readString(firmwareNumberNode);
    }

    @Override
    public CompletableFuture<String> readToolName() {
        return readString(toolNameNode);
    }

    @Override
    public CompletableFuture<Integer> readToolNumber() {
        return readInteger(toolNumberNode);
    }

    @Override
    public CompletableFuture<Integer> readToolMaxSpeedOperation() {
        return readShort(toolMaxSpeedOperationNode);
    }



    private CompletableFuture<Integer> readUInteger(NodeId nodeId){
        return getAddressSpace()
                .getVariableNode(nodeId)
                .thenCompose(VariableNode::getValue)
                .thenApply(value -> ((UInteger)value).intValue());
    }

    private CompletableFuture<Integer> readInteger(NodeId nodeId){
        return getAddressSpace()
                .getVariableNode(nodeId)
                .thenCompose(VariableNode::getValue)
                .thenApply(value -> (Integer)value);
    }

    private CompletableFuture<Integer> readShort(NodeId nodeId){
        return getAddressSpace()
                .getVariableNode(nodeId)
                .thenCompose(VariableNode::getValue)
                .thenApply(value -> ((Short)value).intValue());
    }

    private CompletableFuture<String> readString(NodeId nodeId){
        return getAddressSpace()
                .getVariableNode(nodeId)
                .thenCompose(VariableNode::getValue)
                .thenApply(value -> (String)value);
    }

    private CompletableFuture<Float[]> readFloatArray(NodeId nodeId){
        return getAddressSpace()
                .getVariableNode(nodeId)
                .thenCompose(VariableNode::getValue)
                .thenApply(value -> (Float[])value);
    }


    /////////////////////////////////MANUAL WRITE

    @Override
    public CompletableFuture<Void> immediateStop() {
        return writeValue(immediateStopIndicatorNode,DataValue.valueOnly(new Variant(true)))
                .thenApply(statusCode -> {
                    if(statusCode.isBad()){
                        log.error("Wasn't able to write to immediateStopIndicator: {}", statusCode.toString());
                    }
                    return null;
                });
    }

    @Override
    public CompletableFuture<Void> topPositionStop() {
        return writeValue(topPositionStopIndicatorNode,DataValue.valueOnly(new Variant(true)))
                .thenApply(statusCode -> {
                    if(statusCode.isBad()){
                        log.error("Wasn't able to write to topPositionStopIndicator: {}", statusCode.toString());
                    }
                    return null;
                });
    }









    /////////////////////////////////SUBSCRIPTION ACTIONS
    private void onSerialNumberChange(DataValue value) {
        String serialNumber = (String)value.getValue().getValue();

        log.debug("Serial number of plc with IP address {} has changed to \"{}\"",ipAddress,serialNumber);
        plcService.update(ipAddress, plc ->
        {log.debug("Updating serial number of plc with IP address {} from \"{}\" to \"{}\"",ipAddress,plc.getHardwareInformation().getSerialNumber(),serialNumber);
            plc.getHardwareInformation().setSerialNumber(serialNumber);});
    }

    private void onFirmwareNumberChange(DataValue value) {
        String firmwareNumber = (String)value.getValue().getValue();

        log.debug("Firmware number of plc with IP address {} has changed to \"{}\"",ipAddress,firmwareNumber);
        plcService.update(ipAddress, plc ->
        {log.debug("Updating firmware number of plc with IP address {} from \"{}\" to \"{}\"",ipAddress,plc.getHardwareInformation().getFirmwareNumber(),firmwareNumber);
            plc.getHardwareInformation().setFirmwareNumber(firmwareNumber);});
    }

    private void onToolNumberChange(DataValue value) {
        int toolNumber = (int)value.getValue().getValue();
        log.debug("Tool number has changed to {}",toolNumber);
        plcService.changeCurrentTool(ipAddress,toolNumber);
    }

    private void onHmiTrendChange(DataValue value) {
        Variant variant = value.getValue();
        ExtensionObject xo = (ExtensionObject) variant.getValue();
        HmiTrend hmiTrend = (HmiTrend) xo.decode(getSerializationContext());

        log.debug("Received curve for PLC with id {}", ipAddress);
        Curve measuredCurve = new Curve(hmiTrend.getTorque(), hmiTrend.getSpeed());
        log.debug("Torque: {}", hmiTrend.getTorque());
        log.debug("Speed: {}", hmiTrend.getSpeed());

        plcService.processNewCurve(ipAddress,measuredCurve);
    }
    /////////////////////////////////SUBSCRIPTION ACTIONS










    /////////////////////////////////SUBSCRIPTION
    private CompletableFuture<Void> subscribeAll(){
        return subscribe(serialNumberNode, 1000,this::onSerialNumberChange)
                .thenCompose(nothing -> subscribe(firmwareNumberNode,1000,this::onFirmwareNumberChange))
                .thenCompose(nothing -> subscribe(toolNumberNode,1,this::onToolNumberChange))
                .thenCompose(nothing -> subscribe(hmiTrendNode,1,this::onHmiTrendChange))

                .whenComplete((nothing,ex) -> {
                    if(ex != null){
                        log.error("Subscription over OPC UA to plc with IP address {} was not successful",ipAddress);
                        throw new RuntimeException("SUBSCRIPTION ERROR"); //TODO create own exception
                    } else {
                        log.info("Subscription over OPC UA to plc with IP address {} was successful",ipAddress);
                    }
                });
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

        Consumer<DataValue> onChangeSkipFirstRun = new Consumer<DataValue>() {
            private boolean firstRun = true;
            @Override
            public void accept(DataValue dataValue) {
                if(firstRun){
                    firstRun = false;
                } else {
                    onChangeDo.accept(dataValue);
                }
            }
        };

        // setting the consumer after the subscription creation
        BiConsumer<UaMonitoredItem, Integer> onItemCreated = (monitoredItem, id) -> monitoredItem.setValueConsumer(onChangeSkipFirstRun);


        UaSubscriptionManager manager = getSubscriptionManager();
        manager.addSubscriptionListener(new UaSubscriptionManager.SubscriptionListener() {
            // resubscribe when subscription could not be transferred after connection interrupt
            // (this happens usually when server is restarted)
            @Override
            public void onSubscriptionTransferFailed(UaSubscription subscription, StatusCode statusCode) {
                log.debug("Unable to transfer subscription, resubscribing to "+ nodeToSubscribe.toString());
                subscribe(nodeToSubscribe,samplingInterval,onChangeSkipFirstRun);
            }
        });

        return manager
                .createSubscription(10.0)
                .thenCompose(subscription -> subscription.createMonitoredItems(TimestampsToReturn.Both, createRequests.apply(subscription),onItemCreated))
                .thenApply(object -> null);
    }



    // REGISTER DECODERS
    private void registerHmiTrendCodec(OpcUaClient client) {
        NodeId binaryEncodingId = HmiTrend.BINARY_ENCODING_ID
                .local(client.getNamespaceTable())
                .orElseThrow(() -> new IllegalStateException("namespace not found"));

        // Register codec with the client DataTypeManager instance
        client.getDataTypeManager().registerCodec(binaryEncodingId, new HmiTrend.Codec().asBinaryCodec());
    }
}
