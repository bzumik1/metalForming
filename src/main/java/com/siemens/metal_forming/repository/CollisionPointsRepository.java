package com.siemens.metal_forming.repository;

import com.siemens.metal_forming.entity.log.CollisionPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollisionPointsRepository extends JpaRepository<CollisionPoint,Long> {
}
