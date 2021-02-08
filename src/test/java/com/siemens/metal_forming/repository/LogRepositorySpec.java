package com.siemens.metal_forming.repository;

import com.siemens.metal_forming.entity.log.Log;
import com.siemens.metal_forming.entity.log.PlcInfo;
import com.siemens.metal_forming.entity.log.ToolInfo;
import com.siemens.metal_forming.enumerated.StopReactionType;
import com.siemens.metal_forming.testBuilders.TestLogBuilder;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("<= LOG REPOSITORY SPECIFICATION =>")
@DataJpaTest
class LogRepositorySpec {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    LogRepository logRepository;

    @Autowired
    CollisionPointsRepository collisionPointsRepository;

    @Autowired
    PlcInfoRepository plcInfoRepository;

    @Autowired
    ToolInfoRepository toolInfoRepository;

    TestLogBuilder testLogBuilder;

    @BeforeEach
    void initialize(){
        testLogBuilder = new TestLogBuilder();
    }



    @Nested @DisplayName("CASCADE") @DataJpaTest
    class Cascade{
        @Test @DisplayName("deletes collision points when log is deleted")
        void deletesCollisionPointsWhenLogIsDeleted(){
            Log testLog = testLogBuilder.randomCollisionPoints(3).build();

            Long id = logRepository.save(testLog).getId();
            logRepository.deleteById(id);

            assertThat(collisionPointsRepository.findAll()).isEmpty();
        }

        @Test @DisplayName("deletes toolInformation when log is deleted")
        void deletesToolInformationWhenLogIsDeleted(){
            ToolInfo toolInfo = ToolInfo.builder().nameFromPlc("toolName").toolNumber(1).toolId(1L).stopReaction(StopReactionType.IMMEDIATE).build();
            Log testLog = testLogBuilder.toolInformation(toolInfo).build();

            Long id = logRepository.save(testLog).getId();
            logRepository.deleteById(id);

            assertThat(toolInfoRepository.findAll()).isEmpty();
        }

        @Test @DisplayName("deletes plcInformation when log is deleted")
        void deletesPlcInformationWhenLogIsDeleted(){
            PlcInfo plcInfo = PlcInfo.builder().name("plcName").serialNumber("SN 001").firmwareNumber("FW 001").ipAddress("192.168.0.1").build();
            Log testLog = testLogBuilder.plcInformation(plcInfo).build();

            Long id = logRepository.save(testLog).getId();
            logRepository.deleteById(id);

            assertThat(plcInfoRepository.findAll()).isEmpty();
        }
    }

    @Nested @DisplayName("METHODS") @DataJpaTest
    class Methods{
        @Nested @DisplayName("FIND ID BY IDS IN") @DataJpaTest
        class FindIdByIdsIn{
            @Test @DisplayName("returns ids which are in database")
            void returnsIdsWhichAreInDatabase(){
                Log log1 = testLogBuilder.build();
                Log log2 = testLogBuilder.build();

                List<Long> idsInDb = logRepository.saveAll(List.of(log1,log2)).stream().map(Log::getId).collect(Collectors.toList());
                List<Long> ids = new ArrayList<>(idsInDb);
                ids.add(-1L);

                assertThat(logRepository.findIdsByIdsIn(ids).containsAll(idsInDb));
            }
        }

    }



}
