package com.siemens.metal_forming.exception.exceptions;

public class ToolUniqueConstrainException extends RuntimeException {
    public ToolUniqueConstrainException(Integer toolNumber){
        super("Tool with tool number "+toolNumber+" already exists");
    }
    public ToolUniqueConstrainException(String message){
        super(message);
    }
}
