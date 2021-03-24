package com.siemens.metal_forming.exception.exceptions;

public class CurrentToolCanNotBeDeletedException extends RuntimeException {
    public CurrentToolCanNotBeDeletedException(){}

    public CurrentToolCanNotBeDeletedException(long toolId){
        super("Tool with id "+toolId+" is currently used and that is why it can not be deleted.");
    }
    public CurrentToolCanNotBeDeletedException(String message){
        super(message);
    }
}
