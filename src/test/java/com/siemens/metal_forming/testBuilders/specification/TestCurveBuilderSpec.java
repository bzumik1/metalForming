package com.siemens.metal_forming.testBuilders.specification;

import com.siemens.metal_forming.entity.*;
import com.siemens.metal_forming.testBuilders.TestCurveBuilder;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("<= TEST CURVE BUILDER SPECIFICATION =>")

public class TestCurveBuilderSpec extends TestBuilderSpec {
    TestCurveBuilder testCurveBuilder;

    TestCurveBuilderSpec() {
        super(Curve.class, TestCurveBuilder.class);
    }

    @BeforeEach
    void initialize(){
        testCurveBuilder = new TestCurveBuilder();
    }

    @Nested @DisplayName("SPECIAL METHODS")
    class SpecialMethods{
        @Test
        @DisplayName("adds given number of random points to new curve")
        void addsGivenNumberOfRandomPoints(){
            Curve testCurve = testCurveBuilder.randomPoints(3).build();

            assertThat(testCurve.getPoints().size()).isEqualTo(3);
        }
    }

    @Nested @DisplayName("DIRECT SETTING METHODS")
    class DirectSettingMethods{
        @Test @DisplayName("sets id of new curve")
        void setsIdOfNewCurve(){
            Curve testCurve = testCurveBuilder.id(99L).build();

            assertThat(testCurve.getId()).isEqualTo(99L);
        }

        @Test @DisplayName("sets points of new curve")
        void setsPointsOfNewCurve(){
            List<CurvePoint> testPoints = Stream.generate(() -> new CurvePoint(1.1F,1.1F))
                    .limit(5)
                    .collect(Collectors.toList());
            Curve testCurve = testCurveBuilder.points(testPoints).build();

            assertThat(testCurve.getPoints().size()).isEqualTo(5);
        }
    }
}
