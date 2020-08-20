package com.siemens.metal_forming.exception.exceptionsApi;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Builder @AllArgsConstructor @FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ApiException {
    HttpStatus status;
    String message;
    String detail;
    List<String> errors;
}
