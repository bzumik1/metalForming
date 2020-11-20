package com.siemens.metal_forming.entity;

import com.siemens.metal_forming.entity.abstractSpec.EntitySpec;
import org.assertj.core.api.SoftAssertions;
import org.hibernate.annotations.Cascade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("<= RELATIVE TOLERANCE SPECIFICATION =>")
public class RelativeToleranceSpec extends EntitySpec {
    public RelativeToleranceSpec() {
        super(RelativeTolerance.class);
    }

    @Nested @DisplayName("GET ABSOLUTE TOLERANCE")
    class GetAbsoluteTolerance{
        @Test @DisplayName("returns correct absolute tolernace")
        void returnsCorrectAbsoluteTolerance(){
            RelativeTolerance relativeTolerance = new RelativeTolerance(50, 50);

            AbsoluteTolerance absoluteTolerance = relativeTolerance.getAbsoluteTolerance(new CurvePoint(100F,200F));

            assertThat(absoluteTolerance).isEqualTo(new AbsoluteTolerance(50F,100F));
        }

    }


    @Nested @DisplayName("TOLERANCE VALIDATION")
    class ToleranceValidation{
        private Validator validator;
        @BeforeEach
        void initialize(){
            validator = Validation.buildDefaultValidatorFactory().getValidator();
        }
        @Test @DisplayName("is not valid when torqueTolerance or speedTolerance is smaller than 0 or greather than 100")
        void isInvalidWhenToleranceIsOutOfRange(){
            RelativeTolerance relativeTolerance = new RelativeTolerance(-10, 120);

            Set<ConstraintViolation<RelativeTolerance>> violations = validator.validate(relativeTolerance);

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(violations
                    .stream()
                    .filter(plcConstraintViolation -> plcConstraintViolation.getPropertyPath().toString().equals("torqueTolerance"))
                    .findFirst()).isNotEmpty();
            softAssertions.assertThat(violations
                    .stream()
                    .filter(plcConstraintViolation -> plcConstraintViolation.getPropertyPath().toString().equals("speedTolerance"))
                    .findFirst()).isNotEmpty();
            softAssertions.assertAll();
        }
    }

    @Nested @DisplayName("VALIDATE METHOD")
    class ValidateMethod{

        @CsvSource({
                "100, 105",
                "90, 100",
                "98, 98",
                "100, 100",
                "105.81, 104.05",
                "104,101"
        })
        @ParameterizedTest(name = "[{index}] for tolerance(10, 5), referencePoint(100, 100) and pointToBeValidated({0}, {1}) result should be: true ")
        @DisplayName("returns true if point is in tolerance")
        void returnsTrueIfPointIsInTolerance(float torque, float speed){
            Tolerance tolerance = new RelativeTolerance(10,5);
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
        @ParameterizedTest(name = "[{index}] for tolerance(10, 5), referencePoint(100, 100) and pointToBeValidated({0}, {1}) result should be: false ")
        @DisplayName("returns false if point is out of tolerance")
        void returnsFalseIfPointIsOutOfTolerance(float torque, float speed){
            Tolerance tolerance = new RelativeTolerance(10,5);
            PointOfTorqueAndSpeed referencePoint = new CurvePoint(100f,100f);
            PointOfTorqueAndSpeed pointToBeValidated = new CurvePoint(torque,speed);

            assertThat(tolerance.validate(referencePoint,pointToBeValidated)).isFalse();
        }

        @CsvSource({
                "100, 100, 0, 0, true",
                "101, 100, 1, 0, true",
                "100, 101, 0, 1, true",
                "101, 101, 0, 1, false",
                "101, 101, 1, 0, false"
        })
        @ParameterizedTest(name = "[{index}] for tolerance({2}, {3}), referencePoint(100, 100) and pointToBeValidated({0}, {1}) result should be: {4} ")
        @DisplayName("if tolerance on one or both axes is 0 then return correct value")
        void ifToleranceIsZeroReturnsTrueIfPointsAreSame(float torque,
                                                         float speed,
                                                         float torqueTolerance,
                                                         float speedTolerance,
                                                         boolean result){
            Tolerance tolerance = new RelativeTolerance(torqueTolerance, speedTolerance);
            PointOfTorqueAndSpeed pointToBeValidated = new CurvePoint(torque,speed);
            PointOfTorqueAndSpeed referencePoint = new CurvePoint(100f,100f);

            assertThat(tolerance.validate(referencePoint,pointToBeValidated)).isEqualTo(result);
        }
    }
}
