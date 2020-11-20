package com.siemens.metal_forming.entity;

import com.siemens.metal_forming.entity.AbsoluteTolerance;
import com.siemens.metal_forming.entity.abstractSpec.EntitySpec;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("<= ABSOLUTE TOLERANCE SPECIFICATION =>")
public class AbsoluteToleranceSpec extends EntitySpec {
    public AbsoluteToleranceSpec() {
        super(AbsoluteTolerance.class);
    }
    @Nested @DisplayName("VALIDATE METHOD")
    class ValidateMethod{

        @CsvSource({
                "100, 111",
                "90, 100",
                "95, 95",
                "100, 100",
                "90.19, 98.11",
                "109,103"
        })
        @ParameterizedTest(name = "[{index}] for tolerance(10, 11), referencePoint(100, 100) and pointToBeValidated({0}, {1}) result should be: true ")
        @DisplayName("returns true if point is in tolerance")
        void returnsTrueIfPointIsInTolerance(float torque, float speed){
            Tolerance tolerance = new AbsoluteTolerance(10,11);
            PointOfTorqueAndSpeed referencePoint = new CurvePoint(100f,100f);
            PointOfTorqueAndSpeed pointToBeValidated = new CurvePoint(torque,speed);

            assertThat(tolerance.validate(referencePoint,pointToBeValidated)).isTrue();
        }

        @CsvSource({
                "0, 0",
                "89, 100",
                "112, 112",
                "110, 110",
                "200, 100"
        })
        @ParameterizedTest(name = "[{index}] for tolerance(10, 11), referencePoint(100, 100) and pointToBeValidated({0}, {1}) result should be: false ")
        @DisplayName("returns false if point is out of tolerance")
        void returnsFalseIfPointIsOutOfTolerance(float torque, float speed){
            Tolerance tolerance = new AbsoluteTolerance(10,11);
            PointOfTorqueAndSpeed referencePoint = new CurvePoint(100f,100f);
            PointOfTorqueAndSpeed pointToBeValidated = new CurvePoint(torque,speed);

            assertThat(tolerance.validate(referencePoint,pointToBeValidated)).isFalse();
        }

        @CsvSource({
                "0, 0, 0, 0, true",
                "1, 0, 1, 0, true",
                "0, 1, 0, 1, true",
                "1, 1, 0, 1, false",
                "1, 1, 1, 0, false"
        })
        @ParameterizedTest(name = "[{index}] for tolerance({2}, {3}), referencePoint(0, 0) and pointToBeValidated({0}, {1}) result should be: {4} ")
        @DisplayName("if tolerance on one or both axes is 0 then return correct value")
        void ifToleranceIsZeroReturnsTrueIfPointsAreSame(float torque,
                                                         float speed,
                                                         float torqueTolerance,
                                                         float speedTolerance,
                                                         boolean result){
            Tolerance tolerance = new AbsoluteTolerance(torqueTolerance,speedTolerance);
            PointOfTorqueAndSpeed pointToBeValidated = new CurvePoint(torque,speed);
            PointOfTorqueAndSpeed referencePoint = new CurvePoint(0f,0f);

            assertThat(tolerance.validate(referencePoint,pointToBeValidated)).isEqualTo(result);
        }
    }

}
