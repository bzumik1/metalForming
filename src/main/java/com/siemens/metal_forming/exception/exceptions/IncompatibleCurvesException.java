package com.siemens.metal_forming.exception.exceptions;

public class IncompatibleCurvesException extends RuntimeException {
    public IncompatibleCurvesException(){
        super("Curves have not same length.");
    }
}
