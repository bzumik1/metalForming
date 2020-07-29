package com.siemens.metal_forming.entity;

import org.junit.jupiter.api.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("<= Plc Specification =>")
public class PlcSpec {
    private Validator validator;
    private Plc plc;

    @BeforeEach
    void initializePlc(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        plc = new Plc();
    }

    @Nested @DisplayName("validation")
    class validation{
        @Nested @DisplayName("IP address validation")
        class ipAddressValidation{


            @Test @DisplayName("is invalid when IP address is null")
            void plcAlwaysHasIpAddress(){
                Set<ConstraintViolation<Plc>> violations = validator.validate(plc);
                assertThat(violations
                        .stream()
                        .filter(plcConstraintViolation -> plcConstraintViolation.getPropertyPath().toString().equals("ipAddress"))
                        .findFirst()).isNotEmpty();
            }

            @Test @DisplayName("is invalid when IP address is empty")
            void ipAddressIsNotEmpty(){
                plc.setIpAddress("");
                Set<ConstraintViolation<Plc>> violations = validator.validate(plc);
                assertThat(violations
                        .stream()
                        .filter(plcConstraintViolation -> plcConstraintViolation.getPropertyPath().toString().equals("ipAddress"))
                        .findFirst()).isNotEmpty();
            }

            @Test @DisplayName("is invalid when IP address is \"just a random string\"")
            void ipAddressIsInCorrectFormat(){
                plc.setIpAddress("just a random string");
                Set<ConstraintViolation<Plc>> violations = validator.validate(plc);
                assertThat(violations
                        .stream()
                        .filter(plcConstraintViolation -> plcConstraintViolation.getPropertyPath().toString().equals("ipAddress"))
                        .findFirst()).isNotEmpty();
            }

            @Test @DisplayName("is invalid when IP address is to short \"1.1.1\"")
            void ipAddressIsToShort(){
                plc.setIpAddress("1.1.1");
                Set<ConstraintViolation<Plc>> violations = validator.validate(plc);
                assertThat(violations
                        .stream()
                        .filter(plcConstraintViolation -> plcConstraintViolation.getPropertyPath().toString().equals("ipAddress"))
                        .findFirst()).isNotEmpty();
            }

            @Test @DisplayName("is invalid when IP address is to long \"1.1.1.1.1\"")
            void ipAddressIsToLong(){
                plc.setIpAddress("1.1.1.1.1");
                Set<ConstraintViolation<Plc>> violations = validator.validate(plc);
                assertThat(violations
                        .stream()
                        .filter(plcConstraintViolation -> plcConstraintViolation.getPropertyPath().toString().equals("ipAddress"))
                        .findFirst()).isNotEmpty();
            }

            @Test @DisplayName("is invalid when one of IP addresses number is out of range\"1.1.1.256\"")
            void ipAddressIsOutOfRange(){
                plc.setIpAddress("1.1.1.256");
                Set<ConstraintViolation<Plc>> violations = validator.validate(plc);
                assertThat(violations).isNotEmpty();
            }
        }

        @Test @DisplayName("is invalid when curve is null")
        void isInvalidWhenCurveIsNull(){
            Set<ConstraintViolation<Plc>> violations = validator.validate(plc);
            assertThat(violations
                    .stream()
                    .filter(plcConstraintViolation -> plcConstraintViolation.getPropertyPath().toString().equals("motorCurve"))
                    .findFirst()).isNotEmpty();
        }
    }



}
