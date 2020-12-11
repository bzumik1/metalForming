package com.siemens.metal_forming.service.impl;

import com.siemens.metal_forming.connection.PlcConnector;
import com.siemens.metal_forming.connection.PlcData;
import com.siemens.metal_forming.dto.DtoMapper;
import com.siemens.metal_forming.dto.PlcDto;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.enumerated.ConnectionStatus;
import com.siemens.metal_forming.enumerated.ToolStatusType;
import com.siemens.metal_forming.exception.exceptions.PlcNotFoundException;
import com.siemens.metal_forming.exception.exceptions.PlcUniqueConstrainException;
import com.siemens.metal_forming.repository.PlcRepository;
import com.siemens.metal_forming.service.PlcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service @Slf4j
public class PlcServiceImpl implements PlcService {
    private final PlcRepository plcRepository;
    private final PlcConnector plcConnector;
    private final DtoMapper dtoMapper;

    @Autowired
    public PlcServiceImpl(PlcRepository plcRepository,
                          PlcConnector plcConnector, DtoMapper dtoMapper) {
        this.plcRepository = plcRepository;
        this.plcConnector = plcConnector;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<PlcDto.Response.Overview> findAll() {
        return plcRepository.findAll().stream().map(dtoMapper::toPlcDtoOverview).collect(Collectors.toList());
    }

    @Override
    public PlcDto.Response.Overview find(Long id) {
        return dtoMapper.toPlcDtoOverview(plcRepository.findById(id).orElseThrow(() -> new PlcNotFoundException(id)));
    }

    @Override
    public PlcDto.Response.Overview createPlc(PlcDto.Request.Create plcDto) {
        Plc plcToBeCreated = dtoMapper.toPlc(plcDto);
        validateUniquenessOfPlc(plcToBeCreated);
        return dtoMapper.toPlcDtoOverview(plcRepository.save(connectPlc(plcToBeCreated)));
    }

    @Override @Transactional //ToDo should fetch all from db?, Shouldn't be here?
    public void connectAllPlcsInDatabase() {
        List<Plc> plcs = plcRepository.findAll().stream()
                .map(plc -> CompletableFuture.supplyAsync(() -> this.connectPlc(plc)))
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        log.info("Trying to connect to plcs with IP addresses: {} over OPC UA", plcs.stream().map(Plc::getIpAddress).collect(Collectors.joining(", ")));
        log.info("Plcs with IP addresses: {} were successfully connected.", plcs.stream().filter(Plc::isConnected).map(Plc::getIpAddress).collect(Collectors.joining(", ")));
        plcRepository.saveAll(plcs);
    }

    @Override
    public void delete(Long id) {
        Plc oldPlc = plcRepository.findById(id).orElseThrow(() -> new PlcNotFoundException(id));
        plcConnector.disconnectPlc(oldPlc.getIpAddress());
        plcRepository.deleteById(id);
    }


    @Override
    public PlcDto.Response.Overview update(Long id, PlcDto.Request.Update plcDto) {
        Plc plcToUpdate = plcRepository.findByIdFetchAll(id).orElseThrow(() -> new PlcNotFoundException(id)); //ToDo should it fetch all or fetch when needed
        Plc oldPlc = plcToUpdate.toBuilder().build();


        plcToUpdate.setName(plcDto.getName());
        plcToUpdate.setIpAddress(plcDto.getIpAddress());

        //checks if updated plc doesnt collide with plc in database
        validateUniquenessOfPlc(plcToUpdate);

        //if plc has different IP address then it needs to be reconnected
        if(!plcToUpdate.getIpAddress().equals(oldPlc.getIpAddress())){
            log.info("IP address of plc with id {} was changed to {}",plcToUpdate.getId(),plcToUpdate.getIpAddress());
            plcConnector.disconnectPlc(oldPlc.getIpAddress());
            connectPlc(plcToUpdate);
        }
        return dtoMapper.toPlcDtoOverview(plcRepository.save(plcToUpdate));
    }


    private void validateUniquenessOfPlc(Plc plc){
        boolean ipIsNotUnique = plc.getId() != null ? plcRepository.existsByIpAddressIgnoringId(plc.getIpAddress(), plc.getId()) : plcRepository.existsByIpAddress(plc.getIpAddress());
        boolean nameIsNotUnique = plc.getId() != null ? plcRepository.existsByNameIgnoringId(plc.getName(), plc.getId()) : plcRepository.existsByName(plc.getName());

        StringBuilder exceptionMessage = new StringBuilder();
        String separator = ", ";
        if(ipIsNotUnique){
            exceptionMessage.append("PLC with given IP address ").append(plc.getIpAddress()).append(" already exists").append(separator);
        }

        if (nameIsNotUnique){
            exceptionMessage.append("PLC with given name ").append(plc.getName()).append(" already exists").append(separator);
        }

        if(exceptionMessage.length()!=0){
            exceptionMessage.setLength(exceptionMessage.length()-separator.length());
            throw new PlcUniqueConstrainException(exceptionMessage.toString());
        }
    }


    private Plc connectPlc(Plc plc) {
        PlcData plcData = plcConnector.connectPlc(plc.getIpAddress());
        if(plcData.getConnectionStatus() == ConnectionStatus.CONNECTED){
            plc.markAsConnected();
            plc.getHardwareInformation().setSerialNumber(plcData.getSerialNumber());
            plc.getHardwareInformation().setFirmwareNumber(plcData.getFirmwareNumber());

            // set current tool
            Integer currentToolNumber = plcData.getToolNumber();
            if(plc.getCurrentTool() == null){
                Tool newTool = Tool.builder()
                        .toolNumber(currentToolNumber)
                        .nameFromPlc(plcData.getToolName())
                        .maxSpeedOperation(plcData.getMaxOperationSpeed())
                        .toolStatus(ToolStatusType.AUTODETECTED)
                        .automaticMonitoring(false)
                        .calculateReferenceCurve(false)
                        .build();
                plc.addTool(newTool);
            }
            plc.setCurrentTool(currentToolNumber);
            log.debug("All information about plc were successfully read");

        } else {
            plc.markAsDisconnected();
        }

        return plc;
    }


}
