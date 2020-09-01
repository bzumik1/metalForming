package com.siemens.metal_forming.opcua.mock;

import com.siemens.metal_forming.SpringContext;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.opcua.OpcuaClient;
import com.siemens.metal_forming.opcua.configuration.OpcuaConfigurationMock;
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
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoringParameters;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

@Slf4j @FieldDefaults(level = AccessLevel.PRIVATE)
public class OpcuaClientMock extends OpcUaClient implements OpcuaClient  {
    String ipAddress;
    static final OpcuaConfigurationMock configuration = SpringContext.getBean(OpcuaConfigurationMock.class);
    final PlcService plcService = SpringContext.getBean(PlcService.class);

    static final NodeId serialNumberNode = configuration.getPlcSerialNumber().getNode();
    static final NodeId firmwareNumberNode = configuration.getPlcFirmwareNumber().getNode();


    public OpcuaClientMock(OpcUaClientConfig config, UaStackClient stackClient, String ipAddress) {
        super(config, stackClient);
        this.ipAddress = ipAddress;
        this.addSessionActivityListener(new SessionActivityListener(){
            @Override
            public void onSessionActive(UaSession session) {
                log.info("Connecting PLC with IP address {}",ipAddress);
                plcService.update(ipAddress, Plc::markAsConnected);
            }

            @Override
            public void onSessionInactive(UaSession session) {
                log.info("Disconnecting PLC with IP address {}",ipAddress);
                plcService.update(ipAddress, Plc::markAsDisconnected);
            }
        });

        try {
            this.connect().thenCompose(client -> subscribeAll()).get(); //Connects client and subscribe to given variables
        } catch (InterruptedException|ExecutionException e) {
            log.error("There was problem with creation of client: {}",e.getMessage());
        }
    }










    /////////////////////////////////MANUAL READ
    @Override
    public CompletableFuture<String> readSerialNumber() {
        return readInteger(serialNumberNode)
                .thenApply(integer -> "SN raQds_"+integer);
    }

    @Override
    public CompletableFuture<String> readFirmwareNumber() {
        return readInteger(firmwareNumberNode)
                .thenApply(integer -> "FW V1."+integer);
    }

    private CompletableFuture<Integer> readInteger(NodeId nodeId){
        return getAddressSpace()
                .getVariableNode(nodeId)
                .thenCompose(VariableNode::getValue)
                .thenApply(value -> ((UInteger)value).intValue());
    }
    /////////////////////////////////MANUAL READ










    /////////////////////////////////SUBSCRIPTION ACTIONS
    private void onSerialNumberChange(DataValue value) {
        int opcuaInteger = ((UInteger)value.getValue().getValue()).intValue();
        String serialNumber = "SN 12345_"+opcuaInteger;

        log.debug("Serial number of plc with IP address {} has changed to \"{}\"",ipAddress,serialNumber);
        plcService.update(ipAddress, plc ->
                    {log.debug("Updating serial number of plc with IP address {} from \"{}\" to \"{}\"",ipAddress,plc.getHardwareInformation().getSerialNumber(),serialNumber);
                        plc.getHardwareInformation().setSerialNumber(serialNumber);});
    }

    private void onFirmwareNumberChange(DataValue value) {
        int opcuaInteger = ((UInteger)value.getValue().getValue()).intValue();
        String firmwareNumber = "FW V1."+opcuaInteger;

        log.debug("Firmware number of plc with IP address {} has changed to \"{}\"",ipAddress,firmwareNumber);
        plcService.update(ipAddress, plc ->
        {log.debug("Updating firmware number of plc with IP address {} from \"{}\" to \"{}\"",ipAddress,plc.getHardwareInformation().getFirmwareNumber(),firmwareNumber);
            plc.getHardwareInformation().setFirmwareNumber(firmwareNumber);});
    }
    /////////////////////////////////SUBSCRIPTION ACTIONS










    /////////////////////////////////SUBSCRIPTION
    private CompletableFuture<Void> subscribeAll(){
        return subscribe(serialNumberNode, 1000,this::onSerialNumberChange)
                .thenCompose(nothing -> subscribe(firmwareNumberNode,1000,this::onFirmwareNumberChange))

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


        // setting the consumer after the subscription creation
        BiConsumer<UaMonitoredItem, Integer> onItemCreated = (monitoredItem, id) -> monitoredItem.setValueConsumer(onChangeDo);


        UaSubscriptionManager manager = getSubscriptionManager();
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
    /////////////////////////////////SUBSCRIPTION

}
