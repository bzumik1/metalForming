package com.siemens.metal_forming.testBuilders;

import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.entity.Tool;
import com.siemens.metal_forming.enumerated.StopReactionType;
import com.siemens.metal_forming.enumerated.ToolStatusType;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.test.util.ReflectionTestUtils;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestToolBuilder {
    Long id;
    Plc plc = new TestPlcBuilder().build();
    Integer toolNumber = 1;
    String name = "toolName";
    Integer numberOfReferenceCycles = 10;
    StopReactionType stopReaction = StopReactionType.IMMEDIATE;
    Boolean automaticMonitoring = true;
    Integer maxSpeedOperation = 60;
    ToolStatusType toolStatus = ToolStatusType.MANUALLY_ADDED;
    Curve referenceCurve = new TestCurveBuilder().randomPoints(100).build();

    public TestToolBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public TestToolBuilder plc(Plc plc) {
        this.plc = plc;
        return this;
    }

    public TestToolBuilder toolNumber(Integer toolNumber) {
        this.toolNumber = toolNumber;
        return this;
    }

    public TestToolBuilder name(String name) {
        this.name = name;
        return this;
    }

    public TestToolBuilder numberOfReferenceCycles(Integer numberOfReferenceCycles) {
        this.numberOfReferenceCycles = numberOfReferenceCycles;
        return this;
    }

    public TestToolBuilder stopReaction(StopReactionType stopReaction) {
        this.stopReaction = stopReaction;
        return this;
    }

    public TestToolBuilder automaticMonitoring(Boolean automaticMonitoring) {
        this.automaticMonitoring = automaticMonitoring;
        return this;
    }

    public TestToolBuilder maxSpeedOperation(Integer maxSpeedOperation) {
        this.maxSpeedOperation = maxSpeedOperation;
        return this;
    }

    public TestToolBuilder toolStatus(ToolStatusType toolStatus) {
        this.toolStatus = toolStatus;
        return this;
    }

    public TestToolBuilder referenceCurve(Curve referenceCurve) {
        this.referenceCurve = referenceCurve;
        return this;
    }

    public Tool build(){
        Tool toolToReturn = new Tool();

        ReflectionTestUtils.setField(toolToReturn, "id", id);
        ReflectionTestUtils.setField(toolToReturn, "toolNumber", toolNumber);
        ReflectionTestUtils.setField(toolToReturn, "name", name);
        ReflectionTestUtils.setField(toolToReturn, "numberOfReferenceCycles", numberOfReferenceCycles);
        ReflectionTestUtils.setField(toolToReturn, "stopReaction", stopReaction);
        ReflectionTestUtils.setField(toolToReturn, "automaticMonitoring", automaticMonitoring);
        ReflectionTestUtils.setField(toolToReturn, "maxSpeedOperation", maxSpeedOperation);
        ReflectionTestUtils.setField(toolToReturn, "toolStatus", toolStatus);
        ReflectionTestUtils.setField(toolToReturn, "referenceCurve", referenceCurve);

        plc.addTool(toolToReturn);

        return toolToReturn;
    }
}
