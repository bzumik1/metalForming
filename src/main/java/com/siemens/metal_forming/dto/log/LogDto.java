package com.siemens.metal_forming.dto.log;

import com.siemens.metal_forming.entity.log.PlcInfo;
import com.siemens.metal_forming.entity.log.ToolInfo;
import lombok.Builder;
import lombok.Value;

import java.sql.Timestamp;
import java.util.Set;

public abstract class LogDto {

    public static abstract class Request{
        @Value @Builder
        public static class Comment{
            String Comment;
        }
    }

    public static abstract class Response{
        @Value @Builder
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
