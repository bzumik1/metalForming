package com.siemens.metal_forming.exception.exceptions;

public class PlcNotFoundException extends RuntimeException {
    public PlcNotFoundException(Long id){
        super("Plc with id "+id+" was not found");
    }
    public PlcNotFoundException(String message){
        super(message);
    }
}
