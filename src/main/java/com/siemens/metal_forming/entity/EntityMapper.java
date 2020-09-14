package com.siemens.metal_forming.entity;

import com.siemens.metal_forming.entity.log.CollisionPoint;
import com.siemens.metal_forming.entity.log.Log;
import com.siemens.metal_forming.entity.log.PlcInfo;
import com.siemens.metal_forming.entity.log.ToolInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.control.DeepClone;

import java.util.Set;

@Mapper(componentModel = "spring")//, mappingControl = DeepClone.class)
public interface EntityMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "toolInformation", source = "plc.currentTool")
    @Mapping(target = "referenceCurve", source = "plc.currentTool.referenceCurve",mappingControl = DeepClone.class)
    @Mapping(target = "plcInformation", source = "plc")
    @Mapping(target = "motorCurve", source = "plc.motorCurve", mappingControl = DeepClone.class)
    @Mapping(target = "comment", ignore = true)
    Log toLog(Plc plc, Curve measuredCurve, Set<CollisionPoint> collisionPoints);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "serialNumber", source = "hardwareInformation.serialNumber")
    @Mapping(target = "firmwareNumber", source = "hardwareInformation.firmwareNumber")
    PlcInfo toPlcInfo(Plc plc);

    @Mapping(target = "id", ignore = true)
    ToolInfo toToolInfo(Tool tool);


}


