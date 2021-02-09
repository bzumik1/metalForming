package com.siemens.metal_forming.entity.converter;

import com.siemens.metal_forming.domain.Curve;
import com.siemens.metal_forming.domain.PointOfTorqueAndSpeed;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.List;

public class CurveConverter implements AttributeConverter<Curve, String> {
    private static final String SEPARATOR1 = ",";
    private static final String SEPARATOR2 = ";";


    @Override
    public String convertToDatabaseColumn(Curve curve) {
        if (curve == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for(PointOfTorqueAndSpeed curvePoint:curve.getPoints()){
            sb.append(curvePoint.getTorque());
            sb.append(SEPARATOR1);
            sb.append(curvePoint.getSpeed());
            sb.append(SEPARATOR2);
        }
        sb.setLength(sb.length() - SEPARATOR2.length());

        return sb.toString();
    }

    @Override
    public Curve convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }

        String[] pieces = dbData.split(SEPARATOR2);

        if (pieces == null || pieces.length == 0) {
            return null;
        }

        List<PointOfTorqueAndSpeed> curvePoints = new ArrayList<>();
        for(String piece:pieces){
            String[] numbers = piece.split(SEPARATOR1);
            curvePoints.add(new PointOfTorqueAndSpeed(Float.parseFloat(numbers[0]), Float.parseFloat(numbers[1])));
        }

        return Curve.builder().points(curvePoints).build();
    }
}
