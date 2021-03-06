package com.siemens.metal_forming.dto;


import com.siemens.metal_forming.domain.Curve;
import com.siemens.metal_forming.dto.log.LogDto;
import com.siemens.metal_forming.dto.log.ToolInfoDto;
import com.siemens.metal_forming.entity.*;
import com.siemens.metal_forming.entity.log.Log;
import com.siemens.metal_forming.entity.log.ToolInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RestDtoMapper {

    PlcDto.Response.Overview toPlcDtoOverview(Plc plc);

    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "name", expression = "java(tool.getNickName() != null  ? tool.getNickName() : tool.getNameFromPlc())")
    @Mapping(target = "plcId", source = "tool.plc.id")
    @Mapping(target = "referenceCurveIsCalculated", source = "tool.referenceCurve")
    ToolDto.Response.Overview toToolDtoOverview(Tool tool);

    @Mapping(target = "name", expression = "java(tool.getNickName() != null  ? tool.getNickName() : tool.getNameFromPlc())")
    @Mapping(target = "plcId", source = "tool.plc.id")
    @Mapping(target = "referenceCurveIsCalculated", source = "tool.referenceCurve")
    ToolDto.Response.Overview toToolDtoOverview(Tool tool, boolean isActive);

    default List<ToolDto.Response.Overview> toToolDtoOverview(List<Tool> tools, Set<Long> idsOfActiveTools){
        return tools.stream().map(tool -> {
            if (idsOfActiveTools.contains(tool.getId())){
                return toToolDtoOverview(tool, true);
            } else {
                return toToolDtoOverview(tool,false);
            }
        }).collect(Collectors.toList());
    }

    LogDto.Response.Detail toLogDtoDetail(Log log);

    @Mapping(target = "numberOfCollisions", expression = "java(log.getCollisionPoints()!= null ? log.getCollisionPoints().size():0)")
    LogDto.Response.Overview toLogDtoOverview(Log log);



    @Mapping(target = "name", expression = "java(toolInfo.getNickName() != null  ? toolInfo.getNickName() : toolInfo.getNameFromPlc())")
    ToolInfoDto toToolInfoDto(ToolInfo toolInfo);

    //@Mapping(target = "tools", ignore = true)
    @Mapping(target = "tools", ignore = true)
    @Mapping(target = "motorCurve", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "currentTool",ignore = true)
    Plc toPlc(PlcDto.Request.Create plcDto);

    @Mapping(target = "nickName", source = "name")
    @Mapping(target = "nameFromPlc", ignore = true)
    @Mapping(target = "referenceCurve", ignore = true)
    @Mapping(target = "plc", ignore = true)
    @Mapping(target = "maxSpeedOperation", ignore = true)
    @Mapping(target = "id", ignore = true)
    Tool toTool(ToolDto.Request.Create toolDto);

    @Mapping(target = "toolStatus", ignore = true)
    @Mapping(target = "nickName", source = "name")
    @Mapping(target = "nameFromPlc", ignore = true)
    @Mapping(target = "referenceCurve", ignore = true)
    @Mapping(target = "plc", ignore = true)
    @Mapping(target = "maxSpeedOperation", ignore = true)
    @Mapping(target = "id", ignore = true)
    Tool toTool(ToolDto.Request.Update toolDto);

    AbsoluteToleranceDto toAbsoluteToleranceDto(AbsoluteTolerance absoluteTolerance);
    RelativeToleranceDto toRelativeToleranceDto(RelativeTolerance relativeTolerance);

    @Mapping(target = "id", ignore = true)
    AbsoluteTolerance toAbsoluteTolerance(AbsoluteToleranceDto absoluteToleranceDto);
    @Mapping(target = "id", ignore = true)
    RelativeTolerance toRelativeTolerance(RelativeToleranceDto relativeToleranceDto);

    default boolean toReferenceCurveIsCalculated(Curve referenceCurve){
        return referenceCurve!=null;
    }
    default ToleranceDto toToleranceDto(Tolerance tolerance){
        if(tolerance instanceof AbsoluteTolerance) return toAbsoluteToleranceDto((AbsoluteTolerance)tolerance);
        if(tolerance instanceof RelativeTolerance) return toRelativeToleranceDto((RelativeTolerance)tolerance);
        else return null;
    }
    default Tolerance toTolerance(ToleranceDto toleranceDto){
        if(toleranceDto instanceof AbsoluteToleranceDto) return toAbsoluteTolerance((AbsoluteToleranceDto)toleranceDto);
        if(toleranceDto instanceof RelativeToleranceDto) return toRelativeTolerance((RelativeToleranceDto)toleranceDto);
        else return null;
    }
}
