package com.siemens.metal_forming.entity;

import com.siemens.metal_forming.entity.abstractSpec.EntitySpec;
import com.siemens.metal_forming.enumerated.StopReactionType;
import com.siemens.metal_forming.enumerated.ToolStatusType;
import com.siemens.metal_forming.exception.exceptions.ToolNumberUpdateException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
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
import static org.junit.jupiter.api.Assertions.assertThrows;


@FieldDefaults(level = AccessLevel.PRIVATE)
@DisplayName("<= TOOL SPECIFICATION =>")
class ToolSpec extends EntitySpec {
    Validator validator;

    public ToolSpec() {
        super(Tool.class);
    }

    @Nested @DisplayName("AFTER CREATION")
    class AfterCreation{
        @Test @DisplayName("tool is created with StopReaction DO_NOTHING (Constructor)")
        void toolIsCreatedWithDefaultStopReactionNew(){
            Tool testTool = new Tool();

            assertThat(testTool.getStopReaction()).isEqualTo(StopReactionType.DO_NOTHING);
        }

        @Test @DisplayName("tool is created with StopReaction DO_NOTHING (Builder)")
        void toolIsCreatedWithDefaultStopReactionBuilder(){
            Tool testTool = Tool.builder().build();

            assertThat(testTool.getStopReaction()).isEqualTo(StopReactionType.DO_NOTHING);
        }

        @Test @DisplayName("tool is created with automaticMonitoring set to false (Constructor)")
        void toolIsCreatedWithAutomaticMonitoringSetToFalseConstructor(){
            Tool testTool = new Tool();

            assertThat(testTool.getAutomaticMonitoring()).isFalse();
        }

        @Test @DisplayName("tool is created with automaticMonitoring set to false (Builder)")
        void toolIsCreatedWithAutomaticMonitoringSetToFalseBuilder(){
            Tool testTool = Tool.builder().build();

            assertThat(testTool.getAutomaticMonitoring()).isFalse();
        }

        @Test @DisplayName("tool is created with calculateReferenceCurve set to false (Constructor)")
        void toolIsCreatedWithCalculateReferenceCycleSetToFalseConstructor(){
            Tool testTool = new Tool();

            assertThat(testTool.getCalculateReferenceCurve()).isFalse();
        }

        @Test @DisplayName("tool is created with calculateReferenceCurve set to false (Builder)")
        void toolIsCreatedWithCalculateReferenceCycleSetToFalseBuilder(){
            Tool testTool = Tool.builder().build();

            assertThat(testTool.getCalculateReferenceCurve()).isFalse();
        }
    }

    @Nested @DisplayName("VALIDATION")
    class validation{

        @BeforeEach
        void initialize(){
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

    @Nested @DisplayName("SET TOOL NUMBER")
    class SetToolNumber{
        @Test @DisplayName("throws exception when toolNumber is changed for autodetected tool")
        void throwsExceptionWhenToolNumberIsUpdatedForAutodetectedTool(){
            Tool testTool = Tool.builder().toolNumber(1).toolStatus(ToolStatusType.AUTODETECTED).build();

            assertThrows(ToolNumberUpdateException.class, () -> testTool.setToolNumber(2));
        }

        @Test @DisplayName("toolNumber can be set for first time for autodetected tool")
        void toolNumberCanBeSetForFirstTImeForAutodetectedTool(){
            Tool testTool = Tool.builder().toolStatus(ToolStatusType.AUTODETECTED).build();

            testTool.setToolNumber(1);

            assertThat(testTool.getToolNumber()).isEqualTo(1);
        }

        @Test @DisplayName("toolNumber of autodetected tool can be set to same value")
        void toolNumberOfAutodetectedToolCanBeSetToSameVale(){
            Tool testTool = Tool.builder().toolStatus(ToolStatusType.AUTODETECTED).toolNumber(1).build();

            testTool.setToolNumber(1);

            assertThat(testTool.getToolNumber()).isEqualTo(1);
        }

        @Test @DisplayName("toolNumber can be updated for other tool statuses")
        void toolNumberCanBeUpdatedForOtherToolStatuses(){
            Tool testTool = Tool.builder().toolStatus(ToolStatusType.MANUALLY_ADDED).toolNumber(1).build();

            testTool.setToolNumber(2);

            assertThat(testTool.getToolNumber()).isEqualTo(2);
        }
    }
}
