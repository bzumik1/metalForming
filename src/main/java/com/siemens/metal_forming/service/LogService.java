package com.siemens.metal_forming.service;

import com.siemens.metal_forming.entity.log.Log;

import java.util.List;
import java.util.Set;

public interface LogService {
    List<Log> findAllByToolId(Long toolId);
    Log findById(Long id);
    Log save(Log log);
    void delete(Set<Long> ids);
    Log updateComment(Long id, String comment);
}
