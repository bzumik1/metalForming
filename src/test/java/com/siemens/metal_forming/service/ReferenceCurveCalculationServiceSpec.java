package com.siemens.metal_forming.service;

import com.siemens.metal_forming.domain.ReferenceCurveCalculation;
import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.entity.CurvePoint;
import com.siemens.metal_forming.exception.exceptions.CalculationNotFoundException;
import com.siemens.metal_forming.service.impl.ReferenceCurveCalculationServiceImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@DisplayName("<= REFERENCE CURVE CALCULATION SERVICE SPECIFICATION =>")
public class ReferenceCurveCalculationServiceSpec {
    ReferenceCurveCalculationService referenceCurveCalculationService;
    @Mock
    ToolService toolService;

    @BeforeEach
    void initialize(){
        referenceCurveCalculationService = new ReferenceCurveCalculationServiceImpl(toolService);
    }

    @Test @DisplayName("adds calculation to temporary memory")
    void addsCalculationToTemporaryMemory(){
        referenceCurveCalculationService.addCalculation(1L,10);

        Optional<ReferenceCurveCalculation> referenceCurveCalculation = referenceCurveCalculationService.getReferenceCurveCalculation(1L);

        assertThat(referenceCurveCalculation)
                .as("Calculation should be created")
                .isNotEmpty();
        assertThat(referenceCurveCalculation.get().NUMBER_OF_CYCLES)
                .as("Calculation should be created with corect number of cycles")
                .isEqualTo(10);
    }

    @Test @DisplayName("removes calculation from temorary memory")
    void removesCalculationFromTemporaryMemory(){
        referenceCurveCalculationService.addCalculation(1L, 10);

        referenceCurveCalculationService.removeCalculation(1L);

        assertThat(referenceCurveCalculationService.getReferenceCurveCalculation(1L)).isEmpty();
    }

    @Test @DisplayName("returns calculated reference curve if it is calculated")
    void returnsCalculatedReferenceCurveIfItIsCalculated(){
        referenceCurveCalculationService.addCalculation(1L,1);
        Curve newCurveForCalculation = Curve.builder()
                .points(Stream.generate(() -> new CurvePoint(1f, 1f)).limit(360).collect(Collectors.toList()))
                .build();

        Optional<Curve> referenceCurve = referenceCurveCalculationService.calculate(1L, newCurveForCalculation);

        assertThat(referenceCurve).as("Reference curve should be calculated").isNotEmpty();
        assertThat(referenceCurve.get()).isEqualTo(newCurveForCalculation); //is same because is calculated only from one curve
    }

    @Test @DisplayName("throws CalculationNotFoundException when calculation for given tool was not found")
    void throwsExceptionWhenCalculationForGivenToolWasNotFound(){
        Curve newCurveForCalculation = Curve.builder()
                .points(Stream.generate(() -> new CurvePoint(1f, 1f)).limit(360).collect(Collectors.toList()))
                .build();

        assertThrows(CalculationNotFoundException.class, () -> referenceCurveCalculationService.calculate(1L,newCurveForCalculation));
    }

    @Test @DisplayName("updates tool when reference curve is calculated (sets referenceCurve value and calculateReferenceCurve to false)")
    void updatesToolWhenCalculationIsDone(){
        referenceCurveCalculationService.addCalculation(1L,1);
        Curve newCurveForCalculation = Curve.builder()
                .points(Stream.generate(() -> new CurvePoint(1f, 1f)).limit(360).collect(Collectors.toList()))
                .build();

        referenceCurveCalculationService.calculate(1L, newCurveForCalculation);

        verify(toolService, times(1)).updateReferenceCurve(1L,newCurveForCalculation); //newCurveForCalculation is same as reference curve because it is calculated from one curve
    }
}
