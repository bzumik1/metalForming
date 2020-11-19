package com.siemens.metal_forming.entity;

import com.siemens.metal_forming.entity.abstractSpec.EntitySpec;
import org.assertj.core.api.SoftAssertions;
import org.hibernate.annotations.Cascade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
}
