package com.siemens.metal_forming.entity;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

public class ReferenceCurve extends Curve {
    Timestamp createdOn;

    ReferenceCurve(Long id, @NotNull List<CurvePoint> points) {
        super(id, points);
    }
}
