package com.siemens.metal_forming.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.siemens.metal_forming.annotations.ValidIpAddress;
import com.siemens.metal_forming.enumerated.ConnectionStatus;
import com.siemens.metal_forming.exception.exceptions.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter  @NoArgsConstructor @AllArgsConstructor @Builder(toBuilder = true) @FieldDefaults(level = AccessLevel.PRIVATE)
@Entity @Table(name = "plcs")
public class Plc {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotBlank(message = "Name must be filled")
    @Column(nullable = false, unique = true)
    String name;

    @ValidIpAddress
    @Column(name = "ip_address", nullable = false, unique = true)
    String ipAddress;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hardware_information_id")
    final HardwareInformation hardwareInformation = new HardwareInformation();

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plc", orphanRemoval = true)
    final Set<Tool> tools = new TreeSet<>(Comparator.comparing(Tool::getToolNumber));



    public void markAsConnected(){
        connection.setStatus(ConnectionStatus.CONNECTED);
    }

    public void markAsDisconnected(){
        connection.setStatus(ConnectionStatus.DISCONNECTED);
    }

    public void setConnectionStatus(ConnectionStatus connectionStatus){
        connection.setStatus(connectionStatus);
    }

    public boolean isConnected(){
        return connection.getStatus() == ConnectionStatus.CONNECTED;
    }

    public void setCurrentTool(@NotNull Integer toolNumber){
        if (toolNumber == null) throw new InvalidToolNumberException();

        Optional<Tool> newCurrentTool = tools.stream().filter(tool -> tool.getToolNumber().equals(toolNumber)).findFirst();
        currentTool = newCurrentTool.orElseThrow(ToolNotFoundException::new);
    }

    public void setTools(@NotNull Set<Tool> tools){
        if(tools == null)throw new InvalidToolsException();

        this.tools.clear();
        tools.forEach(tool -> tool.setPlc(this));
        this.tools.addAll(tools);
    }

    public void addTool(@NotNull Tool tool){
        if(tool == null) throw new InvalidToolException();

        tool.setPlc(this);
        if(!tools.add(tool)) throw new ToolUniqueConstrainException(tool.getToolNumber());
    }

    public boolean hasToolByToolNumber(@NotNull Integer toolNumber){
        return tools.stream().anyMatch(tool -> tool.getToolNumber().equals(toolNumber));
    }

    public Tool getToolByToolNumber(@NotNull Integer toolNumber){
        if(toolNumber==null) throw new InvalidToolNumberException();

        return tools.stream()
                .filter(tool -> tool.getToolNumber().equals(toolNumber))
                .findAny()
                .orElseThrow(() -> new ToolNotFoundException(toolNumber));
    }

    public Tool getToolById(@NotNull Long toolId){
        if(toolId == null) throw new InvalidIdException();

        return tools.stream()
                .filter(tool -> tool.getId().equals(toolId))
                .findAny()
                .orElseThrow(() -> new ToolNotFoundException(toolId));
    }

    public boolean removeTool(@NotNull Tool tool){
        return tools.remove(tool);
    }
}
