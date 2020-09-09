package com.siemens.metal_forming.repository;

import com.siemens.metal_forming.entity.log.ToolInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToolInfoRepository extends JpaRepository<ToolInfo, Long> {
}
