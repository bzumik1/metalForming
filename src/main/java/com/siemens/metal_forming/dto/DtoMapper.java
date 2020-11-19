package com.siemens.metal_forming.dto;


import com.siemens.metal_forming.dto.log.LogDto;
import com.siemens.metal_forming.dto.log.ToolInfoDto;
import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.entity.log.Log;
import com.siemens.metal_forming.entity.log.ToolInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DtoMapper {

    PlcDto.Response.Overview toPlcDtoOverview(Plc plc);

    @Mapping(target = "name", expression = "java(tool.getNickName() != null  ? tool.getNickName() : tool.getNameFromPlc())")
    @Mapping(target = "plcId", source = "tool.plc.id")
    @Mapping(target = "referenceCurveIsCalculated", source = "tool.referenceCurve")
    ToolDto.Response.Overview toToolDtoOverview(Tool tool);

    LogDto.Response.Detail toLogDtoDetail(Log log);

    LogDto.Response.Overview toLogDtoOverview(Log log);


    @Mapping(target = "name", expression = "java(toolInfo.getNickName() != null  ? toolInfo.getNickName() : toolInfo.getNameFromPlc())")
    ToolInfoDto toToolInfoDto(ToolInfo toolInfo);

    //@Mapping(target = "tools", ignore = true)
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

    default boolean toReferenceCurveIsCalculated(Curve referenceCurve){
        return referenceCurve!=null;
    }
}
