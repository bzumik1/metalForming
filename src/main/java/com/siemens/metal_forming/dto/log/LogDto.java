package com.siemens.metal_forming.dto.log;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.sql.Timestamp;
import java.util.Set;


public abstract class LogDto {

    public static abstract class Request{
        @Value @Builder @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class Update {
            String comment;
            //this will be added in future, for now has to be here because jackson has problem with one field classes
            Set<String> tags;
        }
    }

    public static abstract class Response{
        @Value @Builder @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class Overview{
            Long id;
            Timestamp createdOn;
            CurveDto measuredCurve;
            CurveDto motorCurve;
            CurveDto referenceCurve;
            Set<PointOfTorqueAndSpeedDto> collisionPoints;
            PlcInfoDto plcInformation;
            ToolInfoDto toolInformation;
            String comment;
        }
    }
}
