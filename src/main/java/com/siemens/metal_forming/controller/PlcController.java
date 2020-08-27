package com.siemens.metal_forming.controller;

import com.siemens.metal_forming.dto.DtoMapper;
import com.siemens.metal_forming.dto.PlcDto;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.service.PlcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/plcs")
public class PlcController {
    private final PlcService plcService;
    private final DtoMapper dtoMapper;

    public PlcController(@Autowired PlcService plcService, DtoMapper dtoMapper) {
        this.plcService = plcService;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping
    public ResponseEntity<List<PlcDto.Response.Overview>> getAllPlcs(){
        return ResponseEntity.ok(plcService.findAll().stream().map(dtoMapper::toPlcDtoOverview).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<PlcDto.Response.Overview> createPlc(@Valid @RequestBody PlcDto.Request.Create plcDto){
        Plc plc = dtoMapper.toPlc(plcDto);
        return new ResponseEntity<>(dtoMapper.toPlcDtoOverview(plcService.create(plc)), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PlcDto.Response.Overview> updatePlc(@PathVariable Long id, @Valid @RequestBody PlcDto.Request.Update plcDto){
        Consumer<Plc> attributesToUpdate = plc -> {
            plc.setName(plcDto.getName());
            plc.setIpAddress(plcDto.getIpAddress());
        };
        return new ResponseEntity<>(dtoMapper.toPlcDtoOverview(plcService.updateById(id,attributesToUpdate)),HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePlcById(@PathVariable Long id){
        plcService.deleteById(id);
    }
}