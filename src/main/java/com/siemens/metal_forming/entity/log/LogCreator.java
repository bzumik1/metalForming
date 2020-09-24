package com.siemens.metal_forming.entity.log;

import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.entity.CurvePoint;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.entity.log.CollisionPoint;
import com.siemens.metal_forming.entity.log.Log;
import com.siemens.metal_forming.entity.log.PlcInfo;
import com.siemens.metal_forming.entity.log.ToolInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.control.DeepClone;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", mappingControl = DeepClone.class)
public interface LogCreator {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "toolInformation", source = "plc.currentTool")
    @Mapping(target = "referenceCurve", source = "plc.currentTool.referenceCurve", qualifiedByName = "deepCurveWithoutId")
    @Mapping(target = "plcInformation", source = "plc")
    @Mapping(target = "motorCurve", source = "plc.motorCurve", qualifiedByName = "deepCurveWithoutId")
    @Mapping(target = "comment", ignore = true)
    Log create(Plc plc, Curve measuredCurve, Set<CollisionPoint> collisionPoints);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "serialNumber", source = "hardwareInformation.serialNumber")
    @Mapping(target = "firmwareNumber", source = "hardwareInformation.firmwareNumber")
    PlcInfo toPlcInfo(Plc plc);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "toolId", source = "id")
    ToolInfo toToolInfo(Tool tool);

    @Named("deepCurveWithoutId")
    @Mapping(target = "id", ignore = true)
    Curve toCurveWithoutId(Curve curve);

    @Mapping(target = "id", ignore = true)
    CurvePoint toCurvePointWithoutId(CurvePoint curvePoint);

    @Mapping(target = "id", ignore = true)
    CollisionPoint toCollisionPoint(CollisionPoint collisionPoint);
}


