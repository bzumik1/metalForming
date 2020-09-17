package com.siemens.metal_forming.testBuilders;

import com.siemens.metal_forming.entity.Connection;
import com.siemens.metal_forming.enumerated.ConnectionStatus;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.Timestamp;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestConnectionBuilder {
    Long id;
    Timestamp lastStatusChange = new Timestamp(System.currentTimeMillis());
    ConnectionStatus status = ConnectionStatus.DISCONNECTED;

    public TestConnectionBuilder id(Long id){
        this.id = id;
        return this;
    }

    public TestConnectionBuilder lastStatusChange(Timestamp lastStatusChange){
        this.lastStatusChange = lastStatusChange;
        return this;
    }

    public TestConnectionBuilder status(ConnectionStatus status){
        this.status = status;
        return this;
    }

    public Connection build(){
        Connection connectionToReturn = new Connection();
        ReflectionTestUtils.setField(connectionToReturn, "id", id);
        ReflectionTestUtils.setField(connectionToReturn, "lastStatusChange", lastStatusChange);
        ReflectionTestUtils.setField(connectionToReturn, "status", status);

        return connectionToReturn;
    }
}
