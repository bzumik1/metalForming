package com.siemens.metal_forming.connection.opcua;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter @Setter @AllArgsConstructor @FieldDefaults(level = AccessLevel.PRIVATE)
public class ToolData {
    Integer toolNumber;
    String toolName;
}
