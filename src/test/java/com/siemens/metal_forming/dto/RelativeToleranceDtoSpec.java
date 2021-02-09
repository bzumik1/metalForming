package com.siemens.metal_forming.dto;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@DisplayName("<= RELATIVE TOLERANCE DTO SPECIFICATION =>")
public class RelativeToleranceDtoSpec extends DtoSpec{
    public RelativeToleranceDtoSpec() {
        super(RelativeToleranceDto.class);
    }

    @Nested
    @DisplayName("TOLERANCE VALIDATION")
    class ToleranceValidation{
        private Validator validator;
        @BeforeEach
        void initialize(){
            validator = Validation.buildDefaultValidatorFactory().getValidator();
        }
        @Test @DisplayName("is not valid when torqueTolerance or speedTolerance is smaller than 0 or greater than 100")
        void isInvalidWhenToleranceIsOutOfRange(){
            ToleranceDto tolerance = new RelativeToleranceDto(-10, 120);

            Set<ConstraintViolation<ToleranceDto>> violations = validator.validate(tolerance);

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
