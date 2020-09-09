package com.siemens.metal_forming.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.siemens.metal_forming.enumerated.ConnectionStatus;
import com.siemens.metal_forming.exception.exceptions.InvalidToolsException;
import com.siemens.metal_forming.exception.exceptions.ToolNotFoundException;
import com.siemens.metal_forming.exception.exceptions.ToolUniqueConstrainException;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter  @NoArgsConstructor @AllArgsConstructor @Builder(toBuilder = true) @FieldDefaults(level = AccessLevel.PRIVATE)
@Entity @Table(name = "plcs")
public class Plc {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotBlank(message = "Name must be filled")
    @Column(nullable = false, unique = true)
    String name;

    @NotBlank(message = "IP address must be filled")
    @Pattern(regexp = "([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])\\.([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])\\." +
                      "([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])\\.([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])",
            message = "IP address must be in correct format")
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plc")
    final Set<Tool> tools = new HashSet<>();

    public void setCurrentTool(int toolId){
        Optional<Tool> newCurrentTool = tools.stream().filter(tool -> tool.getToolNumber().equals(toolId)).findFirst();
        currentTool = newCurrentTool.orElseThrow(ToolNotFoundException::new);
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

    public void setTools(@NotNull Set<Tool> tools){
        if(tools == null){
            throw new InvalidToolsException();
        }
        this.tools.clear();
        tools.forEach(tool -> tool.setPlc(this));
        this.tools.addAll(tools);
    }

    public void addTool(Tool tool){
        tool.setPlc(this);
        if(!tools.add(tool)){
            throw new ToolUniqueConstrainException(tool.getToolNumber());
        }
    }

    public Tool getTool(int toolNumber){
        return tools.stream()
                .filter(tool -> tool.getToolNumber().equals(toolNumber))
                .findAny()
                .orElseThrow(() -> new ToolNotFoundException(toolNumber));
    }

    public Tool getTool(long toolId){
        return tools.stream()
                .filter(tool -> tool.getId().equals(toolId))
                .findAny()
                .orElseThrow(() -> new ToolNotFoundException(toolId));
    }

    public boolean removeTool(Tool tool){
        return tools.remove(tool);
    }
}
