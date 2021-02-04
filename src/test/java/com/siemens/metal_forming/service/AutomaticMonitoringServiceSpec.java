package com.siemens.metal_forming.service;

import com.siemens.metal_forming.connection.PlcData;
import com.siemens.metal_forming.entity.*;
import com.siemens.metal_forming.entity.log.CollisionPoint;
import com.siemens.metal_forming.entity.log.LogCreator;
import com.siemens.metal_forming.enumerated.StopReactionType;
import com.siemens.metal_forming.repository.LogRepository;
import com.siemens.metal_forming.repository.PlcRepository;
import com.siemens.metal_forming.service.impl.AutomaticMonitoringServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("<= CURVE VALIDATION SERVICE SPECIFICATION =>")
public class AutomaticMonitoringServiceSpec {
    AutomaticMonitoringService automaticMonitoringService;
    @Mock PlcRepository plcRepository;
    @Mock LogRepository logRepository;
    @Mock LogCreator logCreator;
    @Mock CurveValidationService curveValidationService;
    @Mock PlcData plcData;


    @BeforeEach
    void initialize(){
        automaticMonitoringService = new AutomaticMonitoringServiceImpl(plcRepository, logRepository, logCreator, curveValidationService);
    }


    @Nested @DisplayName("PROCESS NEW CURVE")
    class ProcessNewCurve{

        @Test @DisplayName("when automatic monitoring is false does nothing")
        void whenAutomaticMonitoringIsFalseDoesNothing(){
            Plc plcInDatabase = Plc.builder()
                    .currentTool(Tool.builder()
                            .referenceCurve( Curve.builder()
                                    .build())
                            .automaticMonitoring(false)
                            .build())
                    .build();

            when(plcData.getIpAddress()).thenReturn("192.168.0.1");
            when(plcRepository.findByIpAddress("192.168.0.1")).thenReturn(Optional.of(plcInDatabase));

            automaticMonitoringService.onMeasuredCurveChange(plcData);

            verify(curveValidationService, never()).validate(any(), any(), any());
        }

        @Test @DisplayName("creates log and saves it to database and sends feedback to plc when curve wasn't valid")
        void calculatesReferenceCurveWhenNeeded(){
            Plc testPlc = Plc.builder()
                    .currentTool(Tool.builder()
                            .automaticMonitoring(true)
                            .id(1L)
                            .calculateReferenceCurve(true)
                            .numberOfReferenceCycles(1)
                            .stopReaction(StopReactionType.IMMEDIATE)
                            .build())
                    .build();

            when(plcData.getIpAddress()).thenReturn("192.168.0.1");
            when(plcRepository.findByIpAddress("192.168.0.1")).thenReturn(Optional.of(testPlc));
            when(curveValidationService.validate(any(), any(), any())).thenReturn(Set.of(new CollisionPoint(1f,1f)));

            automaticMonitoringService.onMeasuredCurveChange(plcData);

            verify(logRepository, times(1).description("Log wasn't created")).save(any());
            verify(plcData, times(1).description("Feedback to PLC wasn't send")).immediateStop();
        }
    }

}
