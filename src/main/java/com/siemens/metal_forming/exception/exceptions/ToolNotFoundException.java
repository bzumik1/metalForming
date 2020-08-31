package com.siemens.metal_forming.exception.exceptions;

public class ToolNotFoundException extends RuntimeException {
    public ToolNotFoundException(){};
    public ToolNotFoundException(Long toolId){
        super("Tool with id "+toolId+" was not found");
    }
    public ToolNotFoundException(String message){
        super(message);
    }
}
