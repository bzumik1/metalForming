package com.siemens.metal_forming.repository;

import com.siemens.metal_forming.entity.*;
import com.siemens.metal_forming.enumerated.ToolStatusType;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

//TODO make tests independent (when new attribute is added to entyty ony one test will not work)
//TODO move nested attributes to own repository specification
@DisplayName("<= PLC REPOSITORY SPECIFICATION =>")
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PlcRepositorySpec {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PlcRepository plcRepository;

    @Autowired
    private CurveRepository curveRepository;

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private HardwareInformationRepository hardwareInformationRepository;

    @Autowired
    private PointOfTorqueAndSpeedRepository pointOfTorqueAndSpeedRepository;

    @Autowired
    private ConnectionRepository connectionRepository;





    @Nested @DisplayName("CASCADING") @DataJpaTest
    class Cascade{
        private Plc plcWithAllAttributes;

        @BeforeEach
        void initializeForCascading(){
            plcWithAllAttributes = new Plc();
            Curve motorCurve = new Curve();
            for(int i=0; i<100; i++){
                motorCurve.getPoints().add(new PointOfTorqueAndSpeed((float)Math.random(),(float)Math.random()));
            }
            Set<Tool> tools = new HashSet<>();
            for(int i=0; i<10; i++){
                Tool tool = new Tool();
                tool.setToolId(i);
                tool.setToolStatus(ToolStatusType.AUTODETECTED);
                Curve referenceCurve = new Curve();
                for(int j=0; j<100; j++){
                    referenceCurve.getPoints().add(new PointOfTorqueAndSpeed((float)Math.random(),(float)Math.random()));
                }
                tool.setReferenceCurve(referenceCurve);
                tools.add(tool);
            }

            plcWithAllAttributes.getHardwareInformation().setSerialNumber("SN 8370938");
            plcWithAllAttributes.getHardwareInformation().setFirmwareNumber("FW V1.2");
            plcWithAllAttributes.setMotorCurve(motorCurve);
            plcWithAllAttributes.setIpAddress("192.168.1.1");
            plcWithAllAttributes.markAsConnected();
            plcWithAllAttributes.setTools(tools);
            plcWithAllAttributes.setCurrentTool(0);
            plcWithAllAttributes.setName("name");
        }


        @Test @DisplayName("when PLC is deleted also motorCurve is deleted")
        void deletesAlsoCurve(){
            Plc plc = plcRepository.save(plcWithAllAttributes);
            plcRepository.deleteById(plc.getId());
            Optional<Curve> curveInDb = curveRepository.findById(plc.getMotorCurve().getId());

            assertThat(curveInDb).isNotPresent();
        }

        @Test @DisplayName("when PLC is deleted also hardwareInformation are deleted")
        void deletesAlsoHardwareInformation(){
            Plc plc = plcRepository.save(plcWithAllAttributes);
            plcRepository.deleteById(plc.getId());
            Optional<HardwareInformation> hardwareInformationInDb = hardwareInformationRepository.findById(plc.getHardwareInformation().getId());

            assertThat(hardwareInformationInDb).isNotPresent();
        }

        @Test @DisplayName("when PLC is deleted also curve and all its points are deleted")
        void deletesAlsoCurvePoints(){
            Plc plc = plcRepository.save(plcWithAllAttributes);
            plcRepository.deleteById(plc.getId());
            List<PointOfTorqueAndSpeed> points
                    = pointOfTorqueAndSpeedRepository.findAll();

            assertThat(points).isEmpty();
        }

        @Test @DisplayName("when PLC is deleted also tools are deleted")
        void deletesAlsoTools(){
            Plc plc = plcRepository.save(plcWithAllAttributes);
            entityManager.flush();

            plcRepository.deleteById(plc.getId());
            List<Tool> tools
                    = toolRepository.findAll();

            assertThat(tools).isEmpty();
        }

        @Test @DisplayName("when PLC is deleted also reference curves of all tools are deleted")
        void deleteAlsoReferenceCurvesOfTools(){
            Plc plc = plcRepository.save(plcWithAllAttributes);
            entityManager.flush();

            plcRepository.deleteById(plc.getId());
            List<Curve> curves
                    = curveRepository.findAll();

            assertThat(curves).isEmpty();
        }

        @Test @DisplayName("when PLC is deleted also connection is deleted")
        void deleteAlsoConnection(){
            Plc plc = plcRepository.save(plcWithAllAttributes);
            entityManager.flush();

            plcRepository.deleteById(plc.getId());
            List<Connection> connections
                    = connectionRepository.findAll();

            assertThat(connections).isEmpty();
        }
    }


    @Nested @DisplayName("CONSTRAINS") @DataJpaTest
    class Validation{
        @Nested @DisplayName("IP ADDRESS") @DataJpaTest
        class IpAddress{
            @Test @DisplayName("throws exception when ipAddress is \"null\"")
            void storePlcWithNullIpAddress(){
                Plc plc = new Plc();

                plcRepository.save(plc);
                assertThrows(ConstraintViolationException.class,() -> entityManager.flush());
            }

            @Test @DisplayName("throws exception when ipAddress is empty string")
            void throwsErrorWhenIpAddressIsEmpty(){
                Plc plc = Plc.builder().ipAddress("").name("name").build();

                plcRepository.save(plc);
                assertThrows(ConstraintViolationException.class, () -> entityManager.flush());
            }

            @Test @DisplayName("throws exception when ipAddress is in incorrect format")
            void throwsErrorWhenIpAddressIsInIncorrectFormat(){
                Plc plc = Plc.builder().ipAddress("192.168.0.300").name("name").build();

                plcRepository.save(plc);
                assertThrows(ConstraintViolationException.class, () -> entityManager.flush());
            }

            @Test @DisplayName("throws exception when two PLCs with same IP address are stored into database")
            void ipAddressIsUnique(){
                Plc plc1 = Plc.builder().ipAddress("192.168.0.1").name("name1").build();
                Plc plc2 = Plc.builder().ipAddress("192.168.0.1").name("name2").build();

                plcRepository.save(plc1);
                plcRepository.save(plc2);
                assertThrows(PersistenceException.class,() -> entityManager.flush());
            }


        }

        @Nested @DisplayName("NAME") @DataJpaTest
        class Name{
            @Test @DisplayName("throws exception when name is \"null\"")
            void throwsErrorWhenNameIsNull(){
                Plc plc = Plc.builder().ipAddress("192.168.0.1").build();

                plcRepository.save(plc);
                assertThrows(ConstraintViolationException.class, () -> entityManager.flush());
            }

            @Test @DisplayName("throws exception when name is empty string")
            void throwsErrorWhenNameIsEmpty(){
                Plc plc = Plc.builder().ipAddress("192.168.0.1").name("").build();

                plcRepository.save(plc);
                assertThrows(ConstraintViolationException.class, () -> entityManager.flush());
            }

            @Test @DisplayName("throws exception when two PLCs with same name are stored into database")
            void nameIsUnique(){
                Plc plc1 = Plc.builder().ipAddress("192.168.0.1").name("sameName").build();
                Plc plc2 = Plc.builder().ipAddress("192.168.0.2").name("sameName").build();

                plcRepository.save(plc1);
                plcRepository.save(plc2);
                assertThrows(PersistenceException.class,() -> entityManager.flush());
            }

        }
    }




}
