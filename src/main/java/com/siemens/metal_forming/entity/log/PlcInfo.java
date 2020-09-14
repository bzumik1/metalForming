package com.siemens.metal_forming.entity.log;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter @FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true) @AllArgsConstructor @Builder(toBuilder = true)
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"name", "iPAddress","serialNumber","firmwareNumber"})})
public class PlcInfo {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotBlank(message = "Name must be filled")
    @Column(nullable = false, updatable = false)
    String name;

    @NotBlank(message = "IP address must be filled")
    @Pattern(regexp = "([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])\\.([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])\\." +
            "([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])\\.([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])",
            message = "IP address must be in correct format")
    @Column(nullable = false, updatable = false)
    String ipAddress;

    @NotEmpty(message = "Serial number must be filled")
    @Column(nullable = false, updatable = false)
    String serialNumber;

    @NotEmpty(message = "Firmware number must be filled")
    @Column(nullable = false, updatable = false)
    String firmwareNumber;
}
