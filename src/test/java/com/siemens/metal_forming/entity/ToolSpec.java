package com.siemens.metal_forming.entity;

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

@DisplayName("<= Plc Repository Specification =>")
public class ToolSpec {
    Validator validator;
    Tool tool;

    @BeforeEach
    void initialize(){
        tool = new Tool();

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Nested @DisplayName("validation")
    class validation{

        @Test @DisplayName("is invalid when toolId is null")
        void invalidWhenToolIdIsNull(){
            Tool tool = new Tool();
            tool.setToolNumber(null);
            Set<ConstraintViolation<Tool>> violations = validator.validate(tool);
            assertThat(violations.stream()
                    .filter(toolConstraintViolation -> toolConstraintViolation.getPropertyPath().toString().equals("toolId"))
                    .count()).isEqualTo(1);
        }

    }

}
