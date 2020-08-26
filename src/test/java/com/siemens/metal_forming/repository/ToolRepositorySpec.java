package com.siemens.metal_forming.repository;

import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.enumerated.ToolStatusType;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("<= TOOL REPOSITORY SPECIFICATION =>")
public class ToolRepositorySpec {
    @Autowired
    ToolRepository toolRepository;
    @Autowired
    PlcRepository plcRepository;
    @Autowired
    TestEntityManager entityManager;

    @DataJpaTest
    @Nested @DisplayName("METHODS WITH SQL SPECIFICATION")
    class MethodsWithSqlSpecification{
        Plc plc1;
        Plc plc2;
        @BeforeEach
        void initializeForMethodsWithSqlSpecification(){
            //Database setup
            plc1 = Plc.builder().ipAddress("192.168.0.1").name("name").build();
            for(int i = 0;i<4;i++){
                plc1.getTools().add(Tool.builder().toolId(i).toolStatus(ToolStatusType.AUTODETECTED).automaticMonitoring(false).build());
            }
            plc2 = Plc.builder().ipAddress("192.168.0.2").name("name2").build();
            for(int i = 0;i<5;i++){
                plc2.getTools().add(Tool.builder().toolId(i).toolStatus(ToolStatusType.AUTODETECTED).automaticMonitoring(false).build());
            }
            plc1 = plcRepository.save(plc1);
            plc2 = plcRepository.save(plc2);
            entityManager.flush();
        }
        @Test @DisplayName("find all by plc id")
        void findAllByPlcId(){
            assertThat(toolRepository.findAllByPlcId(plc1.getId()).size()).isEqualTo(plc1.getTools().size());
        }

        @Test @DisplayName("find tool by plc id and tool id")
        void findToolByPlcIdAndToolId(){
            Tool randomTool = plc1.getTools().stream().findFirst().get();
            Tool foundTool = toolRepository.findByPlcIdAndToolId(plc1.getId(),randomTool.getToolId()).get();

            assertThat(foundTool.getToolId()).isEqualTo(randomTool.getToolId());
        }
    }

}
