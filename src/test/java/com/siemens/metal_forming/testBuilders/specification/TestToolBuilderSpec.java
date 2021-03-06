package com.siemens.metal_forming.testBuilders.specification;

import com.siemens.metal_forming.domain.Curve;
import com.siemens.metal_forming.domain.PointOfTorqueAndSpeed;
import com.siemens.metal_forming.entity.*;
import com.siemens.metal_forming.enumerated.StopReactionType;
import com.siemens.metal_forming.enumerated.ToolStatusType;
import com.siemens.metal_forming.testBuilders.TestToolBuilder;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("<= TEST TOOL BUILDER SPECIFICATION =>")
public class TestToolBuilderSpec extends TestBuilderSpec{
    TestToolBuilder testToolBuilder;

    TestToolBuilderSpec() {
        super(Tool.class, TestToolBuilder.class);
    }

    @BeforeEach
    void initialize(){
        testToolBuilder = new TestToolBuilder();
    }

    @Nested
    @DisplayName("SPECIAL METHODS")
    class SpecialMethods{

    }

    @Nested @DisplayName("DIRECT SETTING METHODS")
    class DirectSettingMethods{
        @Test @DisplayName("sets id of new tool")
        void setsIdOfNewTool(){
            Tool testTool = testToolBuilder.id(99L).build();

            assertThat(testTool.getId()).isEqualTo(99L);
        }

        @Test @DisplayName("sets plc of new tool and add this tool to plc")
        void setsPlcOfNewTool(){
            Plc testPlc = Plc.builder().name("plcName").ipAddress("192.168.0.1").build();
            Tool testTool = testToolBuilder.plc(testPlc).build();

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(testTool.getPlc()).as("plc wasn't set for tool").isEqualTo(testPlc);
            softAssertions.assertThat(testTool.getPlc().getToolByToolNumber(testTool.getToolNumber())).as("tool wasn't added to plc").isEqualTo(testTool);
            softAssertions.assertAll();
        }

        @Test @DisplayName("sets toolNumber of new tool")
        void setsToolNumberOfNewTool(){
            Tool testTool = testToolBuilder.toolNumber(99).build();

            assertThat(testTool.getToolNumber()).isEqualTo(99);
        }

        @Test @DisplayName("sets nameFromPlc of new tool")
        void setsNameFromPlcOfNewTool(){
            Tool testTool = testToolBuilder.nameFromPlc("newName").build();

            assertThat(testTool.getNameFromPlc()).isEqualTo("newName");
        }

        @Test @DisplayName("sets nickName of new tool")
        void setsNickNameOfNewTool(){
            Tool testTool = testToolBuilder.nickName("nickName").build();

            assertThat(testTool.getNickName()).isEqualTo("nickName");
        }

        @Test @DisplayName("sets numberOfReferenceCycles of new tool")
        void setsNumberOfReferenceCyclesOfNewTool(){
            Tool testTool = testToolBuilder.numberOfReferenceCycles(1000).build();

            assertThat(testTool.getNumberOfReferenceCycles()).isEqualTo(1000);
        }

        @Test @DisplayName("sets calculateReferenceCurve of new tool")
        void setsCalculateReferenceCurveOfNewTool(){
            Tool testTool = testToolBuilder.calculateReferenceCurve(true).build();

            assertThat(testTool.getCalculateReferenceCurve()).isTrue();
        }

        @Test @DisplayName("sets tolerance of new tool")
        void setsToleranceOfNewTool(){
            Tool testTool = testToolBuilder.tolerance(new AbsoluteTolerance(1,1)).build();

            assertThat(testTool.getTolerance()).isEqualTo(new AbsoluteTolerance(1,1));
        }

        @Test @DisplayName("sets stopReaction of new tool")
        void setsStopReactionOfNewTool(){
            Tool testTool = testToolBuilder.stopReaction(StopReactionType.TOP_POSITION).build();

            assertThat(testTool.getStopReaction()).isEqualTo(StopReactionType.TOP_POSITION);
        }

        @Test @DisplayName("sets automaticMonitoring of new tool")
        void setsAutomaticMonitoringOfNewTool(){
            Tool testTool = testToolBuilder.automaticMonitoring(false).build();

            assertThat(testTool.getAutomaticMonitoring()).isEqualTo(false);
        }

        @Test @DisplayName("sets maxSpeedOperation of new tool")
        void setsMaxSpeedOperationOfNewTool(){
            Tool testTool = testToolBuilder.maxSpeedOperation(10000).build();

            assertThat(testTool.getMaxSpeedOperation()).isEqualTo(10000);
        }

        @Test @DisplayName("sets toolStatus of new tool")
        void setsToolStatusOfNewTool(){
            Tool testTool = testToolBuilder.toolStatus(ToolStatusType.AUTODETECTED).build();

            assertThat(testTool.getToolStatus()).isEqualTo(ToolStatusType.AUTODETECTED);
        }

        @Test @DisplayName("sets referenceCurve of new tool")
        void setsReferenceCurveOfNewTool(){
            Curve testCurve = Curve.builder()
                    .points(Stream.generate(() -> new PointOfTorqueAndSpeed(1.1F,1.1F)).limit(3).collect(Collectors.toList()))
                    .build();
            Tool testTool = testToolBuilder.referenceCurve(testCurve).build();

            assertThat(testTool.getReferenceCurve().getPoints().size()).isEqualTo(3);
        }
    }
}
