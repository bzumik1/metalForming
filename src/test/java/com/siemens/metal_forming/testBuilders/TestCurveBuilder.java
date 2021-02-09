package com.siemens.metal_forming.testBuilders;

import com.siemens.metal_forming.domain.Curve;
import com.siemens.metal_forming.domain.PointOfTorqueAndSpeed;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestCurveBuilder{
    List<PointOfTorqueAndSpeed> points = new ArrayList<>();




    public TestCurveBuilder points(List<PointOfTorqueAndSpeed> points){
        this.points = points;
        return this;
    }

    public TestCurveBuilder points(PointOfTorqueAndSpeed... points){
        this.points = Arrays.asList(points);
        return this;
    }

    public TestCurveBuilder addPoint(float torque, float speed){
        this.points.add(new PointOfTorqueAndSpeed(torque,speed));
        return this;
    }

    public TestCurveBuilder randomPoints(int numberOfRandomPoints){
        this.points = generateRandomPoints(numberOfRandomPoints);
        return this;
    }

    public Curve build(){
        return new Curve(points);
    }

    private List<PointOfTorqueAndSpeed> generateRandomPoints(int numberOfPoints){
        return Stream.generate(() -> new PointOfTorqueAndSpeed((float)Math.random(),(float)Math.random()))
                .limit(numberOfPoints)
                .collect(Collectors.toList());
    }
}
