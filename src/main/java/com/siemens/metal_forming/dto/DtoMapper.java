package com.siemens.metal_forming.dto;


import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.entity.ReferenceCurve;
import com.siemens.metal_forming.entity.Tool;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DtoMapper {
    DtoMapper INSTANCE = Mappers.getMapper( DtoMapper.class );

    PlcDto.Response.Overview toPlcDtoOverview(Plc plc);

    @Mapping(target = "referenceCurveIsCalculated", source = "tool.referenceCurve")
    ToolDto.Response.Overview toToolDtoOverview(Tool tool);

    //@Mapping(target = "tools", ignore = true)
    @Mapping(target = "motorCurve", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "currentTool",ignore = true)
    Plc toPlc(PlcDto.Request.Create plcDto);

    Tool toTool(ToolDto.Request.Create toolDto);

    default boolean toReferenceCurveIsCalculated(Curve referenceCurve){
        return referenceCurve!=null;
    }
}
