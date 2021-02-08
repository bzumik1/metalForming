package com.siemens.metal_forming.entity.converter;

import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.entity.CurvePoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("<= CURVE CONVERTER SPECIFICATION =>")
public class CurveConverterSpec {
    private CurveConverter curveConverter;

    @BeforeEach
    void initialize(){
        curveConverter = new CurveConverter();
    }

    @Test @DisplayName("converts correctly to database")
    void convertsCorrectlyToDatabase(){
        final Curve curve = Curve.builder()
                .points(List.of(
                        new CurvePoint(1f,1f),
                        new CurvePoint(2f,2f)))
                .build();

        assertThat(curveConverter.convertToDatabaseColumn(curve)).isEqualTo("1.0,1.0;2.0,2.0");
    }

    @Test @DisplayName("converts correctly from database")
    void convertsCorrectlyFromDatabase(){
        final String dataInDatabase = "1.0,1.0;2.0,2.0";
        final Curve curve = Curve.builder()
                .points(List.of(
                        new CurvePoint(1f,1f),
                        new CurvePoint(2f,2f)))
                .build();

        assertThat(curveConverter.convertToEntityAttribute(dataInDatabase)).isEqualTo(curve);
    }
}
