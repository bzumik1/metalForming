package com.siemens.metal_forming.entity.log;

import com.siemens.metal_forming.enumerated.StopReactionType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter @FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true) @AllArgsConstructor @Builder
@Entity
public class ToolInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotNull
    @Column(nullable = false, updatable = false)
    Integer toolNumber;

    @Column(updatable = false)
    String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    StopReactionType stopReaction;
}
