package com.siemens.metal_forming.entity.converter;

import com.siemens.metal_forming.domain.PointOfTorqueAndSpeed;

import javax.persistence.AttributeConverter;
import java.util.HashSet;
import java.util.Set;

public class CollisionPointsConverter implements AttributeConverter<Set<PointOfTorqueAndSpeed>, String> {
    private static final String SEPARATOR1 = ",";
    private static final String SEPARATOR2 = ";";


    @Override
    public String convertToDatabaseColumn(Set<PointOfTorqueAndSpeed> collisionPoints) {
        if (collisionPoints == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for(PointOfTorqueAndSpeed collisionPoint:collisionPoints){
            sb.append(collisionPoint.getTorque());
            sb.append(SEPARATOR1);
            sb.append(collisionPoint.getSpeed());
            sb.append(SEPARATOR2);
        }
        sb.setLength(sb.length() - SEPARATOR2.length());

        return sb.toString();
    }

    @Override
    public Set<PointOfTorqueAndSpeed> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }

        String[] pieces = dbData.split(SEPARATOR2);

        if (pieces == null || pieces.length == 0) {
            return null;
        }

        Set<PointOfTorqueAndSpeed> collisionPoints = new HashSet<>();
        for(String piece:pieces){
            String[] numbers = piece.split(SEPARATOR1);
            collisionPoints.add(new PointOfTorqueAndSpeed(Float.parseFloat(numbers[0]), Float.parseFloat(numbers[1])));
        }

        return collisionPoints;
    }
}
