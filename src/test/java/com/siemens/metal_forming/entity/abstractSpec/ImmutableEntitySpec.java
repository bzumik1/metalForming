package com.siemens.metal_forming.entity.abstractSpec;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class ImmutableEntitySpec extends EntitySpec{
    public ImmutableEntitySpec(Class<?> testedClass) {
        super(testedClass);
    }

    @Nested @DisplayName("IMMUTABLE ENTITY")
    class ImmutableEntity{
        @Test @DisplayName("does not have any setter")
        void doesNotHaveAnySetter(){
            assertThat(Arrays.stream(testedClass.getMethods())
                    .filter(method -> method.getName().contains("set"))
                    .collect(Collectors.toList())).isEmpty();
        }

        @Test @DisplayName("default (no argument) constructor is protected")
        void defaultConstructorIsProtected(){
            try {
                assertThat(testedClass.getDeclaredConstructor().getModifiers()& Modifier.PROTECTED).isEqualTo(Modifier.PROTECTED);
            } catch (NoSuchMethodException e) {
                fail("doesn't have default (no argument) constructor");
            }
        }

        @Test @DisplayName("is final class")
        void isFinalClass(){
            assertThat(testedClass.getModifiers()&Modifier.FINAL).isEqualTo(Modifier.FINAL);
        }

        @Test @DisplayName("has builder")
        void hasBuilder(){
            assertThat(Arrays.stream(testedClass.getMethods())
                    .filter(method -> method.getName().contains("builder"))
                    .collect(Collectors.toList())).isNotEmpty();
        }
    }
}
