package com.siemens.metal_forming.repository;

import com.siemens.metal_forming.entity.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Long> {
    @Query(value = "SELECT * FROM tools WHERE plc_id = ?1", nativeQuery = true)
    List<Tool> findAllByPlcId(Long plcId);


    @Query(value = "SELECT * FROM tools WHERE plc_id = ?1 AND tool_number = ?2", nativeQuery = true)
    Optional<Tool> findByPlcIdAndToolNumber(Long plcId, Integer toolNumber);
}
