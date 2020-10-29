package com.siemens.metal_forming.controller;

import com.siemens.metal_forming.dto.DtoMapper;
import com.siemens.metal_forming.dto.ToolDto;
import com.siemens.metal_forming.dto.ToolDto.Request.Create;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.enumerated.StopReactionType;
import com.siemens.metal_forming.enumerated.ToolStatusType;
import com.siemens.metal_forming.service.ToolService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Tag(name = "Tools")
@RestController
@RequestMapping(path = "/plcs")
public class ToolController {
    private final ToolService toolService;
    private final DtoMapper dtoMapper;



    public ToolController(@Autowired ToolService toolService, DtoMapper dtoMapper) {
        this.toolService = toolService;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping(path = "/tools")
    public List<ToolDto.Response.Overview> getAllTools(){
        return toolService.findAll().stream().map(dtoMapper::toToolDtoOverview).collect(Collectors.toList());
    }

    @GetMapping(path = "/{plcId}/tools")
    public List<ToolDto.Response.Overview> getAllTools(@PathVariable Long plcId){
        return toolService.findAll(plcId).stream().map(dtoMapper::toToolDtoOverview).collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/{plcId}/tools")
    public ToolDto.Response.Overview createTool(@PathVariable Long plcId,@Valid @RequestBody Create toolDto){
        Tool tool = dtoMapper.toTool(toolDto);
        return dtoMapper.toToolDtoOverview(toolService.create(plcId,tool));
    }

    @PutMapping(path = "/{plcId}/tools/{toolId}")
    public ToolDto.Response.Overview updateToolByPlcIdAndToolId(@PathVariable Long plcId, @PathVariable Long toolId, @Valid @RequestBody ToolDto.Request.Update toolDto){
        Consumer<Tool> updateAllAttributesSentFromFrontEnd = tool -> {
            tool.setToolNumber(toolDto.getToolNumber());
            tool.setName(toolDto.getName());
            tool.setNumberOfReferenceCycles(toolDto.getNumberOfReferenceCycles());
            tool.setCalculateReferenceCurve(toolDto.getCalculateReferenceCurve());
            tool.setStopReaction(toolDto.getStopReaction());
            tool.setAutomaticMonitoring(toolDto.getAutomaticMonitoring());
            tool.setToolStatus(toolDto.getToolStatus());
        };
        return dtoMapper.toToolDtoOverview(toolService.update(plcId,toolId,updateAllAttributesSentFromFrontEnd));
    }

    @DeleteMapping(path = "/{plcId}/tools/{toolId}")
    public void deleteToolByPlcIdAndToolId(@PathVariable Long plcId, @PathVariable Long toolId){
        toolService.delete(plcId,toolId);
    }

}
