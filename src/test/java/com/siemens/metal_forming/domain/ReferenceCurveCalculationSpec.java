package com.siemens.metal_forming.domain;

import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.entity.CurvePoint;
import com.siemens.metal_forming.exception.exceptions.IncompatibleCurvesException;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("<= REFERENCE CURVE CALCULATION SPECIFICATION =>")
public class ReferenceCurveCalculationSpec {

    @Test @DisplayName("throws exception when curve has not same length")
    void throwsExceptionWhenCurveHasNotSameLength(){
        ReferenceCurveCalculation referenceCurveCalculation = new ReferenceCurveCalculation(2);
        Curve curve1 = Curve.builder()
                .points(List.of(new CurvePoint(1f,1f),
                                new CurvePoint(2f,2f)))
                .build();

        Curve curve2 = Curve.builder()
                .points(List.of(new CurvePoint(1f,1f),
                                new CurvePoint(2f,2f),
                                new CurvePoint(3f,3f)))
                .build();

        referenceCurveCalculation.calculate(curve1);

        assertThrows(IncompatibleCurvesException.class, () -> referenceCurveCalculation.calculate(curve2));
    }

    @Test @DisplayName("returns correct reference curve when number of reference cycles was reached")
    void returnsCorrectReferenceCurveWhenNumberOfReferenceCyclesWasReached(){
        int cycle = 0;
        ReferenceCurveCalculation referenceCurveCalculation = new ReferenceCurveCalculation(3);

        Curve curve1 = Curve.builder()
                .points(List.of(
                        new CurvePoint(20f,40f),
                        new CurvePoint(30f,60f),
                        new CurvePoint(40f,80f)))
                .build();

        Curve curve2 = Curve.builder()
                .points(List.of(
                        new CurvePoint(25f,42f),
                        new CurvePoint(38f,65f),
                        new CurvePoint(43f,80f)))
                .build();

        Curve curve3 = Curve.builder()
                .points(List.of(
                        new CurvePoint(15f,38f),
                        new CurvePoint(32f,70f),
                        new CurvePoint(43f,80f)))
                .build();

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(referenceCurveCalculation.calculate(curve1)).isEmpty();
        softAssertions.assertThat(referenceCurveCalculation.calculate(curve2)).isEmpty();
        softAssertions.assertThat(referenceCurveCalculation.calculate(curve3).get().getPoints())
                .containsExactly(
                        new CurvePoint(20f, 40f),
                        new CurvePoint(100f/3f,65f),
                        new CurvePoint(42f, 80f));
        softAssertions.assertAll();
    }
}
