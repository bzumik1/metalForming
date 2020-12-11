package com.siemens.metal_forming.service.impl;

import com.siemens.metal_forming.connection.PlcData;
import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.entity.Tolerance;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.entity.log.CollisionPoint;
import com.siemens.metal_forming.entity.log.LogCreator;
import com.siemens.metal_forming.exception.exceptions.IncompatibleCurvesException;
import com.siemens.metal_forming.repository.LogRepository;
import com.siemens.metal_forming.repository.PlcRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service("CurveValidationServiceImpl")
@Slf4j
public class CurveValidationServiceImpl implements com.siemens.metal_forming.service.CurveValidationService{
    private final PlcRepository plcRepository;
    private final LogRepository logRepository;
    private final LogCreator logCreator;

    public CurveValidationServiceImpl(PlcRepository plcRepository, LogRepository logRepository, LogCreator logCreator) {
        this.plcRepository = plcRepository;
        this.logRepository = logRepository;
        this.logCreator = logCreator;
    }

    @Override
    public Set<CollisionPoint> validate(Tolerance tolerance, Curve referenceCurve, Curve measuredCurve) {
        log.info("starting curve validation");
        if(referenceCurve.getPoints().size() != measuredCurve.getPoints().size()) throw new IncompatibleCurvesException();

        Set<CollisionPoint> collisionPoints =  IntStream.range(0,referenceCurve.getPoints().size())
                .filter(i -> !tolerance.isInTolerance(referenceCurve.getPoints().get(i), measuredCurve.getPoints().get(i)))
                .mapToObj(i -> new CollisionPoint(measuredCurve.getPoints().get(i).getTorque(), measuredCurve.getPoints().get(i).getSpeed()))
                .collect(Collectors.toSet());

        if(collisionPoints.isEmpty())
            log.debug("No collision points were found during validation");
        else
            log.info("Curve is not valid, it contains {} collision points",collisionPoints.size());

        return collisionPoints;
    }

    @Transactional
    @Override
    public void onMeasuredCurveChange(PlcData plcData) {
        Optional<Plc> optionalPlcInDb = plcRepository.findByIpAddress(plcData.getIpAddress());
        if(optionalPlcInDb.isPresent()){
            Plc plcInDb = optionalPlcInDb.get();
            Tool currentTool = optionalPlcInDb.get().getCurrentTool();
            Curve measuredCurve = plcData.getMeasuredCurve();
            log.debug("Validating measured curve of PLC with id {} in database",plcInDb);

            if(currentTool.getAutomaticMonitoring()){
                Set<CollisionPoint> collisionPoints = validate(currentTool.getTolerance(), currentTool.getReferenceCurve(),measuredCurve);

                if(!collisionPoints.isEmpty()){
                    logRepository.save(logCreator.create(plcInDb, measuredCurve, collisionPoints));
                    //Sends signal back to PLC
                    switch (currentTool.getStopReaction()){
                        case IMMEDIATE:
                            plcData.immediateStop();
                        case TOP_POSITION:
                            plcData.topPositionStop();
                    }
                }
            } else {
                log.debug("New curve wasn't validated because automatic monitoring for current tool with toolNumber {} is disabled",currentTool.getToolNumber());
            }
        } else {
            log.warn("Measured curve of PLC with IP address {} should be validated, but this PLC wasn't found in database", plcData.getIpAddress());
        }
    }
}
