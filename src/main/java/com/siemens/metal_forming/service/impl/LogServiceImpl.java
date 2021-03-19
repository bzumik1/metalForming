package com.siemens.metal_forming.service.impl;

import com.siemens.metal_forming.entity.log.Log;
import com.siemens.metal_forming.exception.exceptions.LogNotFoundException;
import com.siemens.metal_forming.repository.LogRepository;
import com.siemens.metal_forming.service.LogService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LogServiceImpl implements LogService {
    final LogRepository logRepository;

    @Autowired
    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public List<Log> findAllByToolId(Long toolId) {
        return logRepository.findAllByToolInformationToolIdOrderByCreatedOnDesc(toolId);
    }

    @Override
    public Log findById(Long id) {
        return logRepository.findById(id).orElseThrow(() -> new LogNotFoundException(id));
    }

    @Override
    public Log save(Log log) {
        return logRepository.save(log);
    }

    @Transactional
    @Override
    public void delete(Set<Long> ids) {
        final Set<Long> idsInDb = logRepository.findIdsByIdsIn(ids);

        logRepository.deleteAllByIdIn(idsInDb); // delete all logs which are in DB

        final Set<Long> missingIds = new HashSet<>(ids);
        missingIds.removeAll(idsInDb);
        if(!missingIds.isEmpty()){
            throw new LogNotFoundException(missingIds);
        }
    }

    @Override
    public Log updateComment(Long id, String comment) {
        final Log logInDb = logRepository.findById(id).orElseThrow(() -> new LogNotFoundException(id));
        logInDb.setComment(comment);
        return logRepository.save(logInDb);
    }
}
