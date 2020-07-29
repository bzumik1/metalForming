package com.siemens.metal_forming.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Getter @Setter
@Entity @Table(name = "plc")
public class Plc {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotBlank(message = "IP address can not be blank")
    @Pattern(regexp = "([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])\\.([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])\\." +
                      "([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])\\.([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])",
            message = "IP address must be in correct format")
    @Column(name = "ip_address", nullable = false)
    String ipAddress;

    @NotNull
    @OneToOne(orphanRemoval = true,cascade = CascadeType.ALL)
    @JoinColumn(name = "curve_id",nullable = false)
    Curve motorCurve;
}
