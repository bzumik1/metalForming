package com.siemens.metal_forming.exception;

import com.siemens.metal_forming.exception.exceptions.*;
import com.siemens.metal_forming.exception.exceptionsApi.ApiException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiException apiException = ApiException.builder()
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .message("Invalid method")
                .detail(ex.getMessage())
                .build();
        return new ResponseEntity<>(apiException,apiException.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiException apiException = ApiException.builder().status(HttpStatus.BAD_REQUEST).message("Invalid message format").detail(ex.getCause().getMessage()).build();
        return new ResponseEntity<>(apiException,apiException.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        StringBuilder message = new StringBuilder();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
            message.append(error.getDefaultMessage()).append(", ");
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
            message.append(error.getDefaultMessage()).append(", ");
        }

        message.setLength(message.length()-2);

        ApiException apiException = ApiException.builder()
                .message(message.toString())
                .status(HttpStatus.BAD_REQUEST)
                .errors(errors).build();

        return handleExceptionInternal(
                ex, apiException, headers, apiException.getStatus(), request);
    }

    @ExceptionHandler({PlcNotFoundException.class, ToolNotFoundException.class, LogNotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundException(RuntimeException ex){
        ApiException apiException = ApiException.builder().message(ex.getMessage()).status(HttpStatus.NOT_FOUND).build();

        return new ResponseEntity<>(apiException,apiException.getStatus());
    }

    @ExceptionHandler({PlcUniqueConstrainException.class, ToolUniqueConstrainException.class})
    protected ResponseEntity<Object> handleUniqueConstrainException(RuntimeException ex){
        ApiException apiException = ApiException.builder().message(ex.getMessage()).status(HttpStatus.CONFLICT).build();

        return new ResponseEntity<>(apiException,apiException.getStatus());
    }
}
