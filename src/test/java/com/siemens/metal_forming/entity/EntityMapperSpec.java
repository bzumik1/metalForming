package com.siemens.metal_forming.entity;

import com.siemens.metal_forming.dto.DtoMapperImpl;
import com.siemens.metal_forming.entity.log.CollisionPoint;
import com.siemens.metal_forming.entity.log.Log;
import com.siemens.metal_forming.enumerated.ToolStatusType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest(classes = EntityMapperImpl.class)
@DisplayName("<= ENTITY MAPPER SPECIFICATION =>")
class EntityMapperSpec {
    @Autowired
    EntityMapper entityMapper;

    @Nested @DisplayName("LOG FROM PLC, MEASURED CURVE AND COLLISION POINTS")
    class LogFromPlcMeasuredCurveAndCollisionPoints{
        Plc plcWithAllAttributes;
        Curve measuredCurve;
        Set<CollisionPoint> collisionPoints;

        @BeforeEach
        void initializeForLog(){
            plcWithAllAttributes = new Plc();
            Curve motorCurve = new Curve();
            motorCurve.setId(1L);
            for(int i=0; i<100; i++){
                motorCurve.getPoints().add(new CurvePoint((float)Math.random(),(float)Math.random()));
            }
            Set<Tool> tools = new HashSet<>();
            for(int i=0; i<10; i++){
                Tool tool = new Tool();
                tool.setId((long)i);
                tool.setToolNumber(i);
                tool.setToolStatus(ToolStatusType.AUTODETECTED);
                Curve referenceCurve = new Curve();
                referenceCurve.setId((long)i);
                for(int j=0; j<100; j++){
                    referenceCurve.getPoints().add(new CurvePoint((float)Math.random(),(float)Math.random()));
                }
                tool.setReferenceCurve(referenceCurve);
                tools.add(tool);
            }

            plcWithAllAttributes.getHardwareInformation().setSerialNumber("SN 8370938");
            plcWithAllAttributes.getHardwareInformation().setFirmwareNumber("FW V1.2");
            plcWithAllAttributes.setMotorCurve(motorCurve);
            plcWithAllAttributes.setIpAddress("192.168.1.1");
            plcWithAllAttributes.markAsConnected();
            plcWithAllAttributes.setTools(tools);
            plcWithAllAttributes.setCurrentTool(0);
            plcWithAllAttributes.setName("name");


            measuredCurve = new Curve();
            for(int i=0; i<100; i++){
                measuredCurve.getPoints().add(new CurvePoint((float)Math.random(),(float)Math.random()));
            }

            collisionPoints = new HashSet<>();
            for(int i=0; i<100; i++){
                collisionPoints.add(new CollisionPoint((float)Math.random(),(float)Math.random()));
            }
        }

        @Test @DisplayName("copies correctly all atributes")
        void copiesCorrectlyAllAttributes(){
            Log log = entityMapper.toLog(plcWithAllAttributes,measuredCurve,collisionPoints);
            System.out.println();
        }

        DONT COPY IDS (CURVES AND SO ON)
    }
}
