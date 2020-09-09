package com.siemens.metal_forming.repository;

import com.siemens.metal_forming.entity.log.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log,Long> {
}
