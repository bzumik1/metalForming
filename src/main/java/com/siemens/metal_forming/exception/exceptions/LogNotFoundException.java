package com.siemens.metal_forming.exception.exceptions;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class LogNotFoundException extends RuntimeException {
    public LogNotFoundException(Long id){
        super("Log with id "+id+" was not found");
    }
    public LogNotFoundException(Iterable<Long> ids){
        super("Logs with ids: " + StreamSupport
                .stream(ids.spliterator(),false)
                .map(Object::toString)
                .collect(Collectors.joining(", "))
            + " were not found");
    }
}
