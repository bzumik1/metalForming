package com.siemens.metal_forming.domain;

import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.entity.CurvePoint;
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

@DisplayName("<= REFERENCE CURVE CALCULATION SPECIFICATION =>")
public class ReferenceCurveCalculationSpec {

    @Test @DisplayName("returns correct reference curve when number of reference cycles was reached")
    void returnsCorrectReferenceCurveWhenNumberOfReferenceCyclesWasReached(){
        int cycle = 0;
        //Create referenceCurveCalculation with 5 reference cycles
        ReferenceCurveCalculation referenceCurveCalculation = new ReferenceCurveCalculation(5);
        //Create curves from which the reference curve will be calculated
        List<Curve> curves = Stream.iterate(0,n ->n+1).limit(6)
                .map(n -> Curve.builder()
                        .points(Stream.generate(() -> new CurvePoint((float)n, (float)n)).limit(360).collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
        float average = (0f + 1f + 2f + 3f +4f)/5f;

        Optional<Curve> referenceCurve = Optional.empty();
        while (cycle<6 && referenceCurve.isEmpty()){
            referenceCurve = referenceCurveCalculation.calculate(curves.get(cycle));
            cycle++;
        }

        assertThat(referenceCurve).as("Reference curve should be calculated").isNotEmpty();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(cycle).as("Number of reference cycles should be 5").isEqualTo(5);
        softAssertions.assertThat(referenceCurve.get()).isEqualTo(Curve.builder()
                .points(Stream.generate(() -> new CurvePoint(average, average)).limit(360).collect(Collectors.toList()))
                .build());
        softAssertions.assertAll();
    }
}
