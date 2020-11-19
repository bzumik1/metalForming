package com.siemens.metal_forming.dto;

import com.siemens.metal_forming.enumerated.ToolStatusType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("<= TOOL DTO SPECIFICATION =>")
public class ToolDtoSpec {
    @Nested @DisplayName("CREATE")
    class ToolDtoRequestCreate{

    }

    @Nested @DisplayName("REQUEST")
    class Request{
        @Nested @DisplayName("CREATE")
        class Create extends DtoSpec{
            public Create() {
                super(ToolDto.Request.Create.class);
            }

            @Test @DisplayName("is created with tool status MANUALLY_ADDED")
            void isCreatedWithToolStatusManuallyAdded(){
                ToolDto.Request.Create toolDto = ToolDto.Request.Create.builder().build();

                assertThat(toolDto.getToolStatus()).isEqualTo(ToolStatusType.MANUALLY_ADDED);
            }

            @Nested @DisplayName("VALIDATE TOOL DTO REQUEST CREATE")
            class ValidateToolDtoRequestCreate{
                Validator validator;
                @BeforeEach
                void initialize(){
                    validator = Validation.buildDefaultValidatorFactory().getValidator();
                }

                @Test @DisplayName("is invalid when includes both tolerances")
                void isInvalidWhenIncludesBothTolerances(){
                    ToolDto.Request.Create toolDto = ToolDto.Request.Create
                            .builder()
                            .absoluteTolerance(new AbsoluteToleranceDto(1,1))
                            .relativeTolerance(new RelativeToleranceDto(10,10))
                            .build();

                    Set<ConstraintViolation<ToolDto.Request.Create>> violations = validator.validate(toolDto);

                    assertThat(violations.stream()
                            .filter(toolConstraintViolation -> toolConstraintViolation.getMessage().equals("Maximally one tolerance can be set"))
                            .count()).isEqualTo(1);
                }

                @Test @DisplayName("is invalid when tolerance is invalid")
                void isInvalidWhenToleranceIsInvalid(){
                    ToolDto.Request.Create toolDto = ToolDto.Request.Create
                            .builder()
                            .relativeTolerance(new RelativeToleranceDto(101,10))
                            .build();

                    Set<ConstraintViolation<ToolDto.Request.Create>> violations = validator.validate(toolDto);

                    assertThat(violations.stream()
                            .filter(toolConstraintViolation -> toolConstraintViolation.getPropertyPath().toString().equals("relativeTolerance.torqueTolerance"))
                            .count()).isEqualTo(1);
                }

            }
        }

        @Nested @DisplayName("UPDATE")
        class Update extends DtoSpec{
            public Update() {
                super(ToolDto.Request.Update.class);
            }

            @Nested @DisplayName("VALIDATE TOOL DTO REQUEST UPDATE")
            class ValidateToolDtoRequestCreate{
                Validator validator;
                @BeforeEach
                void initialize(){
                    validator = Validation.buildDefaultValidatorFactory().getValidator();
                }
                @Test @DisplayName("is invalid when includes both tolerances")
                void isInvalidWhenIncludesBothTolerances(){
                    ToolDto.Request.Update toolDto = ToolDto.Request.Update
                            .builder()
                            .absoluteTolerance(new AbsoluteToleranceDto(1,1))
                            .relativeTolerance(new RelativeToleranceDto(10,10))
                            .build();

                    Set<ConstraintViolation<ToolDto.Request.Update>> violations = validator.validate(toolDto);

                    assertThat(violations.stream()
                            .filter(toolConstraintViolation -> toolConstraintViolation.getMessage().equals("Maximally one tolerance can be set"))
                            .count()).isEqualTo(1);
                }

                @Test @DisplayName("is invalid when tolerance is invalid")
                void isInvalidWhenToleranceIsInvalid(){
                    ToolDto.Request.Update toolDto = ToolDto.Request.Update
                            .builder()
                            .relativeTolerance(new RelativeToleranceDto(101,10))
                            .build();

                    Set<ConstraintViolation<ToolDto.Request.Update>> violations = validator.validate(toolDto);

                    assertThat(violations.stream()
                            .filter(toolConstraintViolation -> toolConstraintViolation.getPropertyPath().toString().equals("relativeTolerance.torqueTolerance"))
                            .count()).isEqualTo(1);
                }

            }
        }
    }

    @Nested @DisplayName("RESPONSE")
    class Response{
        @Nested @DisplayName("OVERVIEW")
        class Overview extends DtoSpec{
            public Overview() {
                super(ToolDto.Response.Overview.class);
            }
        }
    }
}
