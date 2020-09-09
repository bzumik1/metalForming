package com.siemens.metal_forming.repository;

import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.entity.CurvePoint;
import com.siemens.metal_forming.entity.log.CollisionPoint;
import com.siemens.metal_forming.entity.log.Log;
import com.siemens.metal_forming.entity.log.PlcInfo;
import com.siemens.metal_forming.entity.log.ToolInfo;
import com.siemens.metal_forming.enumerated.StopReactionType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("<= PLC REPOSITORY SPECIFICATION =>")
@DataJpaTest
class LogRepositorySpec {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    LogRepository logRepository;

    @Autowired
    CollisionPointsRepository collisionPointsRepository;

    @Autowired
    CurveRepository curveRepository;

    @Autowired
    PlcInfoRepository plcInfoRepository;

    @Autowired
    ToolInfoRepository toolInfoRepository;



    @Nested @DisplayName("CASCADE") @DataJpaTest
    class Cascade{
        ArrayList<Log> logs;

        @BeforeEach
        void initializeForCascade(){
            logs = new ArrayList<>();
            for(int l=0;l<2;l++) {
                Curve curve = new Curve();
                Curve motorCurve = new Curve();
                Curve referenceCurve = new Curve();
                for (int i = 0; i < 100; i++) {
                    curve.getPoints().add(new CurvePoint((float) Math.random(), (float) Math.random()));
                    motorCurve.getPoints().add(new CurvePoint((float) Math.random(), (float) Math.random()));
                    referenceCurve.getPoints().add(new CurvePoint((float) Math.random(), (float) Math.random()));
                }

                PlcInfo plcInfo = PlcInfo.builder().firmwareNumber("FW"+l).serialNumber("SN"+l).ipAddress("192.167.0."+l).name("plc"+l).build();
                ToolInfo toolInfo = ToolInfo.builder().toolNumber(l).name("tool"+l).stopReaction(StopReactionType.IMMEDIATE).build();

                Set<CollisionPoint> collisionPoints = new HashSet<>();
                collisionPoints.add(new CollisionPoint((float) Math.random(), (float) Math.random()));
                collisionPoints.add(new CollisionPoint((float) Math.random(), (float) Math.random()));

                Log log = Log.builder()
                        .measuredCurve(curve)
                        .referenceCurve(referenceCurve)
                        .collisionPoints(collisionPoints)
                        .plcInformation(plcInfo)
                        .toolInformation(toolInfo)
                        .comment("comment"+l)
                        .build();
                log.setMotorCurve(motorCurve);

                logs.add(log);
            }
        }

        @Test @DisplayName("saves log with all attributes correctly to database")
        void savesLogWithAllAttributesCorrectlyToDatabase(){
            logRepository.save(logs.get(0));
            entityManager.flush();
        }

        @Test @DisplayName("deletes collision points when log is deleted")
        void deletesCollisionPointsWhenLogIsDeleted(){
            Long id = logRepository.save(logs.get(0)).getId();
            logRepository.deleteById(id);

            assertThat(collisionPointsRepository.findAll()).isEmpty();
        }

        @Nested @DisplayName("ATTRIBUTES ARE NOT DELETED CASCADE") @DataJpaTest
        class AttributesAreNotDeletedCascade{
            @Test @DisplayName("does not delete measured curve when another log points to it")
            void doesNotDeleteMeasuredCurveWhenAnotherLogPointsToIt(){
                Log log1 = logs.get(0);
                Log log2 = logs.get(1).toBuilder().measuredCurve(log1.getMeasuredCurve()).build();
                log1 = logRepository.save(log1);
                log2 = logRepository.save(log2);

                logRepository.deleteById(log1.getId());

                assertThat(log1.getMeasuredCurve().getId()).as("curve ids must be same").isEqualTo(log2.getMeasuredCurve().getId());
                assertThat(curveRepository.findById(log1.getMeasuredCurve().getId())).isPresent();
            }

            @Test @DisplayName("does not delete reference curve when another log points to it")
            void doesNotDeleteReferenceCurveWhenAnotherLogPointsToIt(){
                Log log1 = logs.get(0);
                Log log2 = logs.get(1).toBuilder().referenceCurve(log1.getReferenceCurve()).build();
                log1 = logRepository.save(log1);
                log2 = logRepository.save(log2);

                logRepository.deleteById(log1.getId());

                assertThat(log1.getReferenceCurve().getId()).as("curve ids must be same").isEqualTo(log2.getReferenceCurve().getId());
                assertThat(curveRepository.findById(log1.getReferenceCurve().getId())).isPresent();
            }


            @Test @DisplayName("does not delete motor curve when another log points to it")
            void doesNotDeleteMotorCurveWhenAnotherLogPointsToIt(){
                Log log1 = logs.get(0);
                Log log2 = logs.get(1).toBuilder().motorCurve(log1.getMotorCurve()).build();
                log1 = logRepository.save(log1);
                log2 = logRepository.save(log2);

                logRepository.deleteById(log1.getId());

                assertThat(log1.getMotorCurve().getId()).as("curve ids must be same").isEqualTo(log2.getMotorCurve().getId());
                assertThat(curveRepository.findById(log1.getMotorCurve().getId())).isPresent();
            }

            @Test @DisplayName("does not delete plc information when another log points to it")
            void doesNotDeletePlcInformationWhenAnotherLogPointsToIt(){
                Log log1 = logs.get(0);
                Log log2 = logs.get(1).toBuilder().plcInformation(log1.getPlcInformation()).build();
                log1 = logRepository.save(log1);
                log2 = logRepository.save(log2);

                logRepository.deleteById(log1.getId());

                assertThat(log1.getPlcInformation().getId()).as("plc information ids must be same").isEqualTo(log2.getPlcInformation().getId());
                assertThat(plcInfoRepository.findById(log1.getPlcInformation().getId())).isPresent();
            }

            @Test @DisplayName("does not delete tool information when another log points to it")
            void doesNotDeleteToolInformationWhenAnotherLogPointsToIt(){
                Log log1 = logs.get(0);
                Log log2 = logs.get(1).toBuilder().toolInformation(log1.getToolInformation()).build();
                log1 = logRepository.save(log1);
                log2 = logRepository.save(log2);

                logRepository.deleteById(log1.getId());

                assertThat(log1.getToolInformation().getId()).as("tool information ids must be same").isEqualTo(log2.getToolInformation().getId());
                assertThat(toolInfoRepository.findById(log1.getToolInformation().getId())).isPresent();
            }
        }

        @Nested @DisplayName("ORPHANS ARE REMOVED") @DataJpaTest
        class OrphansAreRemoved{
            @Test @DisplayName("deletes measured curve when there aren't any logs pointing to it")
            void deletesMeasuredCurveWhenThereAreNotAnyLogsPointingToIt(){
                Log log1 = logs.get(0);

                log1 = logRepository.save(log1);
                entityManager.flush();


                log1.getMotorCurve().getLogsWithMotorCurve().remove(log1);
                entityManager.flush();
                //logRepository.deleteById(log1.getId());

                entityManager.clear();

                List<Curve> curves = curveRepository.findAll();
                Optional<Curve> curveInDb = curveRepository.findById(log1.getMotorCurve().getId());
                assertThat(curveInDb).isNotPresent();
            }

            @Test @DisplayName("does not delete reference curve when another log points to it") @Disabled
            void doesNotDeleteReferenceCurveWhenAnotherLogPointsToIt(){
                Log log1 = logs.get(0);
                Log log2 = logs.get(1).toBuilder().referenceCurve(log1.getReferenceCurve()).build();
                log1 = logRepository.save(log1);
                log2 = logRepository.save(log2);

                logRepository.deleteById(log1.getId());

                assertThat(log1.getReferenceCurve().getId()).as("curve ids must be same").isEqualTo(log2.getReferenceCurve().getId());
                assertThat(curveRepository.findById(log1.getReferenceCurve().getId())).isPresent();
            }


            @Test @DisplayName("does not delete motor curve when another log points to it") @Disabled
            void doesNotDeleteMotorCurveWhenAnotherLogPointsToIt(){
                Log log1 = logs.get(0);
                Log log2 = logs.get(1).toBuilder().motorCurve(log1.getMotorCurve()).build();
                log1 = logRepository.save(log1);
                log2 = logRepository.save(log2);

                logRepository.deleteById(log1.getId());

                assertThat(log1.getMotorCurve().getId()).as("curve ids must be same").isEqualTo(log2.getMotorCurve().getId());
                assertThat(curveRepository.findById(log1.getMotorCurve().getId())).isPresent();
            }

            @Test @DisplayName("does not delete plc information when another log points to it") @Disabled
            void doesNotDeletePlcInformationWhenAnotherLogPointsToIt(){
                Log log1 = logs.get(0);
                Log log2 = logs.get(1).toBuilder().plcInformation(log1.getPlcInformation()).build();
                log1 = logRepository.save(log1);
                log2 = logRepository.save(log2);

                logRepository.deleteById(log1.getId());

                assertThat(log1.getPlcInformation().getId()).as("plc information ids must be same").isEqualTo(log2.getPlcInformation().getId());
                assertThat(plcInfoRepository.findById(log1.getPlcInformation().getId())).isPresent();
            }

            @Test @DisplayName("does not delete tool information when another log points to it") @Disabled
            void doesNotDeleteToolInformationWhenAnotherLogPointsToIt(){
                Log log1 = logs.get(0);
                Log log2 = logs.get(1).toBuilder().toolInformation(log1.getToolInformation()).build();
                log1 = logRepository.save(log1);
                log2 = logRepository.save(log2);

                logRepository.deleteById(log1.getId());

                assertThat(log1.getToolInformation().getId()).as("tool information ids must be same").isEqualTo(log2.getToolInformation().getId());
                assertThat(toolInfoRepository.findById(log1.getToolInformation().getId())).isPresent();
            }
        }

        @Nested @DisplayName("ANOTHER ENTITIES ARE NOT DUPLICATED ON CREATION") @DataJpaTest
        class AnotherEntitiesAreNotDuplicatedOnCreation{
            @Test @DisplayName("does not duplicate plc information when already exists")
            void doesNotDuplicatePlcInformationWhenAlreadyExists(){
                Log log1 = logs.get(0);
                Log log2 = logs.get(1).toBuilder().plcInformation(log1.getPlcInformation().toBuilder().build()).build();
                Log log3 = log2.toBuilder().build();
                logRepository.save(log1);


                Long startTimeInMilliseconds = System.currentTimeMillis();
                ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matchingAll()
                        .withIgnorePaths("id");

                Example<PlcInfo> plcInfoExample = Example.of(log2.getPlcInformation(),ignoringExampleMatcher);
                Example<ToolInfo> toolInfoExample = Example.of(log2.getToolInformation(),ignoringExampleMatcher);
                for(int i=0;i<2;i++) {
                    entityManager.clear();
                    Optional<PlcInfo> plcInfoInDb = plcInfoRepository.findOne(plcInfoExample);
                    Optional<ToolInfo> toolInfoInDb = toolInfoRepository.findOne(toolInfoExample);
                    Optional<Curve> motorCurveInDb = curveRepository.findById((long)(i+1000));
                    Optional<Curve> referenceCurveInDb = curveRepository.findById((long)(i+1001));

                    Log.LogBuilder log2Builder = log2.toBuilder();
                    plcInfoInDb.ifPresent(log2Builder::plcInformation);
                    toolInfoInDb.ifPresent(log2Builder::toolInformation);
                    motorCurveInDb.ifPresent(log2Builder::motorCurve);
                    referenceCurveInDb.ifPresent(log2Builder::referenceCurve);
                    log2 = log2Builder.build();
                }

                Long endTimeInMilliseconds = System.currentTimeMillis();


                logRepository.save(log2);

                System.out.println("Time was: "+(endTimeInMilliseconds-startTimeInMilliseconds));

                //assertThat(plcInfoRepository.findAll().size()).isEqualTo(1);
            }


        }

    }




}
