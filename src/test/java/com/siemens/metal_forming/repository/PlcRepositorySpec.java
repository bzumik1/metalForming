package com.siemens.metal_forming.repository;

import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.entity.PointOfTorqueAndSpeed;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.enumerated.ToolStatusType;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.PersistenceException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DisplayName("<= Plc Repository Specification =>")
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
    private PointOfTorqueAndSpeedRepository pointOfTorqueAndSpeedRepository;

    private Plc plcWithAllAttributes;


    @BeforeEach
    void initializePlc(){
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
            for(int j=0; i<100; i++){
                referenceCurve.getPoints().add(new PointOfTorqueAndSpeed((float)Math.random(),(float)Math.random()));
            }
            tool.setReferenceCurve(referenceCurve);
            tools.add(tool);
        }

        plcWithAllAttributes.setMotorCurve(motorCurve);
        plcWithAllAttributes.setIpAddress("192.168.1.1");
        plcWithAllAttributes.setTools(tools);
    }

    @Nested @DisplayName("cascade") @DataJpaTest
    class cascade{


        @Test @DisplayName("when PLC is deleted also curve is deleted")
        void deletesAlsoCurve(){
            Plc plc = plcRepository.save(plcWithAllAttributes);
            plcRepository.deleteById(plc.getId());
            Optional<Curve> curveInDb = curveRepository.findById(plc.getMotorCurve().getId());

            assertThat(curveInDb).isNotPresent();
        }

        @Test @DisplayName("when PLC is deleted also curve and all its points are deleted")
        void deletesAlsoCurvePoints(){
            Plc plc = plcRepository.save(plcWithAllAttributes);
            plcRepository.deleteById(plc.getId());
            List<PointOfTorqueAndSpeed> points
                    = pointOfTorqueAndSpeedRepository.findAll();

            assertThat(points).isEmpty();
        }

        @Test @DisplayName("when PLC is deleted also tools aredeleted")
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
    }



    @Nested @DisplayName("constrains") @DataJpaTest
    class validation{
        @Test @DisplayName("PLC with address \"null\" can not be stored")
        void storePlcWithNullIpAddress(){
            Plc plc = new Plc();
            assertThrows(DataIntegrityViolationException.class,() -> plcRepository.save(plc));
        }

        @Test @DisplayName("two PLCs with same IP Address can not be stored into database")
        void ipAddressIsUnique(){
            Plc plc1 = new Plc();
            plc1.setIpAddress("192.168.1.7");
            Plc plc2 = new Plc();
            plc2.setIpAddress("192.168.1.7");

            plcRepository.save(plc1);
            plcRepository.save(plc2);
            assertThrows(PersistenceException.class,() -> entityManager.flush());
        }
    }




}
