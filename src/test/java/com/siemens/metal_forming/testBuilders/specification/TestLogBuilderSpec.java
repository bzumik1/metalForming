package com.siemens.metal_forming.testBuilders.specification;

import com.siemens.metal_forming.entity.*;
import com.siemens.metal_forming.entity.log.CollisionPoint;
import com.siemens.metal_forming.entity.log.Log;
import com.siemens.metal_forming.entity.log.PlcInfo;
import com.siemens.metal_forming.entity.log.ToolInfo;
import com.siemens.metal_forming.enumerated.ConnectionStatus;
import com.siemens.metal_forming.testBuilders.TestLogBuilder;
import com.siemens.metal_forming.testBuilders.TestPlcBuilder;
import com.siemens.metal_forming.testBuilders.TestToolBuilder;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class TestLogBuilderSpec extends TestBuilderSpec{
    TestLogBuilderSpec() {
        super(Log.class, TestLogBuilder.class);
    }

    TestLogBuilder testLogBuilder;

    @BeforeEach
    void initialize(){
        testLogBuilder = new TestLogBuilder();
    }

    @Nested @DisplayName("SPECIAL METHODS")
    class SpecialMethods{
        @Test @DisplayName("adds random measuredCurve for new log")
        void addsRandomMeasuredCurveForNewLog(){
            Log testLog = testLogBuilder.randomMeasuredCurve(100).build();

            assertThat(testLog.getMeasuredCurve().getPoints()).hasSize(100);
        }

        @Test @DisplayName("adds random motorCurve for new log")
        void addsRandomMotorCurveForNewLog(){
            Log testLog = testLogBuilder.randomMotorCurve(100).build();

            assertThat(testLog.getMotorCurve().getPoints()).hasSize(100);
        }

        @Test @DisplayName("adds random referenceCurve for new log")
        void addsRandomReferenceCurveForNewLog(){
            Log testLog = testLogBuilder.randomReferenceCurve(100).build();

            assertThat(testLog.getReferenceCurve().getPoints()).hasSize(100);
        }

        @Test @DisplayName("adds random collisionPoints for new log")
        void addsRandomCollisionPointsForNewLog(){
            Log testLog = testLogBuilder.randomCollisionPoints(20).build();

            assertThat(testLog.getCollisionPoints()).hasSize(20);
        }
    }


    @Nested @DisplayName("DIRECT SETTING METHODS")
    class DirectSettingMethods{
        @Test @DisplayName("sets id of new log")
        void setsIdOfNewLog(){
            Log testLog = testLogBuilder.id(99L).build();

            assertThat(testLog.getId()).isEqualTo(99L);
        }

        @Test @DisplayName("sets createdOn of new log")
        void setsCreatedOnOfNewLog(){
            Log testLog = testLogBuilder.createdOn(new Timestamp(1)).build();

            assertThat(testLog.getCreatedOn()).isEqualTo(new Timestamp(1));
        }

        @Test @DisplayName("sets measuredCurve of new log")
        void setsMeasuredCurveOfNewLog(){
            Curve measuredCurve = Curve.builder().id(1L).build();

            Log testLog = testLogBuilder.measuredCurve(measuredCurve).build();

            assertThat(testLog.getMeasuredCurve().getId()).isEqualTo(1L);
        }

        @Test @DisplayName("sets motorCurve of new log")
        void setsMotorCurveOfNewLog(){
            Curve motorCurve = Curve.builder().id(1L).build();

            Log testLog = testLogBuilder.motorCurve(motorCurve).build();

            assertThat(testLog.getMotorCurve().getId()).isEqualTo(1L);
        }

        @Test @DisplayName("sets referenceCurve of new log")
        void setsReferenceCurveOfNewLog(){
            Curve referenceCurve = Curve.builder().id(1L).build();

            Log testLog = testLogBuilder.referenceCurve(referenceCurve).build();

            assertThat(testLog.getReferenceCurve().getId()).isEqualTo(1L);
        }

        @Test @DisplayName("sets collisionPoints of new log")
        void setsCollisionPointsOfNewLog(){
            Set<CollisionPoint> collisionPoints = Set.of(new CollisionPoint(1.1F,1.1F), new CollisionPoint(2.2F,2.2F));

            Log testLog = testLogBuilder.collisionPoints(collisionPoints).build();

            assertThat(testLog.getCollisionPoints()).isEqualTo(collisionPoints);
        }

        @Test @DisplayName("sets plcInformation of new log")
        void setsPlcInformationOfNewLog(){
            PlcInfo plcInfo = PlcInfo.builder().name("plcName").build();

            Log testLog = testLogBuilder.plcInformation(plcInfo).build();

            assertThat(testLog.getPlcInformation()).isEqualTo(plcInfo);
        }

        @Test @DisplayName("sets toolInformation of new log")
        void setsToolInformationOfNewLog(){
            ToolInfo toolInfo = ToolInfo.builder().name("toolName").build();

            Log testLog = testLogBuilder.toolInformation(toolInfo).build();

            assertThat(testLog.getToolInformation()).isEqualTo(toolInfo);
        }

        @Test @DisplayName("sets comment of new log")
        void setsCommentOfNewLog(){
            Log testLog = testLogBuilder.comment("new comment").build();

            assertThat(testLog.getComment()).isEqualTo("new comment");
        }

    }
}
