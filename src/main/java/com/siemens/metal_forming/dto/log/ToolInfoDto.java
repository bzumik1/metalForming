package com.siemens.metal_forming.dto.log;

import com.siemens.metal_forming.dto.ToleranceDto;
import com.siemens.metal_forming.enumerated.StopReactionType;
import lombok.Builder;
import lombok.Value;

@Value @Builder
public class ToolInfoDto {
    Long toolId;
    Integer toolNumber;
    String name;
    StopReactionType stopReaction;
    ToleranceDto tolerance;
}
