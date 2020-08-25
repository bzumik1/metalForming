package com.siemens.metal_forming.dto;

import com.siemens.metal_forming.enumerated.ConnectionStatus;
import lombok.Builder;
import lombok.Value;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Value @Builder
public class ConnectionDto {
    Timestamp lastStatusChange;
    ConnectionStatus status;
}
