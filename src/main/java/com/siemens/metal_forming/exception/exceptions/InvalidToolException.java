package com.siemens.metal_forming.exception.exceptions;

public class InvalidToolException extends RuntimeException {
    public InvalidToolException(){
        super("tool can not be null");
    }
}
