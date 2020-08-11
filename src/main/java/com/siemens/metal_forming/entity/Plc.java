package com.siemens.metal_forming.entity;

import com.siemens.metal_forming.enumerated.ConnectionStatus;
import com.siemens.metal_forming.exception.ToolNotFoundException;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.sql.ConnectionPoolDataSource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Getter @Setter  @NoArgsConstructor @AllArgsConstructor @Builder @FieldDefaults(level = AccessLevel.PRIVATE)
@Entity @Table(name = "plc")
public class Plc {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;


    @NotBlank(message = "IP address can not be blank")
    @Pattern(regexp = "([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])\\.([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])\\." +
                      "([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])\\.([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])",
            message = "IP address must be in correct format")
    @Column(name = "ip_address", nullable = false, unique = true)
    String ipAddress;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "connection_id", nullable = false)
    final Connection connection = new Connection();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "curve_id")
    Curve motorCurve;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "current_tool")
    Tool currentTool;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "plc_id")
    Set<Tool> tools = new HashSet<>();

    public void setCurrentTool(int toolId){
        Optional<Tool> newCurrentTool = tools.stream().filter(tool -> tool.getToolId().equals(toolId)).findFirst();
        currentTool = newCurrentTool.orElseThrow(ToolNotFoundException::new);
    }

    public void markAsConnected(){
        connection.setStatus(ConnectionStatus.CONNECTED);
    }

    public void markAsDisconnected(){
        connection.setStatus(ConnectionStatus.DISCONNECTED);
    }
}
