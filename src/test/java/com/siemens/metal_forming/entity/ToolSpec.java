package com.siemens.metal_forming.entity;

import com.siemens.metal_forming.dto.AbsoluteToleranceDto;
import com.siemens.metal_forming.entity.abstractSpec.EntitySpec;
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
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


@FieldDefaults(level = AccessLevel.PRIVATE)
@DisplayName("<= TOOL SPECIFICATION =>")
class ToolSpec extends EntitySpec {
    Validator validator;
    Tool tool;

    public ToolSpec() {
        super(Tool.class);
    }

    @Nested @DisplayName("validation")
    class validation{

        @BeforeEach
        void initialize(){
            tool = new Tool();

            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @Test @DisplayName("is invalid when tool Number is null")
        void invalidWhenToolIdIsNull(){
            Tool tool = new Tool();
            tool.setToolNumber(null);
            Set<ConstraintViolation<Tool>> violations = validator.validate(tool);
            assertThat(violations.stream()
                    .filter(toolConstraintViolation -> toolConstraintViolation.getPropertyPath().toString().equals("toolNumber"))
                    .count()).isEqualTo(1);
        }



        @Test @DisplayName("is invalid when tolerance is invalid")
        void isInvalidWhenToleranceIsInvalid(){
            Tool tool = Tool.builder().tolerance(new RelativeTolerance(101,1)).build();

            Set<ConstraintViolation<Tool>> violations = validator.validate(tool);

            assertThat(violations.stream()
                    .filter(toolConstraintViolation -> toolConstraintViolation.getPropertyPath().toString().equals("tolerance.torqueTolerance"))
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
