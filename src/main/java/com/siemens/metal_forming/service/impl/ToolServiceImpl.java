package com.siemens.metal_forming.service.impl;

import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.enumerated.ToolStatusType;
import com.siemens.metal_forming.exception.exceptions.PlcNotFoundException;
import com.siemens.metal_forming.exception.exceptions.ToolNotFoundException;
import com.siemens.metal_forming.exception.exceptions.ToolNumberUpdateException;
import com.siemens.metal_forming.repository.PlcRepository;
import com.siemens.metal_forming.repository.ToolRepository;
import com.siemens.metal_forming.service.PlcService;
import com.siemens.metal_forming.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Consumer;

@Service
public class ToolServiceImpl implements ToolService {
    private final ToolRepository toolRepository;
    private final PlcRepository plcRepository;
    private final PlcService plcService;

    @Autowired
    public ToolServiceImpl( ToolRepository toolRepository, PlcRepository plcRepository, PlcService plcService) {
        this.toolRepository = toolRepository;
        this.plcRepository = plcRepository;
        this.plcService = plcService;
    }

    @Override
    public List<Tool> findAll(Long plcId) {
        if(!plcRepository.existsById(plcId)){
            throw new PlcNotFoundException(plcId);
        }
        return toolRepository.findAllByPlcId(plcId);
    }

    @Override
    public List<Tool> findAll() {
        return toolRepository.findAll();
    }

    @Override
    public void delete(Long plcId, Long toolId){
        Consumer<Plc> updatePlc = plc -> {
            Tool toolToBeRemoved = plc.getToolById(toolId);
            plc.removeTool(toolToBeRemoved);
        };
        plcService.update(plcId,updatePlc);
    }

    @Override
    public Tool create(Long plcId, Tool tool){
        Plc updatedPlc = plcService.update(plcId, plc -> plc.addTool(tool));
        return updatedPlc.getToolByToolNumber(tool.getToolNumber());
    }

    @Transactional
    @Override
    public Tool update(Long plcId, Long toolId, Consumer<Tool> updateTool) {
        Consumer<Plc> updatePlc = plc -> {
            Tool toolToBeUpdated = plc.getToolById(toolId);
            final Integer oldToolNumber = toolToBeUpdated.getToolNumber();
            plc.removeTool(toolToBeUpdated);
            updateTool.accept(toolToBeUpdated);
            if(!toolToBeUpdated.getToolNumber().equals(oldToolNumber) && toolToBeUpdated.getToolStatus() == ToolStatusType.AUTODETECTED){
                throw new ToolNumberUpdateException();
            }
            plc.addTool(toolToBeUpdated);
        };
        return plcService.update(plcId,updatePlc).getToolById(toolId);
    }

    @Override
    public void updateReferenceCurve(Long toolId, Curve referenceCurve) {
        Tool toolInDb = toolRepository.findById(toolId).orElseThrow(() -> new ToolNotFoundException(toolId));
        toolInDb.setReferenceCurve(referenceCurve);
        toolInDb.setCalculateReferenceCurve(false);
        toolRepository.save(toolInDb);
    }
}
