package com.siemens.metal_forming.entity.log;

import com.siemens.metal_forming.domain.Curve;
import com.siemens.metal_forming.domain.PointOfTorqueAndSpeed;
import com.siemens.metal_forming.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.control.DeepClone;

import java.util.Set;

@Mapper(componentModel = "spring", mappingControl = DeepClone.class)
public interface LogCreator {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "toolInformation", source = "plc.currentTool")
    @Mapping(target = "referenceCurve", source = "plc.currentTool.referenceCurve")
    @Mapping(target = "plcInformation", source = "plc")
    @Mapping(target = "motorCurve", source = "plc.motorCurve")
    @Mapping(target = "comment", ignore = true)
    Log create(Plc plc, Curve measuredCurve, Set<PointOfTorqueAndSpeed> collisionPoints);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "serialNumber", source = "hardwareInformation.serialNumber")
    @Mapping(target = "firmwareNumber", source = "hardwareInformation.firmwareNumber")
    PlcInfo toPlcInfo(Plc plc);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "toolId", source = "id")
    @Mapping(target = "tolerance", source = "tolerance", qualifiedByName = "toleranceWithoutId")
    ToolInfo toToolInfo(Tool tool);

    @Mapping(target = "id", ignore = true)
    AbsoluteTolerance toAbsoluteTolerance(AbsoluteTolerance absoluteTolerance);
    @Mapping(target = "id", ignore = true)
    RelativeTolerance toRelativeTolerance(RelativeTolerance relativeTolerance);

    @Named("toleranceWithoutId")
    default Tolerance toTolerance(Tolerance tolerance){
        if(tolerance instanceof AbsoluteTolerance) return toAbsoluteTolerance((AbsoluteTolerance) tolerance);
        if(tolerance instanceof RelativeTolerance) return toRelativeTolerance((RelativeTolerance) tolerance);
        else return null;
    }
}


