package com.siemens.metal_forming.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;


@Getter @Setter  @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "plc")
public class Plc {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;


    @NotBlank(message = "IP address can not be blank")
    @Pattern(regexp = "([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])\\.([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])\\." +
                      "([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])\\.([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])",
            message = "IP address must be in correct format")
    @Column(name = "ip_address", nullable = false, unique = true)
    String ipAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "curve_id")
    Curve motorCurve;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "plc_id")
    Set<Tool> tools;
}
