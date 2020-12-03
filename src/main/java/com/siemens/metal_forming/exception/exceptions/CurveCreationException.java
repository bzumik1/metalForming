package com.siemens.metal_forming.exception.exceptions;

public class CurveCreationException extends RuntimeException{
    public CurveCreationException(){
        super("Incompatible length of torques and speeds");
    }
}
