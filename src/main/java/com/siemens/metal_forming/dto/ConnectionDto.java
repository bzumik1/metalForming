package com.siemens.metal_forming.dto;

import com.siemens.metal_forming.enumerated.ConnectionStatus;
import lombok.Builder;
import lombok.Value;

import java.sql.Timestamp;

@Value @Builder
public class ConnectionDto {
    Timestamp lastStatusChange;
    ConnectionStatus status;
}
