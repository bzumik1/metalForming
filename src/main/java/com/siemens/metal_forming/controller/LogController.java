package com.siemens.metal_forming.controller;

import com.siemens.metal_forming.dto.DtoMapper;
import com.siemens.metal_forming.dto.log.LogDto;
import com.siemens.metal_forming.service.LogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Tag(name = "Logs")
@RestController
@RequestMapping(path = "/logs")
public class LogController {
    private final LogService logService;
    private final DtoMapper dtoMapper;

    @Autowired
    public LogController(LogService logService, DtoMapper dtoMapper) {
        this.logService = logService;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping
    public List<LogDto.Response.Overview> getAllLogsForOneTool(@RequestParam(name = "tool-id")Long toolId){
        return logService.findAllByToolId(toolId).stream().map(dtoMapper::toLogDtoOverview).collect(Collectors.toList());
    }

    @DeleteMapping
    public void deleteAllLogsWithGivenIds(@RequestBody Set<Long> ids){
        logService.delete(ids);
    }

    @PutMapping(path = "/{id}")
    public LogDto.Response.Overview updateCommentOfLog(@PathVariable Long id, @RequestBody LogDto.Request.Update logDto){
        return dtoMapper.toLogDtoOverview(logService.updateComment(id,logDto.getComment()));
    }
}
