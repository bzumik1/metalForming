package com.siemens.metal_forming.repository;

import com.siemens.metal_forming.entity.log.PlcInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlcInfoRepository extends JpaRepository<PlcInfo, Long> {
}
