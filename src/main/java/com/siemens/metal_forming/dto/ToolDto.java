package com.siemens.metal_forming.dto;

import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.enumerated.StopReactionType;
import com.siemens.metal_forming.enumerated.ToolStatusType;
import lombok.Builder;
import lombok.Value;

import javax.persistence.*;
import javax.validation.constraints.*;

public enum ToolDto {;

    public enum Request{;
        @Value @Builder(toBuilder = true)
        public static class Create{
            @NotNull(message = "Tool ID must be filled")
            Integer toolId;

            String name;

            @NotNull(message = "Number of reference cycles must be filled")
            @Min(value = 1,message = "Number of reference cycles must me at least 1")
            @Max(value = 100, message = "Number of reference cycles can be maximally 100")
            Integer numberOfReferenceCycles;

            @NotNull(message = "Type of stop reaction must be selected")
            StopReactionType stopReaction;

            @NotNull(message = "Automatic monitoring must be filled")
            Boolean automaticMonitoring = false;
        }

        @Value @Builder(toBuilder = true)
        public static class Update{

        }
    }

    public enum Response{;
        @Value @Builder(toBuilder = true)
        public static class Overview{
            Integer toolId;
            String name;
            Integer numberOfReferenceCycles;
            StopReactionType stopReaction;
            boolean referenceCurveIsCalculated;
            boolean automaticMonitoring = false;
            ToolStatusType toolStatus;
        }

        @Value
        public static class Another{
        }
    }
}