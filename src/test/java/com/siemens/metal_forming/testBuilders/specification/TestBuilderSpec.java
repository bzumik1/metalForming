package com.siemens.metal_forming.testBuilders.specification;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("independent") @Tag("testBuilder")
abstract class TestBuilderSpec {
    private final Class<?> originalClass;
    private final Class<?> builderClass;

    TestBuilderSpec(Class<?> originalClass, Class<?> builderClass){
        this.originalClass = originalClass;
        this.builderClass = builderClass;
    }
    @Nested @DisplayName("TEST BUILDER")
    class TestBuilder{
        @Test @DisplayName("has same attributes as class it is building (more is also valid)")
        void hasSameAttributesAsClassItIsBuilding(){
            Field[] fieldsOfPlc = originalClass.getDeclaredFields();
            Field[] fieldsOfTestPlcBuilder = builderClass.getDeclaredFields();

            String missingAttributes = Arrays.stream(fieldsOfPlc)
                    .filter(plcField -> Arrays.stream(fieldsOfTestPlcBuilder).map(Field::getName).noneMatch(plcField.getName()::equals))
                    .map(Field::getName)
                    .collect(Collectors.joining(", "));

            assertThat(missingAttributes).as("missing attributes are: "+ missingAttributes).isEmpty();
        }

        @Test @DisplayName("has all fields private")
        void hasAllFieldsPrivate(){
            String nonPrivateFields = Arrays.stream(builderClass.getDeclaredFields())
                    .filter(field -> !Modifier.isPrivate(field.getModifiers()))
                    .map(Field::getName)
                    .collect(Collectors.joining(", "));

            assertThat(nonPrivateFields).as("public fields are: "+nonPrivateFields).isEmpty();
        }

        @Test @DisplayName("has method build")
        void hasMethodBuild(){
            long numberOfBuildMethods = Arrays.stream(builderClass.getDeclaredMethods()).filter(method -> method.getName().equals("build")).count();

            assertThat(numberOfBuildMethods).isEqualTo(1);
        }

        @Test @DisplayName("has method for each field")
        void hasMethodForEachField(){
            String fieldsWithoutSettingMethod = Arrays.stream(builderClass.getDeclaredFields())
                    .filter(field -> Arrays.stream(builderClass.getDeclaredMethods()).map(Method::getName).noneMatch(field.getName()::equals))
                    .map(Field::getName)
                    .collect(Collectors.joining(", "));

            assertThat(fieldsWithoutSettingMethod).as("missing setting methods for fields: "+fieldsWithoutSettingMethod).isEmpty();
        }

        @Test @DisplayName("has default constructor")
        void hasDefaultConstructor() throws NoSuchMethodException {
            assertThat(builderClass.getConstructor()).isNotNull();
        }
    }


}
