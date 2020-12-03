package com.siemens.metal_forming.opcua.structure;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("<= HMI TREND SPECIFICATION =>")
public class HmiTrendSpec {
    @Nested @DisplayName("UNMODIFIABLE")
    class Unmodifiable{
        @Test @DisplayName("torque can not be modified after creation")
        void torqueCanNotBeModifiedAfterCreation(){
            HmiTrend hmiTrend = new HmiTrend(new Float[]{1f, 2f},new Float[]{1f,2f});

            assertThrows(UnsupportedOperationException.class, () -> hmiTrend.getTorque().add(3f));
            assertThrows(UnsupportedOperationException.class, () -> hmiTrend.getTorque().remove(3f));
        }

        @Test @DisplayName("speed can not be modified after creation")
        void speedCanNotBeModifiedAfterCreation(){
            HmiTrend hmiTrend = new HmiTrend(new Float[]{1f, 2f},new Float[]{1f,2f});

            assertThrows(UnsupportedOperationException.class, () -> hmiTrend.getSpeed().add(3f));
            assertThrows(UnsupportedOperationException.class, () -> hmiTrend.getSpeed().remove(3f));
        }
    }
}
