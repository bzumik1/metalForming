package com.siemens.metal_forming.entity;

import com.siemens.metal_forming.enumerated.ConnectionStatus;
import com.siemens.metal_forming.enumerated.ToolStatusType;
import com.siemens.metal_forming.exception.exceptions.InvalidToolsException;
import com.siemens.metal_forming.exception.exceptions.ToolNotFoundException;
import com.siemens.metal_forming.exception.exceptions.ToolUniqueConstrainException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@FieldDefaults(level = AccessLevel.PRIVATE)
@DisplayName("<= PLC SPECIFICATION =>")
public class PlcSpec extends EntitySpec{
    Plc plc;

    @Override
    public Class getTestedClass() {
        return Plc.class;
    }

    @BeforeEach
    void initializePlc(){
        plc = new Plc();
    }

    @Nested @DisplayName("NEW PLC")
    class NewPlc{
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
    class Connection{
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

    @Nested @DisplayName("PLC VALIDATION")
    class PlcValidation{
        private Validator validator;
        @BeforeEach
        void initializeForPlcValidation(){
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            validator = factory.getValidator();
        }

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

    @Nested @DisplayName("TOOLS")
    class Tools{
        @BeforeEach
        void initializeForTools(){
            for(int i=0; i<3; i++){
                Tool tool = Tool.builder().id((long)i).toolNumber(i).toolStatus(ToolStatusType.AUTODETECTED).build();
                plc.addTool(tool);
            }
        }
        @Nested @DisplayName("SET CURRENT TOOL")
        class SetCurrentTool{
            @Test @DisplayName("when toolId is incorrect throws ToolNotFoundException")
            void whenToolIdIsIncorrectThrowsException(){
                assertThrows(ToolNotFoundException.class, () -> plc.setCurrentTool(plc.getTools().size()));
            }

            @Test @DisplayName("sets correct currentTool based on toolId")
            void setsCorrectCurrentTool(){
                int toolNumber = 2;
                Tool toolToBeSetAsFutureCurrentTool = plc.getTools()
                        .stream().filter(tool -> tool.getToolNumber() == toolNumber)
                        .findAny()
                        .orElseThrow(()-> new RuntimeException("error in test"));

                plc.setCurrentTool(toolNumber);
                assertThat(plc.getCurrentTool()).isEqualTo(toolToBeSetAsFutureCurrentTool);
            }
        }

        @Nested @DisplayName("SET TOOLS")
        class SetTools{
            Set<Tool> newTools;

            @BeforeEach
            void initializeForSetTools(){
                newTools = new HashSet<>();
                newTools.add(Tool.builder().toolNumber(1).build());
                newTools.add(Tool.builder().toolNumber(2).build());
            }

            @Test @DisplayName("old tools are replaced with new ones")
            void oldToolsAreReplacedWithNewOnes(){
                assertThat(plc.getTools().size()).isNotEqualTo(newTools.size());
                plc.setTools(newTools);
                assertThat(plc.getTools().size()).isEqualTo(newTools.size());
            }

            @Test @DisplayName("sets plc for all new tools")
            void setsPlcForAllNewTools(){
                plc.setTools(newTools);

                SoftAssertions softAssertions = new SoftAssertions();
                for (Tool t: newTools){
                    softAssertions.assertThat(t.getPlc()).isEqualTo(plc);
                }
                softAssertions.assertAll();
            }

            @Test @DisplayName("throws exception when null is entered as parameter")
            void throwsExceptionWhenNullIsProvided(){
                assertThrows(InvalidToolsException.class,()-> plc.setTools(null));
            }
        }

        @Nested @DisplayName("ADD TOOL")
        class AddTool{
            @Test @DisplayName("throws ToolUniqueConstrainException when tool is already in PLC's tools")
            void throwsToolUniqueConstrainExceptionWhenToolIsAlreadyInPlc(){
                Tool tool2 = Tool.builder().id(1L).toolNumber(1).name("name2").build();

                assertThrows(ToolUniqueConstrainException.class,() -> plc.addTool(tool2));
            }

            @Test @DisplayName("sets plc of the tool to this plc")
            void setsPlcOfTheToolToThisPlc(){
                Tool newTool = Tool.builder().build();
                plc.addTool(newTool);

                assertThat(newTool.getPlc()).isEqualTo(plc);
            }

            @Test @DisplayName("adds tool if toolNumberIsUnique")
            void addsToolIfToolNumberIsUnique(){
                int sizeOfOldTools = plc.getTools().size();
                Tool tool2 = Tool.builder().id(1L).toolNumber(sizeOfOldTools).name("name").build();
                plc.addTool(tool2);

                assertThat(plc.getTools().size()).isEqualTo(sizeOfOldTools+1);
            }
        }

        @Nested @DisplayName("GET TOOL BY TOOL NUMBER")
        class GetToolByToolNumber{
            @Test @DisplayName("returns tool when tool was not found")
            void returnsToolWhenToolWasFound(){
                assertThat(plc.getTool(0)).isNotNull();
            }

            @Test @DisplayName("throws ToolNotFoundException when tool was found")
            void throwsExceptionWhenToolWasNotFound(){
                assertThrows(ToolNotFoundException.class,() -> plc.getTool(plc.getTools().size()));
            }
        }

        @Nested @DisplayName("GET TOOL BY ID")
        class GetToolById{
            @Test @DisplayName("returns tool when tool was not found")
            void returnsToolWhenToolWasFound(){
                assertThat(plc.getTool((long)0)).isNotNull();
            }

            @Test @DisplayName("throws ToolNotFoundException when tool was found")
            void throwsExceptionWhenToolWasNotFound(){
                assertThrows(ToolNotFoundException.class,() -> plc.getTool((long)plc.getTools().size()));
            }
        }

        @Nested @DisplayName("REMOVE TOOL")
        class RemoveTool{
            @Test @DisplayName("return false when tool with same toolNumber was not found")
            void returnFalseWhenToolWasNotFound(){
                assertThat(plc.removeTool(Tool.builder().toolNumber(plc.getTools().size()).build())).isEqualTo(false);
            }

            @Test @DisplayName("removes tool when tool with same toolNumber was found tool")
            void removesToolWhenToolWithSameToolNumberWasFound(){
                int oldToolsSize  = plc.getTools().size();
                plc.removeTool(Tool.builder().toolNumber(0).build());

                assertThat(plc.getTools().size()).isEqualTo(oldToolsSize-1);
            }
        }
    }
}
