package com.siemens.metal_forming.repository;

import com.siemens.metal_forming.entity.AbsoluteTolerance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsoluteToleranceRepository extends JpaRepository<AbsoluteTolerance, Long> {
}
