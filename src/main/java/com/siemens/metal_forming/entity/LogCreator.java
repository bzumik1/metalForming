package com.siemens.metal_forming.entity;

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
    ToolInfo toToolInfo(Tool tool);

    @Named("deepCurveWithoutId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "points", qualifiedByName = "deepCurvePointWithoutId")
    Curve toCurveWithoutId(Curve curve);

    @Named("deepCurvePointWithoutId")
    @Mapping(target = "id", ignore = true)
    List<CurvePoint> toCurvePointWithoutId(List<CurvePoint> curvePoints);

    CollisionPoint toCollisionPoint(CollisionPoint collisionPoint);
}


