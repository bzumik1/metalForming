package com.siemens.metal_forming.repository;

import com.siemens.metal_forming.domain.Curve;
import com.siemens.metal_forming.entity.*;
import com.siemens.metal_forming.enumerated.ToolStatusType;
import com.siemens.metal_forming.testBuilders.TestCurveBuilder;
import com.siemens.metal_forming.testBuilders.TestPlcBuilder;
import com.siemens.metal_forming.testBuilders.TestToolBuilder;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("<= TOOL REPOSITORY SPECIFICATION =>")
class ToolRepositorySpec {
    @Autowired
    ToolRepository toolRepository;

    @Autowired
    AbsoluteToleranceRepository absoluteToleranceRepository;

    @Autowired
    RelativeToleranceRepository relativeToleranceRepository;

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
        @Test @DisplayName("when tool is deleted also curve is deleted")
        void deleteAlsoReferenceCurveOfTool(){
            Curve referenceCurve = new TestCurveBuilder().randomPoints(10).build();
            Tool tool = new TestToolBuilder().toolNumber(5).referenceCurve(referenceCurve).build();
            Plc plc = new TestPlcBuilder().addTool(tool).build();

            plc = plcRepository.save(plc);
            plc.removeTool(tool);

            assertThat(toolRepository.findAll()).isEmpty();
        }

        @Test @DisplayName("when tool is deleted also absolute tolerance is deleted")
        void deleteAlsoAbsoluteTolerance(){
            Tool tool = new TestToolBuilder().toolNumber(5).tolerance(new AbsoluteTolerance(1f,1f)).build();
            Plc plc = new TestPlcBuilder().addTool(tool).build();

            plc = plcRepository.save(plc);
            plc.removeTool(tool);

            assertThat(absoluteToleranceRepository.findAll()).isEmpty();
        }

        @Test @DisplayName("when tool is deleted also relative tolerance is deleted")
        void deleteRelativeTolerance(){
            Tool tool = new TestToolBuilder().toolNumber(5).tolerance(new RelativeTolerance(10,10)).build();
            Plc plc = new TestPlcBuilder().addTool(tool).build();

            plc = plcRepository.save(plc);
            plc.removeTool(tool);

            assertThat(relativeToleranceRepository.findAll()).isEmpty();
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
