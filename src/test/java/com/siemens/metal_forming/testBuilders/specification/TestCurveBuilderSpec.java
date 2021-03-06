package com.siemens.metal_forming.testBuilders.specification;

import com.siemens.metal_forming.domain.Curve;
import com.siemens.metal_forming.domain.PointOfTorqueAndSpeed;
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

        @Test @DisplayName("adds given points")
        void addsGivenPoints(){
            Curve testCurve = testCurveBuilder
                    .points(
                            new PointOfTorqueAndSpeed(1.1F,1.1F),
                            new PointOfTorqueAndSpeed(2.2F,2.2F))
                    .build();

            assertThat(testCurve.getPoints().size()).as("all points were added").isEqualTo(2);
        }

        @Test @DisplayName("adds point to curve")
        void addsPointToCurve(){
            Curve testCurve = testCurveBuilder.addPoint(10f,10f).addPoint(5f,5f).build();

            assertThat(testCurve.getPoints()).containsExactly(new PointOfTorqueAndSpeed(10f,10f), new PointOfTorqueAndSpeed(5f,5f));
        }
    }

    @Nested @DisplayName("DIRECT SETTING METHODS")
    class DirectSettingMethods{
        @Test @DisplayName("sets points of new curve")
        void setsPointsOfNewCurve(){
            List<PointOfTorqueAndSpeed> testPoints = Stream.generate(() -> new PointOfTorqueAndSpeed(1.1F,1.1F))
                    .limit(5)
                    .collect(Collectors.toList());
            Curve testCurve = testCurveBuilder.points(testPoints).build();

            assertThat(testCurve.getPoints().size()).isEqualTo(5);
        }
    }
}
