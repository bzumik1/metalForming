package com.siemens.metal_forming.entity;

import com.siemens.metal_forming.entity.abstractSpec.EntitySpec;
import com.siemens.metal_forming.enumerated.ConnectionStatus;
import com.siemens.metal_forming.exception.exceptions.*;
import com.siemens.metal_forming.testBuilders.TestPlcBuilder;
import com.siemens.metal_forming.testBuilders.TestToolBuilder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.sql.Timestamp;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@FieldDefaults(level = AccessLevel.PRIVATE)
@DisplayName("<= PLC SPECIFICATION =>")
public class PlcSpec extends EntitySpec {
    TestPlcBuilder testPlcBuilder;

    public PlcSpec() {
        super(Plc.class);
    }

    @BeforeEach
    void initializePlc(){
        testPlcBuilder = new TestPlcBuilder();
    }

    @Nested @DisplayName("NEW PLC")
    class NewPlc{
        @Nested@DisplayName("DEFAULT CONSTRUCTOR")
        class DefaultConstructor{
            @Test @DisplayName("is always created with empty set of tools")
            void isAlwaysCreatedWithEmptySetOfToolsConstructor(){
                Plc testPlc = new Plc();

                assertThat(testPlc.getTools()).isNotNull();
            }

            @Test @DisplayName("is always created with empty hardwareInformation")
            void isAlwaysCreatedWithEmptyHardwareInformation(){
                Plc testPlc = new Plc();

                assertThat(testPlc.getHardwareInformation()).isNotNull();
            }

            @Test @DisplayName("is created with connection where status is FAILED and lastUpdate is now")
            void isCreatedWithConnection(){
                Plc testPlc = new Plc();

                long acceptedTimeDifferenceInMillis = 1000;
                long connectionMillis = testPlc.getConnection().getLastStatusChange().getTime();
                long currentMillis = System.currentTimeMillis();

                assertThat(testPlc.getConnection()).isNotNull();
                assertThat(testPlc.getConnection().getStatus()).isEqualTo(ConnectionStatus.DISCONNECTED);
                assertThat(Math.abs((connectionMillis - currentMillis))).isLessThan(acceptedTimeDifferenceInMillis);
            }
        }

        @Nested @DisplayName("BUILDER")
        class Builder{
            @Test @DisplayName("is always created with empty set of tools")
            void isAlwaysCreatedWithEmptySetOfToolsConstructor(){
                Plc testPlc = Plc.builder().build();

                assertThat(testPlc.getTools()).isNotNull();
            }

            @Test @DisplayName("is always created with empty hardwareInformation")
            void isAlwaysCreatedWithEmptyHardwareInformation(){
                Plc testPlc = Plc.builder().build();

                assertThat(testPlc.getHardwareInformation()).isNotNull();
            }

            @Test @DisplayName("is created with connection where status is FAILED and lastUpdate is now")
            void isCreatedWithConnection(){
                Plc testPlc = Plc.builder().build();

                long acceptedTimeDifferenceInMillis = 1000;
                long connectionMillis = testPlc.getConnection().getLastStatusChange().getTime();
                long currentMillis = System.currentTimeMillis();

                assertThat(testPlc.getConnection()).isNotNull();
                assertThat(testPlc.getConnection().getStatus()).isEqualTo(ConnectionStatus.DISCONNECTED);
                assertThat(Math.abs((connectionMillis - currentMillis))).isLessThan(acceptedTimeDifferenceInMillis);
            }
        }
    }

    @Nested @DisplayName("CONNECTION")
    class ConnectionSetup{
        @Test @DisplayName("connection of plc is set to CONNECTED with last update \"now\" when markAsConnected method is called")
        void markAsConnectedTest(){
            Plc testPlc = testPlcBuilder.disconnectedOn(new Timestamp(1L)).build();

            testPlc.markAsConnected();

            assertThat(testPlc.getConnection()).isNotNull();
            assertThat(testPlc.getConnection().getStatus()).isEqualTo(ConnectionStatus.CONNECTED);
            assertThat(Math.abs((System.currentTimeMillis() - testPlc.getConnection().getLastStatusChange().getTime())))
                    .isLessThan(1000); //time difference in ms
        }

        @Test @DisplayName("connection of plc is set to DISCONNECTED with last update \"now\" when markAsDisconnected method is called")
        void markAsDisconnectedTest(){
            Plc testPlc = testPlcBuilder.connectedOn(new Timestamp(1L)).build();

            testPlc.markAsDisconnected();

            assertThat(testPlc.getConnection()).isNotNull();
            assertThat(testPlc.getConnection().getStatus()).isEqualTo(ConnectionStatus.DISCONNECTED);
            assertThat(Math.abs((System.currentTimeMillis() - testPlc.getConnection().getLastStatusChange().getTime())))
                    .isLessThan(1000); //time difference in ms

        }

        @Test @DisplayName("updates connection status and set last update to \"now\"")
        void updatesConnectionStatusAndSetLastUpdateToNow(){
            Plc testPlc = testPlcBuilder.disconnectedOn(new Timestamp(1L)).build();

            testPlc.setConnectionStatus(ConnectionStatus.CONNECTED);

            assertThat(testPlc.getConnection().getStatus()).isEqualTo(ConnectionStatus.CONNECTED);
            assertThat(Math.abs((System.currentTimeMillis() - testPlc.getConnection().getLastStatusChange().getTime())))
                    .isLessThan(1000); //time difference in ms

        }

        @Test @DisplayName("returns true if connection status is CONNECTED")
        void returnsTrueIfConnectionStatusIsConnected(){
            Plc plc = Plc.builder().build();
            plc.markAsConnected();

            assertThat(plc.isConnected()).isTrue();
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


        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {"just a random string","1.1.1","1.1.1.1.1","1.1.1.256","1.1.256.1","1.265.1.1","256.1.1.1"})
        @DisplayName("is invalid when IP address is invalid")
        void plcAlwaysHasIpAddress(String invalidIpAddress){
            Plc testPlc = testPlcBuilder.ipAddress(invalidIpAddress).build();

            Set<ConstraintViolation<Plc>> violations = validator.validate(testPlc);

            assertThat(violations
                    .stream()
                    .filter(plcConstraintViolation -> plcConstraintViolation.getPropertyPath().toString().equals("ipAddress"))
                    .findFirst()).isNotEmpty();
        }


        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("is invalid when name is invalid")
        void isInvalidWhenNameIsNull(String invalidName){
            Plc testPlc = testPlcBuilder.name(invalidName).build();

            Set<ConstraintViolation<Plc>> violations = validator.validate(testPlc);

            assertThat(violations
                    .stream()
                    .filter(plcConstraintViolation -> plcConstraintViolation.getPropertyPath().toString().equals("name"))
                    .findFirst()).isNotEmpty();
        }
    }

    @Nested @DisplayName("TOOLS")
    class Tools{

        @Nested @DisplayName("SET CURRENT TOOL")
        class SetCurrentTool{
            @Test @DisplayName("when toolNumber is incorrect throws ToolNotFoundException")
            void whenToolIdIsIncorrectThrowsException(){
                Tool tool1 = new TestToolBuilder().toolNumber(1).build();
                Tool tool2 = new TestToolBuilder().toolNumber(2).build();
                Plc testPlc = testPlcBuilder.addTool(tool1).addTool(tool2).build();

                assertThrows(ToolNotFoundException.class, () -> testPlc.setCurrentTool(3));
            }

            @Test @DisplayName("throws InvalidToolNumberException when toolNumber is null")
            void throwsInvalidToolNumberExceptionWhenToolNumberIsNull(){
                Tool tool1 = new TestToolBuilder().toolNumber(1).build();
                Plc testPlc = testPlcBuilder.addTool(tool1).build();

                assertThrows(InvalidToolNumberException.class, () -> testPlc.setCurrentTool(null));
            }

            @Test @DisplayName("sets correct currentTool based on toolId")
            void setsCorrectCurrentTool(){
                Plc testPlc = testPlcBuilder.randomTools(3).build();

                testPlc.setCurrentTool(1);

                assertThat(testPlc.getCurrentTool().getToolNumber()).isEqualTo(1);
            }
        }

        @Nested @DisplayName("SET TOOLS")
        class SetTools{

            @Test @DisplayName("old tools are replaced with new ones")
            void oldToolsAreReplacedWithNewOnes(){
                Plc testPlc = testPlcBuilder.randomTools(2).build();
                Set<Tool> newTools = Stream.iterate(0,n-> n+1).map(i -> new TestToolBuilder().toolNumber(i).build()).limit(5).collect(Collectors.toSet());

                testPlc.setTools(newTools);

                assertThat(testPlc.getTools()).containsExactlyInAnyOrderElementsOf(newTools);
            }

            @Test @DisplayName("sets plc for all new tools")
            void setsPlcForAllNewTools(){
                Plc testPlc = testPlcBuilder.randomTools(2).build();
                Set<Tool> newTools = Stream.iterate(0,n-> n+1).map(i -> new TestToolBuilder().toolNumber(i).build()).limit(5).collect(Collectors.toSet());

                testPlc.setTools(newTools);

                SoftAssertions softAssertions = new SoftAssertions();
                newTools.forEach(tool -> softAssertions.assertThat(tool.getPlc()).isEqualTo(testPlc));
                softAssertions.assertAll();
            }

            @Test @DisplayName("throws exception when null is entered as parameter")
            void throwsExceptionWhenNullIsProvided(){
                Plc testPlc = testPlcBuilder.randomTools(3).build();

                assertThrows(InvalidToolsException.class,()-> testPlc.setTools(null));
            }
        }

        @Nested @DisplayName("ADD TOOL")
        class AddTool{
            @Test @DisplayName("throws ToolUniqueConstrainException when tool is already in PLC's tools")
            void throwsToolUniqueConstrainExceptionWhenToolIsAlreadyInPlc(){
                Tool oldTool = new TestToolBuilder().toolNumber(1).build();
                Plc testPlc = testPlcBuilder.addTool(oldTool).build();

                assertThrows(ToolUniqueConstrainException.class,() -> testPlc.addTool(new TestToolBuilder().toolNumber(1).build()));
            }

            @Test @DisplayName("throws InvalidToolException when tool is null")
            void throwsInvalidToolExceptionWhenToolIsNull(){
                Plc testPlc = testPlcBuilder.build();

                assertThrows(InvalidToolException.class, () -> testPlc.addTool(null));
            }

            @Test @DisplayName("sets plc of the tool to this plc")
            void setsPlcOfTheToolToThisPlc(){
                Tool newTool = new TestToolBuilder().toolNumber(1).build();
                Plc testPlc = testPlcBuilder.build();

                testPlc.addTool(newTool);

                assertThat(newTool.getPlc()).isEqualTo(testPlc);
            }

            @Test @DisplayName("adds tool if tool is unique")
            void addsToolIfToolNumberIsUnique(){
                Tool oldTool = new TestToolBuilder().toolNumber(1).build();
                Tool newTool = new TestToolBuilder().toolNumber(2).build();
                Plc testPlc = testPlcBuilder.addTool(oldTool).build();

                testPlc.addTool(newTool);

                assertThat(testPlc.getTools().size()).isEqualTo(2);
            }
        }

        @Nested @DisplayName("GET TOOL BY TOOL NUMBER")
        class GetToolByToolNumber{
            @Test @DisplayName("returns tool when tool was not found")
            void returnsToolWhenToolWasFound(){
                Tool tool1 = new TestToolBuilder().toolNumber(1).build();
                Tool tool2 = new TestToolBuilder().toolNumber(2).build();
                Plc testPlc = testPlcBuilder.addTool(tool1).addTool(tool2).build();

                assertThat(testPlc.getToolByToolNumber(1)).isNotNull();
            }

            @Test @DisplayName("throws InvalidToolNumberException when toolNumber is null")
            void throwsInvalidToolNumberExceptionWhenToolNumberIsNull(){
                Tool tool1 = new TestToolBuilder().toolNumber(1).build();
                Plc testPlc = testPlcBuilder.addTool(tool1).build();

                assertThrows(InvalidToolNumberException.class, () -> testPlc.getToolByToolNumber(null));
            }

            @Test @DisplayName("throws ToolNotFoundException when tool was found")
            void throwsExceptionWhenToolWasNotFound(){
                Tool tool1 = new TestToolBuilder().toolNumber(1).build();
                Tool tool2 = new TestToolBuilder().toolNumber(2).build();
                Plc testPlc = testPlcBuilder.addTool(tool1).addTool(tool2).build();

                assertThrows(ToolNotFoundException.class,() -> testPlc.getToolByToolNumber(3));
            }
        }

        @Nested @DisplayName("GET TOOL BY ID")
        class GetToolById{
            @Test @DisplayName("returns tool when tool was found")
            void returnsToolWhenToolWasFound(){
                Tool tool1 = new TestToolBuilder().id(1L).toolNumber(55).build();
                Tool tool2 = new TestToolBuilder().id(2L).toolNumber(56).build();
                Plc testPlc = testPlcBuilder.addTool(tool1).addTool(tool2).build();

                assertThat(testPlc.getToolById(1L)).isNotNull();
            }

            @Test @DisplayName("throws InvalidToolNumberException when id is null")
            void throwsInvalidToolNumberExceptionWhenIdIsNull(){
                Tool tool1 = new TestToolBuilder().id(1L).toolNumber(55).build();
                Plc testPlc = testPlcBuilder.addTool(tool1).build();

                assertThrows(InvalidIdException.class, () -> testPlc.getToolById(null));
            }

            @Test @DisplayName("throws ToolNotFoundException when tool was found")
            void throwsExceptionWhenToolWasNotFound(){
                Tool tool1 = new TestToolBuilder().id(1L).toolNumber(55).build();
                Tool tool2 = new TestToolBuilder().id(2L).toolNumber(56).build();
                Plc testPlc = testPlcBuilder.addTool(tool1).addTool(tool2).build();

                assertThrows(ToolNotFoundException.class,() -> testPlc.getToolById(3L));
            }
        }

        @Nested @DisplayName("REMOVE TOOL")
        class RemoveTool{
            @Test @DisplayName("return false when tool with same toolNumber was not found")
            void returnFalseWhenToolWasNotFound(){
                Tool tool1 = new TestToolBuilder().toolNumber(1).build();
                Tool toolWhichIsNotInPlc = new TestToolBuilder().toolNumber(2).build();
                Plc testPlc = testPlcBuilder.addTool(tool1).build();

                assertThat(testPlc.removeTool(toolWhichIsNotInPlc)).isEqualTo(false);
            }

            @Test @DisplayName("removes tool when tool with same toolNumber was found tool")
            void removesToolWhenToolWithSameToolNumberWasFound(){
                Tool toolWhichIsInPlc = new TestToolBuilder().toolNumber(1).build();
                Plc testPlc = testPlcBuilder.addTool(toolWhichIsInPlc).addTool(toolWhichIsInPlc).build();

                testPlc.removeTool(toolWhichIsInPlc);

                assertThat(testPlc.getTools()).isEmpty();
            }

            @Test @DisplayName("removes tool when tool id was changed")
            void removesToolWhenToolIdWasChanged(){
                Tool testTool = new TestToolBuilder().toolNumber(1).build();
                Plc testPlc = testPlcBuilder.addTool(testTool).build();

                testTool.setToolNumber(2);
                testPlc.removeTool(testTool);

                assertThat(testPlc.getTools()).isEmpty();
            }
        }

        @Nested @DisplayName("HAS TOOL BY TOOL NUMBER")
        class HasToolByToolNumber{
            @Test @DisplayName("returns true if plc has tool with given tool number")
            void returnsTrueWhenPlcHasToolWithGivenToolNumber(){
                Tool currentTool = Tool.builder().toolNumber(99).build();
                Plc plc = testPlcBuilder.randomTools(2).currentTool(currentTool).build();

                assertThat(plc.hasToolByToolNumber(99)).isEqualTo(true);
            }
        }
    }
}
