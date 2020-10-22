package com.siemens.metal_forming.exception.exceptions;

public class CalculationNotFoundException extends RuntimeException {
    public CalculationNotFoundException(Long id){
        super("Calculation of reference curve for tool with id "+id+" was not found");
    }
    public CalculationNotFoundException(String message){
        super(message);
    }
}
