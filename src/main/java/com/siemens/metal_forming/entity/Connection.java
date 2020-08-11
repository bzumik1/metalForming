package com.siemens.metal_forming.entity;

import com.siemens.metal_forming.enumerated.ConnectionStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter @Setter @NoArgsConstructor @FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Connection {

    public Connection(ConnectionStatus connectionStatus){
        status = connectionStatus;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotNull
    @Column(name = "last_status_update", nullable = false)
    final Timestamp lastStatusUpdate = new Timestamp(System.currentTimeMillis());

    @NotNull
    @Column(name = "status", nullable = false)
    ConnectionStatus status = ConnectionStatus.DISCONNECTED;

    public void setStatus(ConnectionStatus status){
        updateTimestamp();
        this.status = status;
    }

    public void updateTimestamp(){
        lastStatusUpdate.setTime(System.currentTimeMillis());
    }




}
