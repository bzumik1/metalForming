package com.siemens.metal_forming.testBuilders;

import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.entity.CurvePoint;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestCurveBuilder{
    List<CurvePoint> points = new ArrayList<>();




    public TestCurveBuilder points(List<CurvePoint> points){
        this.points = points;
        return this;
    }

    public TestCurveBuilder points(CurvePoint... points){
        this.points = Arrays.asList(points);
        return this;
    }

    public TestCurveBuilder addPoint(float torque, float speed){
        this.points.add(new CurvePoint(torque,speed));
        return this;
    }

    public TestCurveBuilder randomPoints(int numberOfRandomPoints){
        this.points = generateRandomPoints(numberOfRandomPoints);
        return this;
    }

    public Curve build(){
        return new Curve(points);
    }

    private List<CurvePoint> generateRandomPoints(int numberOfPoints){
        return Stream.generate(() -> new CurvePoint((float)Math.random(),(float)Math.random()))
                .limit(numberOfPoints)
                .collect(Collectors.toList());
    }
}
