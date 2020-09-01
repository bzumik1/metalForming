package com.siemens.metal_forming.exception.exceptions;

public class ToolNotFoundException extends RuntimeException {
    public ToolNotFoundException(){};
    public ToolNotFoundException(long toolId){
        super("Tool with id "+toolId+" was not found");
    }
    public ToolNotFoundException(int toolNumber){
        super("Tool with tool number "+toolNumber+" was not found");
    }
    public ToolNotFoundException(String message){
        super(message);
    }
}
