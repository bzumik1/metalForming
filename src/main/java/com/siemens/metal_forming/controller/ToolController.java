package com.siemens.metal_forming.controller;

import com.siemens.metal_forming.entity.Tool;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/plcs/{plcId}/tools")
public class ToolController {
    @GetMapping
    ResponseEntity<List<Tool>> getAllTools(@PathVariable Long plcId){
        return null;
    }

}
