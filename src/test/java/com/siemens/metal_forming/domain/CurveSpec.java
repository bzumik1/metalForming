package com.siemens.metal_forming.domain;

import com.siemens.metal_forming.domain.Curve;
import com.siemens.metal_forming.domain.PointOfTorqueAndSpeed;
import com.siemens.metal_forming.entity.abstractSpec.EntitySpec;
import com.siemens.metal_forming.exception.exceptions.CurveCreationException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.validation.*;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@FieldDefaults(level = AccessLevel.PRIVATE)
@DisplayName("<= CURVE SPECIFICATION =>")
class CurveSpec  extends EntitySpec {
    Curve curve;
    Validator validator;

    public CurveSpec() {
        super(Curve.class);
    }

    @BeforeEach
    void initialize(){
        curve = new Curve();
    }

    @Test @DisplayName("is created with empty list of values")
    void isCreatedWithEmptyListOfValues(){
        assertThat(curve.getPoints()).isNotNull();
    }

    @Nested @DisplayName("CONSTRUCTOR FROM LIST OF SPEED AND TORQUE")
    class ConstructorFromListOfSpeedAndTorque{
        @Test @DisplayName("throws Exception when speed and torque have different lengths")
        void throwsExceptionWhenSpeedAndTorqueHaveDifferentLength(){
            List<Float> torque = List.of(1f, 2f, 3f);
            List<Float> speed = List.of(1f, 2f);

            assertThrows(CurveCreationException.class, () -> new Curve(torque,speed));
        }

        @Test @DisplayName("creates curve with correct points")
        void createsCurveWithCorrectPoints(){
            List<Float> torque = List.of(1f, 2f);
            List<Float> speed = List.of(3f, 4f);

            Curve curve = new Curve(torque, speed);

            assertThat(curve.getPoints()).containsExactly(new PointOfTorqueAndSpeed(1f, 3f), new PointOfTorqueAndSpeed(2f, 4f));
        }
    }

    @Nested @DisplayName("VALIDATION")
    class validation{
        @BeforeEach
        void initializeValidator(){
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            validator = factory.getValidator();
        }

        @Test @DisplayName("is invalid when measuredCurve points are null")
        void isInvalidWhenPointOfTorqueAndSpeedsAreNull(){
            curve.setPoints(null);
            Set<ConstraintViolation<Curve>> violations = validator.validate(curve);
            assertThat(violations.stream()
                    .filter(curveConstraintViolation -> curveConstraintViolation.getPropertyPath().toString().equals("points"))
                    .findFirst()).isPresent();
        }
    }


}
