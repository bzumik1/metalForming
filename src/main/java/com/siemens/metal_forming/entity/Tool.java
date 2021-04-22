package com.siemens.metal_forming.entity;

import com.siemens.metal_forming.domain.Curve;
import com.siemens.metal_forming.entity.converter.CurveConverter;
import com.siemens.metal_forming.enumerated.StopReactionType;
import com.siemens.metal_forming.enumerated.ToolStatusType;
import com.siemens.metal_forming.exception.exceptions.ToolNumberUpdateException;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
@Getter @Setter @FieldDefaults(level = AccessLevel.PRIVATE) @NoArgsConstructor @AllArgsConstructor @Builder(toBuilder = true)
@EqualsAndHashCode @ToString
@Entity @Table(name = "tools", uniqueConstraints = {@UniqueConstraint(columnNames = {"plc_id","tool_number"})})
public class Tool{
    @EqualsAndHashCode.Exclude
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ToString.Exclude
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

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tolerance_id")
    Tolerance tolerance;

    @Enumerated(EnumType.STRING)
    @Column(name = "stop_reaction")
    @Builder.Default
    StopReactionType stopReaction = StopReactionType.DO_NOTHING;

    @NotNull
    @Column(name = "automatic_monitoring", nullable = false)
    Boolean automaticMonitoring = false;

    @Column(name = "max_speed_operation")
    Integer maxSpeedOperation;

    @NotNull
    @Column(name = "tool_status", nullable = false) @Enumerated(EnumType.STRING)
    ToolStatusType toolStatus;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "reference_curve", length = 10000)
    @Convert(converter = CurveConverter.class)
    Curve referenceCurve;

    public void setToolNumber(Integer toolNumber){
        if(toolStatus == ToolStatusType.AUTODETECTED && this.toolNumber != null && !this.toolNumber.equals(toolNumber) ){
            throw new ToolNumberUpdateException();
        }

        this.toolNumber = toolNumber;
    }
}
