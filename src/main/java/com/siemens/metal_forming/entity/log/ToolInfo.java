package com.siemens.metal_forming.entity.log;

import com.siemens.metal_forming.entity.Tolerance;
import com.siemens.metal_forming.enumerated.StopReactionType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter @FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder @EqualsAndHashCode
@Entity
public final class ToolInfo {
    @EqualsAndHashCode.Exclude
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotNull
    @Column(nullable = false,updatable = false)
    Long toolId;

    @NotNull
    @Column(nullable = false, updatable = false)
    Integer toolNumber;

    //@NotBlank //ToDo uncomment after testing and also set nullable to true
    @Column(updatable = false)
    String nameFromPlc;

    @Column(updatable = false)
    String nickName;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    StopReactionType stopReaction;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tolerance_id")
    Tolerance tolerance;
}
