package com.siemens.metal_forming.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter @Setter @FieldDefaults(level = AccessLevel.PRIVATE) @EqualsAndHashCode @ToString
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")
@Table(name = "Tolerance")
public abstract class Tolerance {
    @EqualsAndHashCode.Exclude
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    public abstract boolean validate(PointOfTorqueAndSpeed referencePoint, PointOfTorqueAndSpeed pointToBeValidated);
}
