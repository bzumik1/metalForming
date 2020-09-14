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

@DisplayName("<= TOOL SPECIFICATION =>")
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

        @Test @DisplayName("is invalid when tool Number is null")
        void invalidWhenToolIdIsNull(){
            Tool tool = new Tool();
            tool.setToolNumber(null);
            Set<ConstraintViolation<Tool>> violations = validator.validate(tool);
            assertThat(violations.stream()
                    .filter(toolConstraintViolation -> toolConstraintViolation.getPropertyPath().toString().equals("toolNumber"))
                    .count()).isEqualTo(1);
        }

    }

    @Nested @DisplayName("IS EQUAL")
    class CompareTo{
        @Test @DisplayName("returns true if compared tools have same toolNumber")
        void returnsTrueIfComparedToolsHaveSameToolNumber(){
            Tool tool1 = Tool.builder().id(1L).toolNumber(1).build();
            Tool tool2 = Tool.builder().id(2L).toolNumber(1).build();

            assertThat(tool1.equals(tool2)).isEqualTo(true);
        }

        @Test @DisplayName("returns false if compared tools have same toolNumber")
        void returnsFalseIfComparedToolsHaveSameToolNumber(){
            Tool tool1 = Tool.builder().id(1L).toolNumber(1).build();
            Tool tool2 = Tool.builder().id(2L).toolNumber(2).build();

            assertThat(tool1.equals(tool2)).isEqualTo(false);
        }
    }
}
