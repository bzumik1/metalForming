package com.siemens.metal_forming.dto;



import com.siemens.metal_forming.entity.Plc;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WebSocketDtoMapper {

    @Mapping(target = "connectionStatus", source = "connection.status")
    PlcDto.Response.Connection toPlcDtoConnection(Plc plc);

}
