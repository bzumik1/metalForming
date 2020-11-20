package com.siemens.metal_forming.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.siemens.metal_forming.annotations.MaxOneField;
import com.siemens.metal_forming.enumerated.StopReactionType;
import com.siemens.metal_forming.enumerated.ToolStatusType;
import lombok.Builder;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.*;

public enum ToolDto {;

    public enum Request{;
        @MaxOneField.List({
                @MaxOneField(first = "absoluteTolerance", second = "relativeTolerance", message = "Maximally one tolerance can be set")
        })
        @Value @Builder(toBuilder = true)
        public static class Create{
            @NotNull(message = "Tool number must be filled")
            Integer toolNumber;

            String name;

            @NotNull(message = "Number of reference cycles must be filled")
            @Min(value = 1,message = "Number of reference cycles must me at least 1")
            @Max(value = 100, message = "Number of reference cycles can be maximally 100")
            Integer numberOfReferenceCycles;

            @NotNull(message = "Calculate reference curve must be filled")
            Boolean calculateReferenceCurve;

            @Valid
            ToleranceDto tolerance;

            @NotNull(message = "Type of stop reaction must be selected")
            StopReactionType stopReaction;

            @NotNull(message = "Automatic monitoring must be filled")
            Boolean automaticMonitoring;

            ToolStatusType toolStatus = ToolStatusType.MANUALLY_ADDED;
        }

        @MaxOneField.List({
                @MaxOneField(first = "absoluteTolerance", second = "relativeTolerance", message = "Maximally one tolerance can be set")
        })
        @Value @Builder(toBuilder = true)
        public static class Update{
            @NotNull(message = "Tool number must be filled")
            Integer toolNumber;

            String name;

            @NotNull(message = "Number of reference cycles must be filled")
            @Min(value = 1,message = "Number of reference cycles must me at least 1")
            @Max(value = 100, message = "Number of reference cycles can be maximally 100")
            Integer numberOfReferenceCycles;

            @NotNull(message = "Calculate reference curve must be filled")
            Boolean calculateReferenceCurve;

            @Valid
            ToleranceDto tolerance;

            @NotNull(message = "Type of stop reaction must be selected")
            StopReactionType stopReaction;

            @NotNull(message = "Automatic monitoring must be filled")
            Boolean automaticMonitoring;
        }
    }

    public enum Response{;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @Value @Builder(toBuilder = true)
        public static class Overview{
            Long id;
            Long plcId;
            Integer toolNumber;
            String name;
            Integer numberOfReferenceCycles;
            Boolean calculateReferenceCurve;
            ToleranceDto tolerance;
            StopReactionType stopReaction;
            Boolean referenceCurveIsCalculated;
            Boolean automaticMonitoring;
            ToolStatusType toolStatus;
        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @Value
        public static class Another{
        }
    }
}
