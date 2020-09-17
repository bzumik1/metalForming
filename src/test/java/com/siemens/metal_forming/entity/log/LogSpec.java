package com.siemens.metal_forming.entity.log;

import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.entity.CurvePoint;
import com.siemens.metal_forming.entity.abstractSpec.ImmutableEntitySpec;
import com.siemens.metal_forming.enumerated.StopReactionType;
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
    Log validLogWithAllAttributes;

    public LogSpec() {
        super(Log.class);
    }

    @BeforeEach
    void initialize(){
        Curve curve = new Curve();
        Curve motorCurve = new Curve();
        Curve referenceCurve = new Curve();
        for (int i = 0; i < 100; i++) {
            curve.getPoints().add(new CurvePoint((float) Math.random(), (float) Math.random()));
            motorCurve.getPoints().add(new CurvePoint((float) Math.random(), (float) Math.random()));
            referenceCurve.getPoints().add(new CurvePoint((float) Math.random(), (float) Math.random()));
        }

        PlcInfo plcInfo = PlcInfo.builder().firmwareNumber("FW").serialNumber("SN").ipAddress("192.167.0.").name("plc").build();
        ToolInfo toolInfo = ToolInfo.builder().toolNumber(1).name("tool").stopReaction(StopReactionType.IMMEDIATE).build();

        Set<CollisionPoint> collisionPoints = new HashSet<>();
        collisionPoints.add(new CollisionPoint((float) Math.random(), (float) Math.random()));
        collisionPoints.add(new CollisionPoint((float) Math.random(), (float) Math.random()));

        validLogWithAllAttributes = Log.builder()
                .measuredCurve(curve)
                .referenceCurve(referenceCurve)
                .collisionPoints(collisionPoints)
                .plcInformation(plcInfo)
                .toolInformation(toolInfo)
                .comment("comment")
                .build();
    }

    @Test @DisplayName("is created with current timestamp")
    void isCreatedWithCurrentTimestamp(){
        long acceptedTimeDifferenceInMillis = 1000;

        long connectionMillis = validLogWithAllAttributes.getCreatedOn().getTime();
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
            Log invalidLog = validLogWithAllAttributes.toBuilder().measuredCurve(null).build();
            Set<ConstraintViolation<Log>> violations = validator.validate(invalidLog);

            assertThat(violations
                    .stream()
                    .filter(violation -> violation.getPropertyPath().toString().equals("measuredCurve"))
                    .findFirst()).isNotEmpty();
        }

        @Test @DisplayName("is invalid when motor curve is null")
        void isInvalidWhenMotorCurveIsNull(){
            Log invalidLog = validLogWithAllAttributes.toBuilder().motorCurve(null).build();
            Set<ConstraintViolation<Log>> violations = validator.validate(invalidLog);

            assertThat(violations
                    .stream()
                    .filter(violation -> violation.getPropertyPath().toString().equals("motorCurve"))
                    .findFirst()).isNotEmpty();
        }

        @Test @DisplayName("is invalid when reference curve is null")
        void isInvalidWhenReferenceCurveIsNull(){
            Log invalidLog = validLogWithAllAttributes.toBuilder().referenceCurve(null).build();
            Set<ConstraintViolation<Log>> violations = validator.validate(invalidLog);

            assertThat(violations
                    .stream()
                    .filter(violation -> violation.getPropertyPath().toString().equals("referenceCurve"))
                    .findFirst()).isNotEmpty();
        }

        @Test @DisplayName("is invalid when plc information is null")
        void isInvalidWhenPlcInformationIsNull(){
            Log invalidLog = validLogWithAllAttributes.toBuilder().plcInformation(null).build();
            Set<ConstraintViolation<Log>> violations = validator.validate(invalidLog);

            assertThat(violations
                    .stream()
                    .filter(violation -> violation.getPropertyPath().toString().equals("plcInformation"))
                    .findFirst()).isNotEmpty();
        }

        @Test @DisplayName("is invalid when tool information is null")
        void isInvalidWhenToolInformationIsNull(){
            Log invalidLog = validLogWithAllAttributes.toBuilder().toolInformation(null).build();
            Set<ConstraintViolation<Log>> violations = validator.validate(invalidLog);

            assertThat(violations
                    .stream()
                    .filter(violation -> violation.getPropertyPath().toString().equals("toolInformation"))
                    .findFirst()).isNotEmpty();
        }
    }


}
