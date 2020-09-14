package com.siemens.metal_forming.repository;

import com.siemens.metal_forming.entity.CurvePoint;
import com.siemens.metal_forming.entity.PointOfTorqueAndSpeed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurvePointRepository extends JpaRepository<CurvePoint,Long> {
}
