package com.siemens.metal_forming.repository;

import com.siemens.metal_forming.entity.log.Log;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface LogRepository extends JpaRepository<Log,Long> {
    @EntityGraph(attributePaths = {"plcInformation", "toolInformation", "toolInformation.tolerance"})
    List<Log> findAllByToolInformationToolIdOrderByCreatedOnDesc(Long ToolId);

    void deleteAllByIdIn(Iterable<Long> ids);

    @EntityGraph(attributePaths = {"collisionPoints", "plcInformation", "toolInformation", "toolInformation.tolerance", "motorCurve", "referenceCurve", "measuredCurve"})
    Optional<Log> findById(Long id);


    @Query("select l.id from Log l where l.id in :ids")
    Set<Long> findIdsByIdsIn(@Param("ids") Iterable<Long> ids);

}
