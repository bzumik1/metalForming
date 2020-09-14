package com.siemens.metal_forming.repository;

import com.siemens.metal_forming.entity.Curve;
import com.siemens.metal_forming.entity.CurveEnvelope;
import com.siemens.metal_forming.entity.CurvePoint;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("<= CURVE REPOSITORY SPECIFICATION =>")
@DataJpaTest
class CurveEnvelopeRepositorySpec {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    CurveEnvelopeRepository curveEnvelopeRepository;

    @Autowired
    CurveRepository curveRepository;

    CurveEnvelope curveEnvelope;

    @BeforeEach
    void initialize(){
        Set<Curve> curves = new HashSet<>();
        for(int i=0; i<3;i++){
            List<CurvePoint> curvePoints = new ArrayList<>();
            for(int j=0; j<100; j++){
                curvePoints.add(new CurvePoint((float)Math.random(),(float)Math.random()));
            }
            curves.add(Curve.builder().points(curvePoints).build());
        }
        curveEnvelope = CurveEnvelope.builder().boundaryCurves(curves).build();
    }

    @Nested @DisplayName("CASCADE") @DataJpaTest
    class Cascade{
        @Test @DisplayName("deletes all boundary curves when curve envelope is deleted")
        void deletesAllBoundaryCurvesWhenCurveEnvelopeIsDeleted(){
            Long id = curveEnvelopeRepository.save(curveEnvelope).getId();
            curveEnvelopeRepository.deleteById(id);

            assertThat(curveRepository.findAll()).isEmpty();
        }
    }

}
