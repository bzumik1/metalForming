package com.siemens.metal_forming.entity;

import com.siemens.metal_forming.annotations.MaxOneField;
import com.siemens.metal_forming.enumerated.StopReactionType;
import com.siemens.metal_forming.enumerated.ToolStatusType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
@MaxOneField.List({
        @MaxOneField(first = "absoluteTolerance", second = "relativeTolerance", message = "Maximally one tolerance can be set")
})
@Getter @Setter @FieldDefaults(level = AccessLevel.PRIVATE) @NoArgsConstructor @AllArgsConstructor @Builder(toBuilder = true)
@EqualsAndHashCode @ToString
@Entity @Table(name = "tools")
public class Tool{
    @EqualsAndHashCode.Exclude
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name = "plc_id", nullable = false)
    Plc plc;


    @NotNull
    @Column(name = "tool_number", nullable = false)
    Integer toolNumber;

    String nameFromPlc;

    String nickName;

    @Column(name = "number_of_reference_cycles")
    Integer numberOfReferenceCycles;

    @Column(name = "calculate_reference_curve")
    Boolean calculateReferenceCurve;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    AbsoluteTolerance absoluteTolerance;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    RelativeTolerance relativeTolerance;

    @Enumerated(EnumType.STRING)
    @Column(name = "stop_reaction")
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
    @PrimaryKeyJoinColumn
    Curve referenceCurve;

    public void setAbsoluteTolerance(AbsoluteTolerance absoluteTolerance) {
        if (absoluteTolerance != null) relativeTolerance = null;
        this.absoluteTolerance = absoluteTolerance;
    }

    public void setRelativeTolerance(RelativeTolerance relativeTolerance){
        if (relativeTolerance != null) absoluteTolerance = null;
        this.relativeTolerance = relativeTolerance;
    }
}
