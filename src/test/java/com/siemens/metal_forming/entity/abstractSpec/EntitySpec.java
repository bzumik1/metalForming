package com.siemens.metal_forming.entity.abstractSpec;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.fail;

@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class EntitySpec{
    protected Class<?> testedClass;

    public EntitySpec(Class<?> testedClass){
        this.testedClass = testedClass;
    }

    @Nested @DisplayName("GENERAL ENTITY")
    class GeneralEntity{
        @Test @DisplayName("has all attributes private")
        void hasAllAttributesPrivate(){
            SoftAssertions softAssertions = new SoftAssertions();
            for (Field f: testedClass.getDeclaredFields()){
                softAssertions.assertThat(f.getModifiers()& Modifier.PRIVATE).as(f.getName()+" is not private").isEqualTo(Modifier.PRIVATE);
            }
            softAssertions.assertAll();
        }

        @Test @DisplayName("has default (no argument) constructor")
        void hasDefaultConstructor() {
            try {
                testedClass.getDeclaredConstructor();
            } catch (NoSuchMethodException e) {
                fail("doesn't have default (no argument) constructor");
            }
        }
    }

}
