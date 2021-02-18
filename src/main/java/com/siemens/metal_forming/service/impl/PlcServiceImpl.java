package com.siemens.metal_forming.service.impl;

import com.siemens.metal_forming.connection.PlcConnector;
import com.siemens.metal_forming.dto.RestDtoMapper;
import com.siemens.metal_forming.dto.PlcDto;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.exception.exceptions.PlcNotFoundException;
import com.siemens.metal_forming.exception.exceptions.PlcUniqueConstrainException;
import com.siemens.metal_forming.repository.PlcRepository;
import com.siemens.metal_forming.service.PlcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @Slf4j
public class PlcServiceImpl implements PlcService {
    private final PlcRepository plcRepository;
    private final PlcConnector plcConnector;
    private final RestDtoMapper dtoMapper;

    @Autowired
    public PlcServiceImpl(PlcRepository plcRepository, PlcConnector plcConnector, RestDtoMapper dtoMapper) {
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
        return dtoMapper.toPlcDtoOverview(plcRepository.save(plcConnector.connect(plcToBeCreated)));
    }

    @Override
    public void delete(Long id) {
        Plc oldPlc = plcRepository.findById(id).orElseThrow(() -> new PlcNotFoundException(id));
        plcConnector.disconnect(oldPlc.getIpAddress());
        plcRepository.deleteById(id);
    }

    @Override
    public PlcDto.Response.Overview update(Long id, PlcDto.Request.Update plcDto) {
        Plc plcContainingUpdatedAttributes = Plc.builder().id(id).name(plcDto.getName()).ipAddress(plcDto.getIpAddress()).build();

        //checks if updated plc doesnt collide with plc in database
        validateUniquenessOfPlc(plcContainingUpdatedAttributes);

        Plc plcToUpdate = plcRepository.findByIdFetchAll(id).orElseThrow(() -> new PlcNotFoundException(id)); //ToDo should it fetch all or fetch when needed
        Plc oldPlc = plcToUpdate.toBuilder().build();


        plcToUpdate.setName(plcDto.getName());
        plcToUpdate.setIpAddress(plcDto.getIpAddress());



        //if plc has different IP address then it needs to be reconnected
        if(!plcToUpdate.getIpAddress().equals(oldPlc.getIpAddress())){
            log.info("IP address of plc with id {} was changed to {}",plcToUpdate.getId(),plcToUpdate.getIpAddress());
            plcConnector.disconnect(oldPlc.getIpAddress());
            plcConnector.connect(plcToUpdate);
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
}
