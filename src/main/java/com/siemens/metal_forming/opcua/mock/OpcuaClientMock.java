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
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig;
import org.eclipse.milo.opcua.sdk.client.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaMonitoredItem;
import org.eclipse.milo.opcua.stack.client.UaStackClient;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoringParameters;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;

import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

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
        return getAddressSpace() //TODO connect before this
                .getVariableNode(nodeId)
                .thenCompose(VariableNode::getValue)
                .thenApply(value -> ((UInteger)value).intValue());
    }
    /////////////////////////////////MANUAL READ



    /////////////////////////////////SUBSCRIPTION ACTIONS

    private void onSerialNumberChange(DataValue value) {
        int opcuaInteger = ((UInteger)value.getValue().getValue()).intValue();
        String serialNumber = "SN 12345_"+opcuaInteger;

        log.info("Serial number of plc with IP address {} has changed to \"{}\"",ipAddress,serialNumber);

        Optional<Plc> plcInDd = plcService.findByIpAddress(ipAddress);
        if(plcInDd.isPresent()){
            log.info("Updating serial number of plc with IP address {} from \"{}\" to \"{}\"",ipAddress,plcInDd.get().getId(),serialNumber);
            plcInDd.get().markAsConnected();
            plcInDd.get().getHardwareInformation().setSerialNumber(serialNumber);
            plcService.updateById(plcInDd.get().getId(),plcInDd.get());

        }else {
            log.warn("Serial number of plc with IP address {} could not be updated because plc was not found in database",ipAddress);
        }
    }

    private void onFirmwareNumberChange(DataValue value) {
        int opcuaInteger = ((UInteger)value.getValue().getValue()).intValue();
        String firmwareNumber = "FW V1."+opcuaInteger;

        log.info("Firmware number of plc with IP address {} has changed to \"{}\"",ipAddress,firmwareNumber);

        Optional<Plc> plcInDd = plcService.findByIpAddress(ipAddress);
        if(plcInDd.isPresent()){
            log.info("Updating firmware number of plc with IP address {} from \"{}\" to \"{}\"",ipAddress,plcInDd.get().getId(),firmwareNumber);
            plcInDd.get().markAsConnected();
            plcInDd.get().getHardwareInformation().setFirmwareNumber(firmwareNumber);
            plcService.updateById(plcInDd.get().getId(),plcInDd.get());

        }else {
            log.warn("Firmware number of plc with IP address {} could not be updated because plc was not found in database",ipAddress);
        }
    }
    /////////////////////////////////SUBSCRIPTION ACTIONS



    @Override
    public CompletableFuture<Void> subscribeAll(){
        return subscribe(serialNumberNode, 10,this::onSerialNumberChange)
                .thenCompose(nothing -> subscribe(firmwareNumberNode,10000,this::onFirmwareNumberChange))
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
        // what to read
        ReadValueId readValueId = new ReadValueId(nodeToSubscribe, AttributeId.Value.uid(), null, null);

        // monitoring parameters
        int clientHandle = 123456789; //random number
        MonitoringParameters parameters = new MonitoringParameters(uint(clientHandle), samplingInterval, null, uint(10), true);

        // creation request
        MonitoredItemCreateRequest request = new MonitoredItemCreateRequest(readValueId, MonitoringMode.Reporting, parameters);

        // setting the consumer after the subscription creation
        BiConsumer<UaMonitoredItem, Integer> onItemCreated = (monitoredItem, id) -> monitoredItem.setValueConsumer(onChangeDo);


        return getSubscriptionManager()
                .createSubscription(10.0)
                .thenCompose(subscription -> subscription.createMonitoredItems(TimestampsToReturn.Both, Collections.singletonList(request),onItemCreated))
                .thenApply(object -> null);

    }
}
