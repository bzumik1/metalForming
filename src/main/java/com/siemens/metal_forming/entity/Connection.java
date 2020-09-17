package com.siemens.metal_forming.entity;

import com.siemens.metal_forming.enumerated.ConnectionStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @FieldDefaults(level = AccessLevel.PRIVATE)
@Entity @Table(name = "connections")
public class Connection {

    public Connection(ConnectionStatus connectionStatus){
        status = connectionStatus;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotNull
    @Column(name = "last_status_change", nullable = false)
    final Timestamp lastStatusChange = new Timestamp(System.currentTimeMillis());

    @NotNull
    @Column(name = "status", nullable = false)
    ConnectionStatus status = ConnectionStatus.DISCONNECTED;

    public void setStatus(ConnectionStatus status){
        updateTimestamp();
        this.status = status;
    }

    public void updateTimestamp(){
        lastStatusChange.setTime(System.currentTimeMillis());
    }




}
