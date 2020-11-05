package com.siemens.metal_forming.repository;

import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.entity.Tool;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlcRepository extends JpaRepository<Plc,Long> {
    Optional<Plc> findByIpAddress(String ipAddress);

    boolean existsByIpAddress(String ipAddress);
    boolean existsByName(String name);

    @Query("select case when (count(plc) > 0)  then true else false end  \n" +
            "from Plc plc where plc.ipAddress = ?1 and plc.id != ?2")
    boolean existsByIpAddressIgnoringId(String ipAddress, Long id);

    @Query("select case when (count(plc) > 0)  then true else false end  \n" +
            "from Plc plc where plc.name = ?1 and plc.id != ?2")
    boolean existsByNameIgnoringId(String name, Long id);

    @EntityGraph(attributePaths = {"tools","connection","hardwareInformation","motorCurve"})
    @Query("SELECT plc FROM Plc plc WHERE plc.id = ?1")
    Optional<Plc> findByIdFetchAll(Long id);
}
