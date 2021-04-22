package com.siemens.metal_forming.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.siemens.metal_forming.annotations.ValidIpAddress;
import com.siemens.metal_forming.domain.Curve;
import com.siemens.metal_forming.entity.converter.CurveConverter;
import com.siemens.metal_forming.enumerated.ConnectionStatus;
import com.siemens.metal_forming.exception.exceptions.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
@Builder(toBuilder = true) @FieldDefaults(level = AccessLevel.PRIVATE)
@Entity @Table(name = "plcs")
@ToString
public class Plc {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private
    Long id;

    @NotBlank(message = "Name must be filled")
    @Column(nullable = false, unique = true)
    private
    String name;

    @ValidIpAddress
    @Column(name = "ip_address", nullable = false, unique = true)
    private
    String ipAddress;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hardware_information_id")
    private final HardwareInformation hardwareInformation = new HardwareInformation();

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "connection_id", nullable = false)
    private final Connection connection = new Connection();

    @ToString.Exclude
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "motor_curve", length = 10000)
    @Convert(converter = CurveConverter.class)
    private Curve motorCurve;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "current_tool")
    private
    Tool currentTool;

    @Singular("addTool")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plc", orphanRemoval = true)
    private final List<Tool> tools;

    public Plc(Long id, @NotBlank(message = "Name must be filled") String name, @ValidIpAddress String ipAddress, Curve motorCurve, Tool currentTool, List<Tool> tools) {
        this.id = id;
        this.name = name;
        this.ipAddress = ipAddress;
        this.motorCurve = motorCurve;
        this.currentTool = currentTool;
        this.tools = new ArrayList<>();

        //Sets plc for each tool
        tools.forEach(t -> {t.setPlc(this); addTool(t);});
    }

    public Plc() {
        tools = new ArrayList<>();
    }


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
        tools.forEach(this::addTool);
    }

    public void addTool(@NotNull Tool tool){
        if(tool == null) throw new InvalidToolException();

        if(hasToolByToolNumber(tool.getToolNumber())) throw new ToolUniqueConstrainException(tool.getToolNumber());
        tool.setPlc(this);
        tools.add(tool);
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
