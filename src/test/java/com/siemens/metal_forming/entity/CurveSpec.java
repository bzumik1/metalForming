package com.siemens.metal_forming.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.validation.*;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("<= Curve Specification =>")
public class CurveSpec {
    private Curve curve;
    private Validator validator;

    @BeforeEach
    void initialize(){
        curve = new Curve();
    }

    @Test @DisplayName("is created with empty list of values")
    void isCreatedWithEmptyListOfValues(){
        assertThat(curve.getPoints()).isNotNull();
    }

    @Nested @DisplayName("validation")
    class validation{
        @BeforeEach
        void initializeValidator(){
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            validator = factory.getValidator();
        }

        @Test @DisplayName("is invalid when curve points are null")
        void isInvalidWhenCurvePointsAreNull(){
            curve.setPoints(null);
            Set<ConstraintViolation<Curve>> violations = validator.validate(curve);
            assertThat(violations.stream()
                    .filter(curveConstraintViolation -> curveConstraintViolation.getPropertyPath().toString().equals("points"))
                    .findFirst()).isPresent();
        }
    }


}
