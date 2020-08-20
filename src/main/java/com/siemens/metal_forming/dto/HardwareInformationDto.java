package com.siemens.metal_forming.dto;

import lombok.Builder;
import lombok.Value;


@Value @Builder
public class HardwareInformationDto {
    String serialNumber;
    String firmwareNumber;
}
