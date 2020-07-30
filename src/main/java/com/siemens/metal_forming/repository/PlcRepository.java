package com.siemens.metal_forming.repository;

import com.siemens.metal_forming.entity.Plc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlcRepository extends JpaRepository<Plc,Long> {
    Optional<Plc> findByIpAddress(String ipAddress);
}
