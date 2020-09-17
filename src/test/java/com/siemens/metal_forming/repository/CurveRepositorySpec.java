package com.siemens.metal_forming.repository;

import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.enumerated.ToolStatusType;
import com.siemens.metal_forming.testBuilders.TestCurveBuilder;
import com.siemens.metal_forming.testBuilders.TestToolBuilder;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("<= CURVE REPOSITORY SPECIFICATION =>")
class CurveRepositorySpec {
    @Autowired
    CurveRepository curveRepository;

    @Autowired
    CurvePointRepository curvePointRepository;

    @Autowired
    TestEntityManager entityManager;

    TestCurveBuilder testCurveBuilder;

    @BeforeEach
    void initialize(){
        testCurveBuilder = new TestCurveBuilder();
    }

    @Nested @DisplayName("CASCADE") @DataJpaTest
    class Cascade{
        @Test @DisplayName("when curve is deleted also all its points are deleted") @Disabled("plc needs to be saved first")
        void deletesAlsoCurvePoints(){
//                Plc plc = plcRepository.save(plcWithAllAttributes);
//                plcRepository.deleteById(plc.getId());
//                List<CurvePoint> points
//                        = curvePointRepository.findAll();
//
//                assertThat(points).isEmpty();
        }
    }
}
