package com.siemens.metal_forming.repository;

import com.siemens.metal_forming.entity.HardwareInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HardwareInformationRepository extends JpaRepository<HardwareInformation,Long> {
}
