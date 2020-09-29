package com.siemens.metal_forming.dto.log;

import lombok.Builder;
import lombok.Value;

@Value @Builder
public class PlcInfoDto {
    String name;
    String ipAddress;
    String serialNumber;
    String firmwareNumber;
}
