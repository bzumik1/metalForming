package com.siemens.metal_forming.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class CalculationStatus {
    int current = 0;
    final int total;

    public CalculationStatus(int total) {
        this.total = total;
    }

    public int increase(){
        current++;
        return current;
    }

    @Override
    public String toString(){
        return ""+current+"/"+total;
    }
}
