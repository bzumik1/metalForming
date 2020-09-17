package com.siemens.metal_forming.dto;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public abstract class DtoSpec{
    Class<?> testedClass;

    public DtoSpec(Class<?> testedClass){
        this.testedClass = testedClass;
    }

    @Test
    @DisplayName("has all attributes private")
    void hasAllAttributesPrivate(){
        SoftAssertions softAssertions = new SoftAssertions();
        for (Field f: testedClass.getDeclaredFields()){
            softAssertions.assertThat(f.getModifiers()& Modifier.PRIVATE).as(f.getName()+" is not private").isEqualTo(Modifier.PRIVATE);
        }
        softAssertions.assertAll();
    }

    @Test
    @DisplayName("has all attributes final")
    void hasAllAttributesFinal(){
        SoftAssertions softAssertions = new SoftAssertions();
        for (Field f: testedClass.getDeclaredFields()){
            softAssertions.assertThat(f.getModifiers()& Modifier.FINAL).as(f.getName()+" is not final").isEqualTo(Modifier.FINAL);
        }
        softAssertions.assertAll();
    }
}
