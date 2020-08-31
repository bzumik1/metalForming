package com.siemens.metal_forming.controller;

import com.siemens.metal_forming.dto.DtoMapper;
import com.siemens.metal_forming.dto.ToolDto;
import com.siemens.metal_forming.dto.ToolDto.Request.Create;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.service.ToolService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Tools")
@RestController
@RequestMapping(path = "/plcs/{plcId}/tools")
public class ToolController {
    private final ToolService toolService;
    private final DtoMapper dtoMapper;



    public ToolController(@Autowired ToolService toolService, DtoMapper dtoMapper) {
        this.toolService = toolService;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping
    public ResponseEntity<List<ToolDto.Response.Overview>> getAllTools(@PathVariable Long plcId){
        return ResponseEntity.ok(toolService.findAll(plcId).stream().map(dtoMapper::toToolDtoOverview).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<ToolDto.Response.Overview> createTool(@PathVariable Long plcId,@Valid @RequestBody Create tool){
        return null;
    }

    @DeleteMapping(path = "/{toolId}")
    public void deleteToolByPlcIdAndToolId(@PathVariable Long plcId, @PathVariable Long toolId){
        toolService.deleteByPlcIdAndToolId(plcId,toolId);
    }

}
