package com.siemens.metal_forming.controller;

import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.service.PlcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/plcs")
public class PlcController {
    private final PlcService plcService;

    public PlcController(@Autowired PlcService plcService) {
        this.plcService = plcService;
    }

    @PostMapping
    public ResponseEntity<Plc> createPlc(@Valid @RequestBody Plc plc){
        return new ResponseEntity<>(plcService.create(plc), HttpStatus.ACCEPTED);
    }
}
