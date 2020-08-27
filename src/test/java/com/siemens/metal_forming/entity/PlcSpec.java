package com.siemens.metal_forming.entity;

import com.siemens.metal_forming.enumerated.ConnectionStatus;
import com.siemens.metal_forming.enumerated.ToolStatusType;
import com.siemens.metal_forming.exception.exceptions.InvalidToolsException;
import com.siemens.metal_forming.exception.exceptions.ToolNotFoundException;
import org.junit.jupiter.api.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("<= PLC SPECIFICATION =>")
public class PlcSpec {
    private Validator validator;
    private Plc plc;

    @BeforeEach
    void initializePlc(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        plc = new Plc();
    }

    @Nested @DisplayName("NEW PLC")
    class newPlc{
        @Test @DisplayName("is always created with empty set of tools (constructor)")
        void isAlwaysCreatedWithEmptySetOfToolsConstructor(){
            assertThat(plc.getTools()).isNotNull();
        }

        @Test @DisplayName("is always created with empty set of tools (builder)")
        void isAlwaysCreatedWithEmptySetOfToolsBuilder(){
            assertThat(Plc.builder().build().getTools()).isNotNull();
        }

        @Test @DisplayName("is always created with empty hardwareInformations")
        void isAlwaysCreatedWithEmptyHardwareInformation(){
            assertThat(plc.getHardwareInformation()).isNotNull();
        }

        @Test @DisplayName("is created with connection where status is FAILED and lastUpdate is now")
        void isCreatedWithConnection(){
            long acceptedTimeDifferenceInMillis = 1000;

            long connectionMillis = plc.getConnection().getLastStatusChange().getTime();
            long currentMillis = System.currentTimeMillis();

            assertThat(plc.getConnection()).isNotNull();
            assertThat(plc.getConnection().getStatus()).isEqualTo(ConnectionStatus.DISCONNECTED);
            assertThat(Math.abs((connectionMillis - currentMillis))).isLessThan(acceptedTimeDifferenceInMillis);
        }
    }

    @Nested @DisplayName("CONNECTION")
    class connection{
        @Test @DisplayName("connection of plc is set to CONNECTED with last update \"now\" when markAsConnected method is called")
        void markAsConnectedTest(){
            plc.getConnection().getLastStatusChange().setTime(1);

            plc.markAsConnected();

            assertThat(plc.getConnection()).isNotNull();
            assertThat(plc.getConnection().getStatus()).isEqualTo(ConnectionStatus.CONNECTED);
            assertThat(Math.abs((System.currentTimeMillis() - plc.getConnection().getLastStatusChange().getTime())))
                    .isLessThan(1000); //time difference in ms
        }

        @Test @DisplayName("connection of plc is set to DISCONNECTED with last update \"now\" when markAsDisconnected method is called")
        void markAsDisconnectedTest(){
            plc.getConnection().getLastStatusChange().setTime(1); //sets random time

            plc.markAsDisconnected();


            assertThat(plc.getConnection()).isNotNull();
            assertThat(plc.getConnection().getStatus()).isEqualTo(ConnectionStatus.DISCONNECTED);
            assertThat(Math.abs((System.currentTimeMillis() - plc.getConnection().getLastStatusChange().getTime())))
                    .isLessThan(1000); //time difference in ms

        }

        @Test @DisplayName("updates connection status and set last update to \"now\"")
        void updatesConnectionStatusAndSetLastUpdateToNow(){
            plc.setConnectionStatus(ConnectionStatus.CONNECTED);

            assertThat(plc.getConnection().getStatus()).isEqualTo(ConnectionStatus.CONNECTED);
            assertThat(Math.abs((System.currentTimeMillis() - plc.getConnection().getLastStatusChange().getTime())))
                    .isLessThan(1000); //time difference in ms

        }

    }

    @Nested @DisplayName("VALIDATION")
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

        @Test @DisplayName("is invalid when currentTool is null") @Disabled
        void isInvalidWhenCurrentToolIsNull(){
            Set<ConstraintViolation<Plc>> violations = validator.validate(plc);
            assertThat(violations
                    .stream()
                    .filter(plcConstraintViolation -> plcConstraintViolation.getPropertyPath().toString().equals("currentTool"))
                    .findFirst()).isNotEmpty();
        }

        @Test @DisplayName("is invalid when name is null")
        void isInvalidWhenNameIsNull(){
            plc.setName(null);
            Set<ConstraintViolation<Plc>> violations = validator.validate(plc);
            assertThat(violations
                    .stream()
                    .filter(plcConstraintViolation -> plcConstraintViolation.getPropertyPath().toString().equals("name"))
                    .findFirst()).isNotEmpty();
        }

        @Test @DisplayName("is invalid when name is empty string")
        void isInvalidWhenNameIsEmptyString(){
            plc.setName("");
            Set<ConstraintViolation<Plc>> violations = validator.validate(plc);
            assertThat(violations
                    .stream()
                    .filter(plcConstraintViolation -> plcConstraintViolation.getPropertyPath().toString().equals("name"))
                    .findFirst()).isNotEmpty();
        }
    }

    @Nested @DisplayName("SET CURRENT TOOL")
    class setCurrentTool{
        @BeforeEach
        void initializePlcWithAttributesNeededForCurrentTool(){
            Set<Tool> tools = new HashSet<>();
            for(int i=0; i<10; i++){
                Tool tool = new Tool();
                tool.setToolNumber(i);
                tool.setToolStatus(ToolStatusType.AUTODETECTED);
                Curve referenceCurve = new Curve();
                for(int j=0; j<100; j++){
                    referenceCurve.getPoints().add(new PointOfTorqueAndSpeed((float)Math.random(),(float)Math.random()));
                }
                tool.setReferenceCurve(referenceCurve);
                tools.add(tool);
            }

            plc.setTools(tools);
            plc.setCurrentTool(0);
        }

        @Test @DisplayName("when toolId is incorrect throws ToolNotFoundException")
        void whenToolIdIsIncorrectThrowsException(){
            assertThrows(ToolNotFoundException.class, () -> plc.setCurrentTool(11));
        }

        @Test @DisplayName("sets correct currentTool based on toolId")
        void setsCorrectCurrentTool(){
            int toolId = 5;
            Tool toolToBeSetAsFutureCurrentTool = plc.getTools().stream().filter(tool -> tool.getToolNumber() == toolId).findAny().get();
            plc.setCurrentTool(toolId);
            assertThat(plc.getCurrentTool()).isEqualTo(toolToBeSetAsFutureCurrentTool);
        }
    }

    @Nested @DisplayName("SET TOOLS")
    class setTools{
        @BeforeEach
        void initializePlcWithAttributesNeededForCurrentTool(){
            for(int i=0; i<10; i++){
                Tool tool = new Tool();
                tool.setToolNumber(i);
                tool.setToolStatus(ToolStatusType.AUTODETECTED);
                Curve referenceCurve = new Curve();
                for(int j=0; j<100; j++){
                    referenceCurve.getPoints().add(new PointOfTorqueAndSpeed((float)Math.random(),(float)Math.random()));
                }
                tool.setReferenceCurve(referenceCurve);
                plc.getTools().add(tool);
            }
        }

        @Test @DisplayName("old tools are replaced with new ones")
        void oldToolsAreReplacedWithNewOnes(){
            Set<Tool> newTools = new HashSet<>();
            newTools.add(new Tool());

            assertThat(plc.getTools().size()).isNotEqualTo(newTools.size());
            plc.setTools(newTools);
            assertThat(plc.getTools().size()).isEqualTo(newTools.size());
        }

        @Test @DisplayName("throws exception when null is entered as parameter")
        void throwsExceptionWhenNullIsProvided(){
            assertThrows(InvalidToolsException.class,()-> plc.setTools(null));
        }
    }



}
