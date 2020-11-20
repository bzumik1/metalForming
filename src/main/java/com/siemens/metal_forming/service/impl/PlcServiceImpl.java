package com.siemens.metal_forming.service.impl;

import com.siemens.metal_forming.domain.ReferenceCurveCalculation;
import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.entity.log.CollisionPoint;
import com.siemens.metal_forming.entity.log.LogCreator;
import com.siemens.metal_forming.enumerated.ToolStatusType;
import com.siemens.metal_forming.exception.exceptions.OpcuaConnectionException;
import com.siemens.metal_forming.exception.exceptions.PlcNotFoundException;
import com.siemens.metal_forming.exception.exceptions.PlcUniqueConstrainException;
import com.siemens.metal_forming.opcua.OpcuaClient;
import com.siemens.metal_forming.opcua.OpcuaConnector;
import com.siemens.metal_forming.repository.PlcRepository;
import com.siemens.metal_forming.service.CurveValidationService;
import com.siemens.metal_forming.service.LogService;
import com.siemens.metal_forming.service.PlcService;
import com.siemens.metal_forming.service.ReferenceCurveCalculationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service @Slf4j
public class PlcServiceImpl implements PlcService {
    private final PlcRepository plcRepository;
    private final OpcuaConnector opcuaConnector;
    private final CurveValidationService curveValidationService;
    private final ReferenceCurveCalculationService referenceCurveCalculationService;
    private final LogService logService;
    private final LogCreator logCreator;

    @Autowired
    public PlcServiceImpl(PlcRepository plcRepository,
                          OpcuaConnector opcuaConnector,
                          @Qualifier("CurveValidationServiceImpl") CurveValidationService curveValidationService,
                          ReferenceCurveCalculationService referenceCurveCalculationService,
                          LogService logService,
                          LogCreator logCreator) {
        this.plcRepository = plcRepository;
        this.opcuaConnector = opcuaConnector;
        this.curveValidationService = curveValidationService;
        this.referenceCurveCalculationService = referenceCurveCalculationService;
        this.logService = logService;
        this.logCreator = logCreator;
    }

    @Override
    public void checkIfExistsOrThrowException(Long id) {
        if (!plcRepository.existsById(id)){
            throw new PlcNotFoundException(id);
        }
    }

    @Override
    public List<Plc> findAll() {
        return plcRepository.findAll();
    }

    @Override
    public Optional<Plc> find(Long id) {
        return plcRepository.findById(id);
    }

    @Override
    public Optional<Plc> find(String ipAddress) {
        return plcRepository.findByIpAddress(ipAddress);
    }

    @Override
    public Plc createPlc(Plc plc) {
        validateUniquenessOfPlc(plc);
        return plcRepository.save(connectPlc(plc));
    }

    @Override @Transactional //ToDo should get all data from db
    public void connectAllPlcsInDatabase() {
        List<Plc> plcs = plcRepository.findAll().stream()
                .map(plc -> CompletableFuture.supplyAsync(() -> this.connectPlc(plc)))
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        log.info("Trying to connect to plcs with IP addresses: {} over OPC UA", plcs.stream().map(Plc::getIpAddress).collect(Collectors.joining(", ")));
        log.info("Plcs with IP addresses: {} were successfully connected.", plcs.stream().filter(Plc::isConnected).map(Plc::getIpAddress).collect(Collectors.joining(", ")));
    }

    @Override
    public void delete(Long id) {
        Optional<Plc> oldPlc = plcRepository.findById(id);
        if(oldPlc.isPresent()){
            opcuaConnector.disconnectPlc(oldPlc.get());
            plcRepository.deleteById(id);
        } else {
            throw new PlcNotFoundException(id);
        }
    }

    @Transactional
    @Override
    public void changeCurrentTool(String ipAddress, int toolNumber) {
        Plc plc = plcRepository.findByIpAddress(ipAddress).orElseThrow(() -> new PlcNotFoundException("Plc with IP address "+ipAddress+" was not found."));
        // Canceling calculation of reference curve on old tool
        referenceCurveCalculationService.removeCalculation(plc.getCurrentTool().getId());

        //Setting new current tool
        if(plc.hasToolByToolNumber(toolNumber)){
            plc.setCurrentTool(toolNumber);
            log.debug("Setting current tool: {}", plc.getCurrentTool());
            Tool currentTool = plc.getCurrentTool();
        } else {
            OpcuaClient client = opcuaConnector.getClient(plc);
            try {
                Tool autodetectedTool = Tool.builder()
                        .toolNumber(client.readToolNumber().get())
                        .nameFromPlc(client.readToolName().get())
                        .maxSpeedOperation(client.readToolMaxSpeedOperation().get())
                        .toolStatus(ToolStatusType.AUTODETECTED)
                        .automaticMonitoring(false)
                        .calculateReferenceCurve(false)
                        .build();
                log.debug("Setting autodetected tool as current tool: {}", autodetectedTool);
                plc.addTool(autodetectedTool);
                plc.setCurrentTool(toolNumber);
            } catch (InterruptedException|ExecutionException e) {
                log.error("Information about tool could not be correctly read.");
            }
        }
        plcRepository.save(plc);
    }

    @Override
    public void processNewCurve(String ipAddress, Curve measuredCurve) {
        Plc plc = plcRepository.findByIpAddress(ipAddress).orElseThrow(() -> new PlcNotFoundException("Plc with IP address "+ipAddress+" was not found."));
        Tool currentTool = plc.getCurrentTool();

        //Validation of reference curve
        if(currentTool.getAutomaticMonitoring()){
            Set<CollisionPoint> collisionPoints = curveValidationService.validate(currentTool.getTolerance(), currentTool.getReferenceCurve(),measuredCurve);

            if(!collisionPoints.isEmpty()){
                logService.save(logCreator.create(plc, measuredCurve, collisionPoints));

                switch (currentTool.getStopReaction()){
                    case IMMEDIATE:
                        opcuaConnector.getClient(plc).immediateStop();
                    case TOP_POSITION:
                        opcuaConnector.getClient(plc).topPositionStop();
                }
            }
        } else {
            log.debug("New curve wasn't validated because automatic monitoring for current tool with toolNumber {} is disabled",currentTool.getToolNumber());
        }

        //Calculation of reference curve
        if(currentTool.getCalculateReferenceCurve()){
            Optional<ReferenceCurveCalculation> calculation = referenceCurveCalculationService.getReferenceCurveCalculation(currentTool.getId());
            if(calculation.isEmpty()){
                referenceCurveCalculationService.addCalculation(currentTool.getId(),currentTool.getNumberOfReferenceCycles());
            }
            referenceCurveCalculationService.calculate(currentTool.getId(), measuredCurve);
        }
    }

    @Transactional //ToDo should it be transactional?
    @Override
    public Plc update(String ipAddress, Consumer<Plc> updatePlc) {
        Optional<Plc> plcInDb = plcRepository.findByIpAddress(ipAddress);
        if(plcInDb.isPresent()){
            return update(plcInDb.get(),updatePlc);
        } else {
            throw new PlcNotFoundException("Plc with given IP address "+ipAddress+" was not in database");
        }
    }


    @Override
    public Plc update(Long id, Consumer<Plc> updatePlc) {
        Optional<Plc> plcInDb = plcRepository.findByIdFetchAll(id); //ToDo should it fetch all or fetch when needed
        if(plcInDb.isPresent()){
            return update(plcInDb.get(),updatePlc);
        } else {
            throw new PlcNotFoundException("Plc with given id "+id+" was not in database");
        }
    }

    private Plc update(Plc plc, Consumer<Plc> updatePlc){
        final Plc oldPlc = plc.toBuilder().build();
        updatePlc.accept(plc);

        //checks if updated plc doesnt collide with plc in database
        validateUniquenessOfPlc(plc);

        //if plc has different IP address then it needs to be reconnected
        if(!plc.getIpAddress().equals(oldPlc.getIpAddress())){
            log.info("IP address of plc with id {} was changed to {}",plc.getId(), plc.getIpAddress());
            opcuaConnector.disconnectPlc(oldPlc);
            connectPlc(plc);
        }
        return plcRepository.save(plc);
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
        try {
            OpcuaClient client = opcuaConnector.connectPlc(plc);
            plc.markAsConnected();
            plc.getHardwareInformation().setSerialNumber(client.readSerialNumber().get());
            plc.getHardwareInformation().setFirmwareNumber(client.readFirmwareNumber().get());

            // set current tool
            Integer currentToolNumber = client.readToolNumber().get();
            if(plc.getCurrentTool() == null){
                Tool newTool = Tool.builder()
                        .toolNumber(currentToolNumber)
                        .nameFromPlc(client.readToolName().get())
                        .maxSpeedOperation(client.readToolMaxSpeedOperation().get())
                        .toolStatus(ToolStatusType.AUTODETECTED)
                        .automaticMonitoring(false)
                        .calculateReferenceCurve(false)
                        .build();
                plc.addTool(newTool);
            }
            plc.setCurrentTool(currentToolNumber);

            log.debug("All information about plc were successfully read");
        } catch (OpcuaConnectionException e){
            log.warn("Plc with IP address {} could not be connected",plc.getIpAddress());
            plc.markAsDisconnected();
        } catch (InterruptedException | ExecutionException e) {
            log.warn("Information about plc could not be read: {}",e.getMessage());
        }

        return plc;
    }


}
