package com.siemens.metal_forming.repository;

import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.enumerated.ToolStatusType;
import com.siemens.metal_forming.testBuilders.TestCurveBuilder;
import com.siemens.metal_forming.testBuilders.TestToolBuilder;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("<= TOOL REPOSITORY SPECIFICATION =>")
class ToolRepositorySpec {
    @Autowired
    ToolRepository toolRepository;

    @Autowired
    CurveRepository curveRepository;

    @Autowired
    PlcRepository plcRepository;

    @Autowired
    TestEntityManager entityManager;

    TestToolBuilder testToolBuilder;

    TestCurveBuilder testCurveBuilder;

    @BeforeEach
    void initialize(){
        testToolBuilder = new TestToolBuilder();
        testCurveBuilder = new TestCurveBuilder();
    }

    @Nested @DisplayName("CASCADE") @DataJpaTest
    class Cascade{
        @Test @DisplayName("when tool is deleted also curve is deleted") @Disabled("plc needs to be saved first")
        void deleteAlsoReferenceCurveOfTool(){
            Curve referenceCurve = testCurveBuilder.randomPoints(100).build();
            Tool testTool = testToolBuilder.referenceCurve(referenceCurve).build();

            testTool = toolRepository.save(testTool);
            toolRepository.deleteById(testTool.getId());

            assertThat(curveRepository.findAll()).isEmpty();
        }
    }

    @Nested @DisplayName("METHODS WITH SQL SPECIFICATION") @DataJpaTest
    class MethodsWithSqlSpecification{
        Plc plc1;
        Plc plc2;
        @BeforeEach
        void initializeForMethodsWithSqlSpecification(){
            //Database setup
            plc1 = Plc.builder().ipAddress("192.168.0.1").name("name").build();
            for(int i = 0;i<4;i++){
                plc1.addTool(Tool.builder().toolNumber(i).toolStatus(ToolStatusType.AUTODETECTED).automaticMonitoring(false).build());
            }
            plc2 = Plc.builder().ipAddress("192.168.0.2").name("name2").build();
            for(int i = 0;i<5;i++){
                plc2.addTool(Tool.builder().toolNumber(i).toolStatus(ToolStatusType.AUTODETECTED).automaticMonitoring(false).build());
            }
            plc1 = plcRepository.save(plc1);
            plc2 = plcRepository.save(plc2);
            entityManager.flush();
        }
        @Test @DisplayName("find all by plc id")
        void findAllByPlcId(){
            assertThat(toolRepository.findAllByPlcId(plc1.getId()).size()).isEqualTo(plc1.getTools().size());
        }

        @Test @DisplayName("find tool by plc id and toolNumber")
        void findToolByPlcIdAndToolId(){
            Tool randomTool = plc1.getTools().stream().findFirst().get();
            Tool foundTool = toolRepository.findByPlcIdAndToolNumber(plc1.getId(),randomTool.getToolNumber()).get();

            assertThat(foundTool.getToolNumber()).isEqualTo(randomTool.getToolNumber());
        }
    }

}
