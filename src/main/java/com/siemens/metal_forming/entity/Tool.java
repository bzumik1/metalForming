package com.siemens.metal_forming.entity;

import com.siemens.metal_forming.enumerated.StopReactionType;
import com.siemens.metal_forming.enumerated.ToolStatusType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter @Setter @FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Tool {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotNull
    @Column(name = "tool_id", nullable = false)
    Integer toolId;

    @Column(name = "number_of_reference_cycles")
    Integer numberOfReferenceCycles;

    @Column(name = "stop_reaction") @Enumerated(EnumType.STRING)
    StopReactionType stopReaction;

    @NotNull
    @Column(name = "automatic_monitoring", nullable = false)
    Boolean automaticMonitoring = false;

    @Column(name = "max_speed_operation")
    Integer maxSpeedOperation;

    @NotNull
    @Column(name = "tool_status", nullable = false) @Enumerated(EnumType.STRING)
    ToolStatusType toolStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reference_curve_id")
    Curve referenceCurve;









}
