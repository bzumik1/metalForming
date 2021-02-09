package com.siemens.metal_forming.service;

import com.siemens.metal_forming.connection.PlcData;
import com.siemens.metal_forming.domain.Curve;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.repository.ToolRepository;
import com.siemens.metal_forming.service.impl.ReferenceCurveCalculationServiceImpl;
import com.siemens.metal_forming.testBuilders.TestCurveBuilder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@DisplayName("<= REFERENCE CURVE CALCULATION SERVICE SPECIFICATION =>")
public class ReferenceCurveCalculationServiceSpec {
    ReferenceCurveCalculationService referenceCurveCalculationService;
    @Mock
    ToolRepository toolRepository;

    @Mock
    PlcData plcData;

    @Captor
    ArgumentCaptor<Tool> toolCaptor;

    @BeforeEach
    void initialize(){
        referenceCurveCalculationService = new ReferenceCurveCalculationServiceImpl(toolRepository);
    }

    @Test @DisplayName("calculation is interrupted when tool is changed")
    void calculationIsInterruptedWhenToolIsChanged(){
        Tool toolInDb = Tool.builder()
                .id(1L)
                .toolNumber(1)
                .calculateReferenceCurve(true)
                .numberOfReferenceCycles(2)
                .build();

        when(plcData.getMeasuredCurve()).thenReturn(new TestCurveBuilder().randomPoints(100).build());
        when(plcData.getIpAddress()).thenReturn("192.168.0.1");
        when(plcData.getToolNumber()).thenReturn(1);
        when(toolRepository.findByPlcIpAddressAndToolNumber("192.168.0.1",1)).thenReturn(Optional.of(toolInDb));

        referenceCurveCalculationService.onMeasuredCurveChange(plcData);
        referenceCurveCalculationService.onToolNumberChange(plcData);
        referenceCurveCalculationService.onMeasuredCurveChange(plcData);

        verify(toolRepository,never().description("Calculation wasn't interrupted!")).save(any());
    }

    @Test @DisplayName("updates tool in database when calculation is finished")
    void updatesToolInDatabaseWhenCalculationIsFinished(){
        Curve measuredCurve = new TestCurveBuilder().randomPoints(100).build();
        Tool toolInDb = Tool.builder()
                .id(1L)
                .toolNumber(1)
                .calculateReferenceCurve(true)
                .numberOfReferenceCycles(1)
                .build();

        when(plcData.getMeasuredCurve()).thenReturn(measuredCurve);
        when(plcData.getIpAddress()).thenReturn("192.168.0.1");
        when(plcData.getToolNumber()).thenReturn(1);
        when(toolRepository.findByPlcIpAddressAndToolNumber("192.168.0.1",1)).thenReturn(Optional.of(toolInDb));

        referenceCurveCalculationService.onMeasuredCurveChange(plcData);

        verify(toolRepository, times(1).description("Database wasn't updated.")).save(toolCaptor.capture());

        Tool updatedTool = toolCaptor.getValue();
        assertThat(updatedTool.getReferenceCurve()).as("Reference curve should be updated").isEqualTo(measuredCurve);
        assertThat(updatedTool.getCalculateReferenceCurve()).as("CalculateReferenceCurve should be set to false").isFalse();
    }

    @Test @DisplayName("updates tool in database with new data when calculation is started next time")
    void updatesToolInDatabaseWithNewDataWhenCalculationIsStartedNextTIme(){
        Curve measuredCurve1 = new TestCurveBuilder().randomPoints(100).build();
        Curve measuredCurve2 = new TestCurveBuilder().randomPoints(101).build();
        Tool toolInDb = Tool.builder()
                .id(1L)
                .toolNumber(1)
                .calculateReferenceCurve(true)
                .numberOfReferenceCycles(1)
                .build();

        when(plcData.getMeasuredCurve()).thenReturn(measuredCurve1,measuredCurve2);
        when(plcData.getIpAddress()).thenReturn("192.168.0.1");
        when(plcData.getToolNumber()).thenReturn(1);
        when(toolRepository.findByPlcIpAddressAndToolNumber("192.168.0.1", 1)).thenReturn(Optional.of(toolInDb));

        referenceCurveCalculationService.onMeasuredCurveChange(plcData); // first calculation cycle
        toolInDb.setCalculateReferenceCurve(true);
        referenceCurveCalculationService.onMeasuredCurveChange(plcData); // second calculation cycle

        verify(toolRepository, times(2).description("One of two curves wasn't saved to database")).save(toolCaptor.capture());

        assertThat(toolCaptor.getValue().getReferenceCurve()).as("Reference curve should be calculated from new values").isEqualTo(measuredCurve2);
    }

    @Test @DisplayName("reference curve isn't calculated when CalculateReferenceCurve is false")
    void referenceCurveIsNotCalculatedWhenCalculateReferenceCurveIsFalse(){
        Curve measuredCurve1 = new TestCurveBuilder().randomPoints(100).build();
        Tool toolInDb = Tool.builder()
                .id(1L)
                .toolNumber(1)
                .calculateReferenceCurve(false)
                .numberOfReferenceCycles(1)
                .build();

        when(plcData.getMeasuredCurve()).thenReturn(measuredCurve1);
        when(plcData.getIpAddress()).thenReturn("192.168.0.1");
        when(plcData.getToolNumber()).thenReturn(1);
        when(toolRepository.findByPlcIpAddressAndToolNumber("192.168.0.1", 1)).thenReturn(Optional.of(toolInDb));

        referenceCurveCalculationService.onMeasuredCurveChange(plcData);

        verify(toolRepository, times(0).description("Calculation of reference curve was triggered")).save(any());
    }
}
