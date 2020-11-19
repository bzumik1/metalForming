package com.siemens.metal_forming.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter @Setter @FieldDefaults(level = AccessLevel.PRIVATE) @NoArgsConstructor @EqualsAndHashCode @ToString
@Entity
public class AbsoluteTolerance {
    @EqualsAndHashCode.Exclude
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(nullable = false)
    float torqueTolerance;
    @Column(nullable = false)
    float speedTolerance;

    public AbsoluteTolerance(float torqueTolerance, float speedTolerance){
        this.torqueTolerance = torqueTolerance;
        this.speedTolerance = speedTolerance;
    }
}
