package com.siemens.metal_forming.entity.log;

import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.entity.CurvePoint;
import com.siemens.metal_forming.entity.abstractSpec.ImmutableEntitySpec;
import com.siemens.metal_forming.enumerated.StopReactionType;
import com.siemens.metal_forming.testBuilders.TestLogBuilder;
import com.siemens.metal_forming.testBuilders.TestToolBuilder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
@FieldDefaults(level = AccessLevel.PRIVATE)
@DisplayName("<= LOG SPECIFICATION =>")
class LogSpec extends ImmutableEntitySpec {
    TestLogBuilder testLogBuilder;

    public LogSpec() {
        super(Log.class);
    }

    @BeforeEach
    void initialize(){
        testLogBuilder = new TestLogBuilder();
    }

    @Test @DisplayName("is created with current timestamp")
    void isCreatedWithCurrentTimestamp(){
        Log testLog = Log.builder().build();
        long acceptedTimeDifferenceInMillis = 1000;

        long connectionMillis = testLog.getCreatedOn().getTime();
        long currentMillis = System.currentTimeMillis();

        assertThat(Math.abs((connectionMillis-currentMillis))).isLessThan(acceptedTimeDifferenceInMillis);
    }

    @Nested @DisplayName("VALIDATION")
    class Validation{
        Validator validator;

        @BeforeEach
        void initializeForValidation(){
            validator = javax.validation.Validation.buildDefaultValidatorFactory().getValidator();
        }

        @Test @DisplayName("is invalid when measured curve is null")
        void isInvalidWhenMeasuredCurveIsNull(){
            Log invalidLog = testLogBuilder.measuredCurve(null).build();
            Set<ConstraintViolation<Log>> violations = validator.validate(invalidLog);

            assertThat(violations
                    .stream()
                    .filter(violation -> violation.getPropertyPath().toString().equals("measuredCurve"))
                    .findFirst()).isNotEmpty();
        }

        @Test @DisplayName("is invalid when motor curve is null")
        void isInvalidWhenMotorCurveIsNull(){
            Log invalidLog = testLogBuilder.motorCurve(null).build();
            Set<ConstraintViolation<Log>> violations = validator.validate(invalidLog);

            assertThat(violations
                    .stream()
                    .filter(violation -> violation.getPropertyPath().toString().equals("motorCurve"))
                    .findFirst()).isNotEmpty();
        }

        @Test @DisplayName("is invalid when reference curve is null")
        void isInvalidWhenReferenceCurveIsNull(){
            Log invalidLog = testLogBuilder.referenceCurve(null).build();
            Set<ConstraintViolation<Log>> violations = validator.validate(invalidLog);

            assertThat(violations
                    .stream()
                    .filter(violation -> violation.getPropertyPath().toString().equals("referenceCurve"))
                    .findFirst()).isNotEmpty();
        }

        @Test @DisplayName("is invalid when plc information is null")
        void isInvalidWhenPlcInformationIsNull(){
            Log invalidLog = testLogBuilder.plcInformation(null).build();
            Set<ConstraintViolation<Log>> violations = validator.validate(invalidLog);

            assertThat(violations
                    .stream()
                    .filter(violation -> violation.getPropertyPath().toString().equals("plcInformation"))
                    .findFirst()).isNotEmpty();
        }

        @Test @DisplayName("is invalid when tool information is null")
        void isInvalidWhenToolInformationIsNull(){
            Log invalidLog = testLogBuilder.toolInformation(null).build();
            Set<ConstraintViolation<Log>> violations = validator.validate(invalidLog);

            assertThat(violations
                    .stream()
                    .filter(violation -> violation.getPropertyPath().toString().equals("toolInformation"))
                    .findFirst()).isNotEmpty();
        }
    }


}
