package com.siemens.metal_forming.repository;

import com.siemens.metal_forming.entity.Curve;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurveRepository extends JpaRepository<Curve,Long> {
}
