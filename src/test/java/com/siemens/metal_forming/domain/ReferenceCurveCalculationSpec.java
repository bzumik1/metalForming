package com.siemens.metal_forming.domain;

import com.siemens.metal_forming.exception.exceptions.IncompatibleCurvesException;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("<= REFERENCE CURVE CALCULATION SPECIFICATION =>")
public class ReferenceCurveCalculationSpec {

    @Test @DisplayName("throws exception when curve has not same length")
    void throwsExceptionWhenCurveHasNotSameLength(){
        ReferenceCurveCalculation referenceCurveCalculation = new ReferenceCurveCalculation(2);
        Curve curve1 = Curve.builder()
                .points(List.of(new PointOfTorqueAndSpeed(1f,1f),
                                new PointOfTorqueAndSpeed(2f,2f)))
                .build();

        Curve curve2 = Curve.builder()
                .points(List.of(new PointOfTorqueAndSpeed(1f,1f),
                                new PointOfTorqueAndSpeed(2f,2f),
                                new PointOfTorqueAndSpeed(3f,3f)))
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
                        new PointOfTorqueAndSpeed(20f,40f),
                        new PointOfTorqueAndSpeed(30f,60f),
                        new PointOfTorqueAndSpeed(40f,80f)))
                .build();

        Curve curve2 = Curve.builder()
                .points(List.of(
                        new PointOfTorqueAndSpeed(25f,42f),
                        new PointOfTorqueAndSpeed(38f,65f),
                        new PointOfTorqueAndSpeed(43f,80f)))
                .build();

        Curve curve3 = Curve.builder()
                .points(List.of(
                        new PointOfTorqueAndSpeed(15f,38f),
                        new PointOfTorqueAndSpeed(32f,70f),
                        new PointOfTorqueAndSpeed(43f,80f)))
                .build();

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(referenceCurveCalculation.calculate(curve1)).isEmpty();
        softAssertions.assertThat(referenceCurveCalculation.calculate(curve2)).isEmpty();
        softAssertions.assertThat(referenceCurveCalculation.calculate(curve3).get().getPoints())
                .containsExactly(
                        new PointOfTorqueAndSpeed(20f, 40f),
                        new PointOfTorqueAndSpeed(100f/3f,65f),
                        new PointOfTorqueAndSpeed(42f, 80f));
        softAssertions.assertAll();
    }

    @Test @DisplayName("updates calculation status")
    void updatesCalculationStatus(){
        ReferenceCurveCalculation referenceCurveCalculation = new ReferenceCurveCalculation(1);

        Curve curve1 = Curve.builder()
                .points(List.of(
                        new PointOfTorqueAndSpeed(20f,40f),
                        new PointOfTorqueAndSpeed(30f,60f),
                        new PointOfTorqueAndSpeed(40f,80f)))
                .build();

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(referenceCurveCalculation.getStatus().getCurrent()).as("Starts at 0").isEqualTo(0);
        softAssertions.assertThat(referenceCurveCalculation.getStatus().getTotal()).as("Total is set to numberOfCalculations").isEqualTo(1);
        referenceCurveCalculation.calculate(curve1);
        softAssertions.assertThat(referenceCurveCalculation.getStatus().getCurrent()).as("Increases current when curve is added").isEqualTo(1);
        softAssertions.assertAll();
    }
}
