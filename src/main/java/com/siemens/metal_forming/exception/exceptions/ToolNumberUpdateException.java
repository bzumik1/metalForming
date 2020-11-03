package com.siemens.metal_forming.exception.exceptions;

public class ToolNumberUpdateException extends RuntimeException {
    public ToolNumberUpdateException(){
        super("ToolNumber can not be changed for autodetected tool");
    }
}
