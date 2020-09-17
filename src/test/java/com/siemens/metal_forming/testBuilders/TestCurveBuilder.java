package com.siemens.metal_forming.testBuilders;

import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.entity.CurvePoint;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestCurveBuilder{
    Long id;
    List<CurvePoint> points = generateRandomPoints(100);



    public TestCurveBuilder id(Long id){
        this.id = id;
        return this;
    }

    public TestCurveBuilder points(List<CurvePoint> points){
        this.points = points;
        return this;
    }

    public TestCurveBuilder randomPoints(int numberOfRandomPoints){
        this.points = generateRandomPoints(numberOfRandomPoints);
        return this;
    }

    public Curve build(){
        return new Curve(id,points);
    }

    private List<CurvePoint> generateRandomPoints(int numberOfPoints){
        return Stream.generate(() -> new CurvePoint((float)Math.random(),(float)Math.random()))
                .limit(numberOfPoints)
                .collect(Collectors.toList());
    }
}
