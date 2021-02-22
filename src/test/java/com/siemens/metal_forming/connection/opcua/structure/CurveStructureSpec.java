package com.siemens.metal_forming.connection.opcua.structure;

import com.siemens.metal_forming.connection.opcua.structure.CurveStructure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("<= HMI TREND SPECIFICATION =>")
public class CurveStructureSpec {
    @Nested @DisplayName("UNMODIFIABLE")
    class Unmodifiable{
        @Test @DisplayName("torque can not be modified after creation")
        void torqueCanNotBeModifiedAfterCreation(){
            CurveStructure curveStructure = new CurveStructure(new Float[]{1f, 2f},new Float[]{1f,2f});

            assertThrows(UnsupportedOperationException.class, () -> curveStructure.getTorque().add(3f));
            assertThrows(UnsupportedOperationException.class, () -> curveStructure.getTorque().remove(3f));
        }

        @Test @DisplayName("speed can not be modified after creation")
        void speedCanNotBeModifiedAfterCreation(){
            CurveStructure curveStructure = new CurveStructure(new Float[]{1f, 2f},new Float[]{1f,2f});

            assertThrows(UnsupportedOperationException.class, () -> curveStructure.getSpeed().add(3f));
            assertThrows(UnsupportedOperationException.class, () -> curveStructure.getSpeed().remove(3f));
        }
    }
}
