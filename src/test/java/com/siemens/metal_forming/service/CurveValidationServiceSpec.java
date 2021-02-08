package com.siemens.metal_forming.service;


import com.siemens.metal_forming.domain.Curve;
import com.siemens.metal_forming.domain.PointOfTorqueAndSpeed;
import com.siemens.metal_forming.entity.*;
import com.siemens.metal_forming.exception.exceptions.IncompatibleCurvesException;
import com.siemens.metal_forming.service.impl.CurveValidationServiceImpl;
import com.siemens.metal_forming.testBuilders.TestCurveBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DisplayName("<= CURVE VALIDATOR SPECIFICATION =>")
public class CurveValidationServiceSpec {
    private CurveValidationService curveValidationService;

    @BeforeEach
    void initialize(){
        curveValidationService = new CurveValidationServiceImpl();
    }


    @Test @DisplayName("throws IncompatibleCurvesException when curves doesn't have same length")
    void throwsIncompatibleCurvesExceptionWhenCurvesDoesNotHaveSameLength(){
        Curve referenceCurve = new TestCurveBuilder().randomPoints(9).build();
        Curve measuredCurve = new TestCurveBuilder().randomPoints(10).build();
        Tolerance tolerance = new AbsoluteTolerance(100,100);

        assertThrows(IncompatibleCurvesException.class, () -> curveValidationService.validate(tolerance,referenceCurve,measuredCurve));
    }

    @Test @DisplayName("returns set of collision points")
    void returnsSetOfPointOfTorqueAndSpeeds(){
        Curve referenceCurve = new TestCurveBuilder()
                .addPoint(100,100)
                .addPoint(100,100)
                .addPoint(100,100)
                .build();
        Curve measuredCurve = new TestCurveBuilder()
                .addPoint(101,102)
                .addPoint(102,111)
                .addPoint(89,103)
                .build();
        Tolerance tolerance = new AbsoluteTolerance(10,10);

        assertThat(curveValidationService.validate(tolerance,referenceCurve,measuredCurve))
                .containsExactly(new PointOfTorqueAndSpeed(102f,111f), new PointOfTorqueAndSpeed(89f,103f));
    }

}