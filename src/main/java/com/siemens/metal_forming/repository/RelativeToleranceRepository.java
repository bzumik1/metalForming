package com.siemens.metal_forming.repository;

import com.siemens.metal_forming.entity.RelativeTolerance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelativeToleranceRepository extends JpaRepository<RelativeTolerance, Long> {
}
