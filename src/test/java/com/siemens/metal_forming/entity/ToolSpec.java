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

        @Test @DisplayName("is invalid when both tolerances are set")
        void isInvalidWhenBothTolerancesAreSet(){
            Tool tool = Tool.builder()
                    .absoluteTolerance(new AbsoluteTolerance(1,1))
                    .relativeTolerance(new RelativeTolerance(10,10))
                    .build();

            Set<ConstraintViolation<Tool>> violations = validator.validate(tool);

            assertThat(violations.stream()
                    .filter(toolConstraintViolation -> toolConstraintViolation.getMessage().equals("Maximally one tolerance can be set"))
                    .count()).isEqualTo(1);
        }

        @Test @DisplayName("is invalid when relative tolerance is invalid")
        void isInvalidWhenRelativeToleranceIsInvalid(){
            Tool tool = Tool.builder().relativeTolerance(new RelativeTolerance(101,1)).build();

            Set<ConstraintViolation<Tool>> violations = validator.validate(tool);

            assertThat(violations.stream()
                    .filter(toolConstraintViolation -> toolConstraintViolation.getPropertyPath().toString().equals("relativeTolerance.torqueTolerance"))
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

    @Nested @DisplayName("SET TOLERANCE")
    class SetTolerance{
        @Test @DisplayName("sets relativeTolerance to Null when absoluteTolerance is set")
        void setsRelativeToleranceToNullWhenAbsoluteToleranceIsSet(){
            Tool tool = Tool.builder().relativeTolerance(new RelativeTolerance(10,10)).build();

            tool.setAbsoluteTolerance(new AbsoluteTolerance(1,1));

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(tool.getAbsoluteTolerance())
                    .as("Absolute tolerance should be set")
                    .isEqualTo(new AbsoluteTolerance(1,1));
            softAssertions.assertThat(tool.getRelativeTolerance())
                    .as("Relative tolerance should be set to null")
                    .isNull();
            softAssertions.assertAll();
        }

        @Test @DisplayName("sets NOT relativeTolerance to Null when absoluteTolerance is set to null")
        void setsNotRelativeToleranceToNullWhenAbsoluteToleranceIsSetToNull(){
            Tool tool = Tool.builder().relativeTolerance(new RelativeTolerance(10,10)).build();

            tool.setAbsoluteTolerance(null);

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(tool.getAbsoluteTolerance())
                    .as("Absolute tolerance should be set to null")
                    .isNull();
            softAssertions.assertThat(tool.getRelativeTolerance())
                    .as("Relative tolerance should NOT be set to null")
                    .isEqualTo(new RelativeTolerance(10, 10));
            softAssertions.assertAll();
        }

        @Test @DisplayName("sets absoluteTolerance to Null when relativeTolerance is set")
        void setsAbsoluteToleranceToNullWhenRelativeToleranceIsSet(){
            Tool tool = Tool.builder().absoluteTolerance(new AbsoluteTolerance(1,1)).build();

            tool.setRelativeTolerance(new RelativeTolerance(10,10));

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(tool.getRelativeTolerance())
                    .as("Relative tolerance should be set")
                    .isEqualTo(new RelativeTolerance(10,10));
            softAssertions.assertThat(tool.getAbsoluteTolerance())
                    .as("Absolute tolerance should be set to null")
                    .isNull();
            softAssertions.assertAll();
        }

        @Test @DisplayName("sets NOT absoluteTolerance to Null when relativeTolerance is set to null")
        void setsNotAbsoluteToleranceToNullWhenRelativeToleranceIsSetToNull(){
            Tool tool = Tool.builder().absoluteTolerance(new AbsoluteTolerance(1,1)).build();

            tool.setRelativeTolerance(null);

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(tool.getRelativeTolerance())
                    .as("Relative tolerance should be set")
                    .isNull();
            softAssertions.assertThat(tool.getAbsoluteTolerance())
                    .as("Absolute tolerance should NOT be set to null")
                    .isEqualTo(new AbsoluteTolerance(1,1));
            softAssertions.assertAll();
        }
    }
}
