package com.siemens.metal_forming.service.impl;

import com.siemens.metal_forming.dto.RestDtoMapper;
import com.siemens.metal_forming.dto.ToolDto;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.enumerated.ToolStatusType;
import com.siemens.metal_forming.exception.exceptions.CurrentToolCanNotBeDeletedException;
import com.siemens.metal_forming.exception.exceptions.PlcNotFoundException;
import com.siemens.metal_forming.exception.exceptions.ToolNumberUpdateException;
import com.siemens.metal_forming.repository.PlcRepository;
import com.siemens.metal_forming.repository.ToolRepository;
import com.siemens.metal_forming.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class ToolServiceImpl implements ToolService {
    private final ToolRepository toolRepository;
    private final PlcRepository plcRepository;
    private final RestDtoMapper dtoMapper;

    @Autowired
    public ToolServiceImpl( ToolRepository toolRepository, PlcRepository plcRepository, RestDtoMapper dtoMapper) {
        this.toolRepository = toolRepository;
        this.plcRepository = plcRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override @Transactional
    public List<ToolDto.Response.Overview> findAll(Long plcId) {
        Plc plcInDb = plcRepository.findByIdFetchTools(plcId).orElseThrow(() -> new PlcNotFoundException(plcId));

        Set<Long> setOfIdsOfCurrentTool = plcInDb.getCurrentTool()== null? Set.of():Set.of(plcInDb.getCurrentTool().getId());

        return dtoMapper.toToolDtoOverview(plcInDb.getTools(), setOfIdsOfCurrentTool);
    }

    @Override @Transactional
    public List<ToolDto.Response.Overview> findAll() {
        return dtoMapper.toToolDtoOverview(toolRepository.findAll(),toolRepository.getIdsOfUsedTools());
    }

    @Override
    public void delete(Long plcId, Long toolId){
        Plc plcInDb = plcRepository.findByIdFetchTools(plcId).orElseThrow(() -> new PlcNotFoundException(plcId));

        if(plcInDb.getCurrentTool() != null && plcInDb.getCurrentTool().getId().equals(toolId)){
            throw new CurrentToolCanNotBeDeletedException(toolId);
        }

        Tool toolToBeRemoved = plcInDb.getToolById(toolId);
        plcInDb.removeTool(toolToBeRemoved);

        plcRepository.save(plcInDb);
    }

    @Override
    public ToolDto.Response.Overview create(Long plcId, ToolDto.Request.Create toolDto){
        Tool newTool = dtoMapper.toTool(toolDto);
        Plc plcInDb = plcRepository.findByIdFetchTools(plcId).orElseThrow(() -> new PlcNotFoundException(plcId));

        plcInDb.addTool(newTool);

        return dtoMapper.toToolDtoOverview(plcRepository.save(plcInDb).getToolByToolNumber(newTool.getToolNumber()));
    }

    @Transactional
    @Override
    public ToolDto.Response.Overview update(Long plcId, Long toolId, ToolDto.Request.Update toolDto) {
        Plc plcInDb = plcRepository.findByIdFetchTools(plcId).orElseThrow(() -> new PlcNotFoundException(plcId));

        Tool updatedTool = dtoMapper.toTool(toolDto);
        Tool toolToUpdate = plcInDb.getToolById(toolId);
        final Integer oldToolNumber = toolToUpdate.getToolNumber();
        plcInDb.removeTool(toolToUpdate);


        toolToUpdate.setToolNumber(updatedTool.getToolNumber());
        toolToUpdate.setNickName(updatedTool.getNickName());
        toolToUpdate.setNumberOfReferenceCycles(updatedTool.getNumberOfReferenceCycles());
        toolToUpdate.setCalculateReferenceCurve(updatedTool.getCalculateReferenceCurve());
        toolToUpdate.setTolerance(updatedTool.getTolerance());
        toolToUpdate.setStopReaction(updatedTool.getStopReaction());
        toolToUpdate.setAutomaticMonitoring(updatedTool.getAutomaticMonitoring());


        if(!toolToUpdate.getToolNumber().equals(oldToolNumber) && toolToUpdate.getToolStatus() == ToolStatusType.AUTODETECTED){
            throw new ToolNumberUpdateException();
        }
        plcInDb.addTool(toolToUpdate);


        return dtoMapper.toToolDtoOverview(plcRepository.save(plcInDb).getToolById(toolId));
    }
}
