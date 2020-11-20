package com.siemens.metal_forming.dto;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@DisplayName("<= ABSOLUTE TOLERANCE DTO SPECIFICATION =>")
public class AbsoluteToleranceDtoSpec extends DtoSpec {
    public AbsoluteToleranceDtoSpec() {
        super(AbsoluteToleranceDto.class);
    }

    @Nested @DisplayName("TOLERANCE VALIDATION")
    class ToleranceValidation{
        private Validator validator;
        @BeforeEach
        void initialize(){
            validator = Validation.buildDefaultValidatorFactory().getValidator();
        }
        @Test
        @DisplayName("is not valid when torqueTolerance or speedTolerance is smaller than 0")
        void isInvalidWhenToleranceIsOutOfRange(){
            ToleranceDto tolerance = new AbsoluteToleranceDto(-10, -4);

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
