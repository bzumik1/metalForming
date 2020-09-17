package com.siemens.metal_forming.exception.exceptions;

public class InvalidIdException extends RuntimeException {
    public InvalidIdException(){
        super("id can not be null");
    }
}
