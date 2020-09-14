package com.siemens.metal_forming.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.validation.*;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("<= Curve Specification =>")
class CurveSpec  extends EntitySpec{
    Curve curve;
    Validator validator;

    @Override
    public Class getTestedClass() {
        return Curve.class;
    }

    @BeforeEach
    void initialize(){
        curve = new Curve();
    }

    @Test @DisplayName("is created with empty list of values")
    void isCreatedWithEmptyListOfValues(){
        assertThat(curve.getPoints()).isNotNull();
    }


    @Nested @DisplayName("VALIDATION")
    class validation{
        @BeforeEach
        void initializeValidator(){
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            validator = factory.getValidator();
        }

        @Test @DisplayName("is invalid when measuredCurve points are null")
        void isInvalidWhenCurvePointsAreNull(){
            curve.setPoints(null);
            Set<ConstraintViolation<Curve>> violations = validator.validate(curve);
            assertThat(violations.stream()
                    .filter(curveConstraintViolation -> curveConstraintViolation.getPropertyPath().toString().equals("points"))
                    .findFirst()).isPresent();
        }
    }


}
