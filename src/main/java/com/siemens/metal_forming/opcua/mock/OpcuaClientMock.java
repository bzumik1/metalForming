package com.siemens.metal_forming.opcua.mock;

import com.siemens.metal_forming.exception.OpcuaConnectionException;
import com.siemens.metal_forming.opcua.OpcuaClient;
import com.siemens.metal_forming.opcua.impl.OpcuaConnectorImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig;
import org.eclipse.milo.opcua.stack.client.UaStackClient;

import java.util.concurrent.ExecutionException;

@Slf4j @FieldDefaults(level = AccessLevel.PRIVATE)
public class OpcuaClientMock extends OpcUaClient implements OpcuaClient  {
    String ipAddress;


    public OpcuaClientMock(OpcUaClientConfig config, UaStackClient stackClient, String ipAddress) {
        super(config, stackClient);
        this.ipAddress = ipAddress;
    }

    @Override
    public String readSerialNumber() {
        try {
            connect().get();
            //Read boolean value
            System.out.println("READ BOOLEAN VALUE:");
            boolean booleanOpcUa = (boolean)getAddressSpace().getVariableNode(booleanNode).get().getValue().get();
            System.out.println(booleanOpcUa);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new OpcuaConnectionException();
        }

        return null;
    }
}
