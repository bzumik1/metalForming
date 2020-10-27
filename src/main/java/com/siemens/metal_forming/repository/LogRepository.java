package com.siemens.metal_forming.repository;

import com.siemens.metal_forming.entity.log.Log;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface LogRepository extends JpaRepository<Log,Long> {
    List<Log> findAllByToolInformationToolIdOrderByCreatedOnDesc(Long ToolId);

    void deleteAllByIdIn(Iterable<Long> ids);

    @EntityGraph(attributePaths = {"measuredCurve.points", "motorCurve.points", "referenceCurve.points", "collisionPoints", "plcInformation", "toolInformation"})
    Optional<Log> findById(Long id);

    @Query("select l.id from Log l where l.id in :ids")
    Set<Long> findIdsByIdsIn(@Param("ids") Iterable<Long> ids);

}
