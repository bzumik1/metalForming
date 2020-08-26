package com.siemens.metal_forming.exception.exceptions;

public class InvalidToolsException extends RuntimeException {
    public InvalidToolsException(){
        super("Tools can not be set to null");
    }
}
