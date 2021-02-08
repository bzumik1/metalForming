package com.siemens.metal_forming.controller;

import com.siemens.metal_forming.dto.PlcDto;
import com.siemens.metal_forming.service.PlcService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "PLCs")
@RestController
@RequestMapping(path = "/plcs")
public class PlcController {
    private final PlcService plcService;

    public PlcController(@Autowired PlcService plcService) {
        this.plcService = plcService;
    }

    @GetMapping
    public ResponseEntity<List<PlcDto.Response.Overview>> getAllPlcs(){
        return ResponseEntity.ok(plcService.findAll());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PlcDto.Response.Overview createPlc(@Valid @RequestBody PlcDto.Request.Create plcDto){
        return plcService.createPlc(plcDto);
    }

    @PutMapping(path = "/{plcId}")
    public PlcDto.Response.Overview updatePlc(@PathVariable Long plcId, @Valid @RequestBody PlcDto.Request.Update plcDto){
        return plcService.update(plcId, plcDto);
    }

    @DeleteMapping(path = "/{plcId}")
    public void deletePlcById(@PathVariable Long plcId){
        plcService.delete(plcId);
    }
}
