package com.siemens.metal_forming.repository;

import com.siemens.metal_forming.entity.*;
import com.siemens.metal_forming.testBuilders.TestCurveBuilder;
import com.siemens.metal_forming.testBuilders.TestPlcBuilder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

//TODO move nested attributes to own repository specification
@FieldDefaults(level = AccessLevel.PRIVATE)
@DisplayName("<= PLC REPOSITORY SPECIFICATION =>")
@ExtendWith(SpringExtension.class)
@DataJpaTest
class PlcRepositorySpec {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    PlcRepository plcRepository;

    @Autowired
    ToolRepository toolRepository;

    @Autowired
    HardwareInformationRepository hardwareInformationRepository;

    @Autowired
    CurvePointRepository curvePointRepository;

    @Autowired
    ConnectionRepository connectionRepository;

    TestCurveBuilder testCurveBuilder;

    TestPlcBuilder testPlcBuilder;

    @BeforeEach
    void initialize(){
        testCurveBuilder = new TestCurveBuilder();
        testPlcBuilder = new TestPlcBuilder();
    }




    @Nested @DisplayName("CASCADING") @DataJpaTest
    class Cascade{

        @Nested @DisplayName("DELETING PLC") @DataJpaTest
        class DeletingPlc{


            @Test @DisplayName("when PLC is deleted also hardwareInformation are deleted")
            void deletesAlsoHardwareInformation(){
                HardwareInformation hardwareInformation = HardwareInformation.builder().serialNumber("SN").firmwareNumber("FW").build();
                Plc testPlc = testPlcBuilder.hardwareInformation(hardwareInformation).build();

                Plc plc = plcRepository.save(testPlc);
                plcRepository.deleteById(plc.getId());
                Optional<HardwareInformation> hardwareInformationInDb = hardwareInformationRepository.findById(plc.getHardwareInformation().getId());

                assertThat(hardwareInformationInDb).isNotPresent();
            }

            @Test @DisplayName("when PLC is deleted also tools are deleted")
            void deletesAlsoTools(){
                Plc testPlc = testPlcBuilder.randomTools(3).build();

                Plc plc = plcRepository.save(testPlc);
                entityManager.flush();

                plcRepository.deleteById(plc.getId());
                List<Tool> tools = toolRepository.findAll();

                assertThat(tools).isEmpty();
            }

            @Test @DisplayName("when PLC is deleted also connection is deleted")
            void deleteAlsoConnection(){
                Connection connection = new Connection();
                Plc testPlc = testPlcBuilder.connection(connection).build();

                Plc plc = plcRepository.save(testPlc);
                entityManager.flush();

                plcRepository.deleteById(plc.getId());
                List<Connection> connections = connectionRepository.findAll();

                assertThat(connections).isEmpty();
            }
        }

        @Nested @DisplayName("UPDATING PLC") @DataJpaTest
        class UpdatingPlc{
            @Test @DisplayName("deletes tool from database when tool is deleted from plc")
            void deletesToolFromDatabaseWhenToolIsDeletedFromPlc(){
                Plc testPlc = testPlcBuilder.randomTools(3).build();

                Plc plc = plcRepository.save(testPlc);
                entityManager.flush();

                Tool randomTool = plc.getTools().stream().findAny().orElseThrow(() -> new RuntimeException("Plc doesn't have any tools"));
                plc.removeTool(randomTool); //todo remove by id
                plcRepository.save(plc);

                assertThat(toolRepository.findAll().size()).isEqualTo(2);
            }
        }

    }


    @Nested @DisplayName("CONSTRAINS") @DataJpaTest
    class Validation{
        @Nested @DisplayName("IP ADDRESS") @DataJpaTest
        class IpAddress{
            @Test @DisplayName("throws exception when ipAddress is \"null\"")
            void storePlcWithNullIpAddress(){
                Plc testPlc = testPlcBuilder.ipAddress(null).build();

                plcRepository.save(testPlc);

                assertThrows(ConstraintViolationException.class,() -> entityManager.flush());
            }

            @Test @DisplayName("throws exception when ipAddress is empty string")
            void throwsErrorWhenIpAddressIsEmpty(){
                Plc testPlc = testPlcBuilder.ipAddress("").build();

                plcRepository.save(testPlc);

                assertThrows(ConstraintViolationException.class, () -> entityManager.flush());
            }

            @Test @DisplayName("throws exception when ipAddress is in incorrect format")
            void throwsErrorWhenIpAddressIsInIncorrectFormat(){
                Plc testPlc = testPlcBuilder.ipAddress("192.168.0.300").build();

                plcRepository.save(testPlc);

                assertThrows(ConstraintViolationException.class, () -> entityManager.flush());
            }

            @Test @DisplayName("throws exception when two PLCs with same IP address are stored into database")
            void ipAddressIsUnique(){
                Plc testPlc1 = testPlcBuilder.ipAddress("192.168.0.1").name("name1").build();
                Plc testPlc2 = testPlcBuilder.ipAddress("192.168.0.1").name("name2").build();

                plcRepository.save(testPlc1);
                plcRepository.save(testPlc2);

                assertThrows(PersistenceException.class,() -> entityManager.flush());
            }


        }

        @Nested @DisplayName("NAME") @DataJpaTest
        class Name{
            @Test @DisplayName("throws exception when name is \"null\"")
            void throwsErrorWhenNameIsNull(){
                Plc testPlc = testPlcBuilder.name(null).build();

                plcRepository.save(testPlc);

                assertThrows(ConstraintViolationException.class, () -> entityManager.flush());
            }

            @Test @DisplayName("throws exception when name is empty string")
            void throwsErrorWhenNameIsEmpty(){
                Plc testPlc = testPlcBuilder.name("").build();

                plcRepository.save(testPlc);

                assertThrows(ConstraintViolationException.class, () -> entityManager.flush());
            }

            @Test @DisplayName("throws exception when two PLCs with same name are stored into database")
            void nameIsUnique(){
                Plc testPlc1 = testPlcBuilder.ipAddress("192.168.0.1").name("sameName").build();
                Plc testPlc2 = testPlcBuilder.ipAddress("192.168.0.2").name("sameName").build();

                plcRepository.save(testPlc1);
                plcRepository.save(testPlc2);

                assertThrows(PersistenceException.class,() -> entityManager.flush());
            }

        }
    }

    @Nested @DisplayName("OWN QUERIES")
    class OwnQueries{
        @Nested @DisplayName("EXISTS BY IP ADDRESS IGNORING ID") @DataJpaTest
        class ExistsByIpAddressIgnoringId{
            @Test @DisplayName("returns true when different plc with same ip exists and false if not")
            void returnsTrueWhenThereIsAnotherPlcWithSameIpAddressAndFalseIfNot(){
                Plc plcsInDb1 = new TestPlcBuilder().id(1L).name("plc1").ipAddress("192.168.0.1").build();
                Plc plcsInDb2 = new TestPlcBuilder().id(2L).name("plc2").ipAddress("192.168.0.2").build();

                plcsInDb1 = plcRepository.save(plcsInDb1);
                plcsInDb2 = plcRepository.save(plcsInDb2);

                SoftAssertions softAssertions = new SoftAssertions();
                softAssertions.assertThat(plcRepository.existsByIpAddressIgnoringId("192.168.0.1", plcsInDb2.getId()))
                        .as("there is different PLC with given IP addresss -> true")
                        .isTrue();
                softAssertions.assertThat(plcRepository.existsByIpAddressIgnoringId("192.168.0.2", plcsInDb2.getId()))
                        .as("there is only this PLC with given IP address (not different) -> false")
                        .isFalse();
                softAssertions.assertThat(plcRepository.existsByIpAddressIgnoringId("192.168.0.3", plcsInDb2.getId()))
                        .as("there is no PLC with given IP address -> false")
                        .isFalse();
                softAssertions.assertAll();
            }
        }

        @Nested @DisplayName("EXISTS BY NAME IGNORING ID") @DataJpaTest
        class ExistsByNameIgnoringId{
            @Test @DisplayName("returns true when different plc with same name exists and false if not")
            void returnsTrueWhenThereIsAnotherPlcWithSameNameAndFalseIfNot(){
                Plc plcsInDb1 = new TestPlcBuilder().id(1L).name("plc1").ipAddress("192.168.0.1").build();
                Plc plcsInDb2 = new TestPlcBuilder().id(2L).name("plc2").ipAddress("192.168.0.2").build();

                plcsInDb1 = plcRepository.save(plcsInDb1);
                plcsInDb2 = plcRepository.save(plcsInDb2);

                SoftAssertions softAssertions = new SoftAssertions();
                softAssertions.assertThat(plcRepository.existsByNameIgnoringId("plc1", plcsInDb2.getId()))
                        .as("there is different PLC with given name -> true")
                        .isTrue();
                softAssertions.assertThat(plcRepository.existsByNameIgnoringId("plc2", plcsInDb2.getId()))
                        .as("there is only this PLC with given IP address (not different) -> false")
                        .isFalse();
                softAssertions.assertThat(plcRepository.existsByNameIgnoringId("plc3", plcsInDb2.getId()))
                        .as("there is no PLC with given IP address -> false")
                        .isFalse();
                softAssertions.assertAll();
            }
        }


    }

    @Test
    void findByExample(){
        for (int p=0;p<10;p++) {
            Plc plcToBeStoredInDatabase = testPlcBuilder.ipAddress("192.168.0."+p).name("plc_"+p).build();
            plcRepository.save(plcToBeStoredInDatabase);
            entityManager.flush();
        }

        Plc plcExample = testPlcBuilder.ipAddress("192.168.0.1").name("plc_1").build();

        ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matchingAll()
                .withIgnorePaths("connection");
        Example<Plc> example = Example.of(plcExample,ignoringExampleMatcher);

        Plc foundPlc = plcRepository.findOne(example).get();

        System.out.println(foundPlc);

    }




}
