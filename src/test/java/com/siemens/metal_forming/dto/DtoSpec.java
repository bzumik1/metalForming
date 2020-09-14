package com.siemens.metal_forming.dto;

import com.siemens.metal_forming.TestedClass;
import com.siemens.metal_forming.entity.log.Log;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public abstract class DtoSpec implements TestedClass {
    @Test
    @DisplayName("has all attributes private")
    void hasAllAttributesPrivate(){
        SoftAssertions softAssertions = new SoftAssertions();
        for (Field f: getTestedClass().getDeclaredFields()){
            softAssertions.assertThat(f.getModifiers()& Modifier.PRIVATE).as(f.getName()+" is not private").isEqualTo(Modifier.PRIVATE);
        }
        softAssertions.assertAll();
    }

    @Test
    @DisplayName("has all attributes final")
    void hasAllAttributesFinal(){
        SoftAssertions softAssertions = new SoftAssertions();
        for (Field f: getTestedClass().getDeclaredFields()){
            softAssertions.assertThat(f.getModifiers()& Modifier.FINAL).as(f.getName()+" is not final").isEqualTo(Modifier.FINAL);
        }
        softAssertions.assertAll();
    }
}
