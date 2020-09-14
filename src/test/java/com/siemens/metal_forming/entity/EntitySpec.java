package com.siemens.metal_forming.entity;

import com.siemens.metal_forming.TestedClass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.fail;

@Setter @Getter @FieldDefaults(level = AccessLevel.PRIVATE) @AllArgsConstructor
abstract class EntitySpec implements TestedClass {
    @Nested @DisplayName("GENERAL ENTITY")
    class GeneralEntity{
        @Test @DisplayName("has all attributes private")
        void hasAllAttributesPrivate(){
            SoftAssertions softAssertions = new SoftAssertions();
            for (Field f: getTestedClass().getDeclaredFields()){
                softAssertions.assertThat(f.getModifiers()& Modifier.PRIVATE).as(f.getName()+" is not private").isEqualTo(Modifier.PRIVATE);
            }
            softAssertions.assertAll();
        }

        @Test @DisplayName("has default (no argument) constructor")
        void hasDefaultConstructor() {
            try {
                getTestedClass().getDeclaredConstructor();
            } catch (NoSuchMethodException e) {
                fail("doesn't have default (no argument) constructor");
            }
        }
    }

}
