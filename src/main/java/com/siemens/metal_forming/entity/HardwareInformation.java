package com.siemens.metal_forming.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder @FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class HardwareInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "serial_number")
    String serialNumber;

    @Column(name = "firmwareNumber")
    String firmwareNumber;
}
