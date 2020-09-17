package com.siemens.metal_forming.exception.exceptions;

public class InvalidToolNumberException extends RuntimeException {
    public InvalidToolNumberException(){
        super("tool number can not be set to null");
    }
}
