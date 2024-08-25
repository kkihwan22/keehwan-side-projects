package com.keehwan.api.rest.exceptions;

import com.keehwan.api.consts.ErrorCodes;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class BadRequestParameterException extends RuntimeException {
    @NotNull private final ErrorCodes errorCodes;
    @NotNull private final HttpStatus httpStatus;
    @NotNull private final Map<String, Object> fieldErrorMap;

    public BadRequestParameterException(ErrorCodes errorCodes, List<FieldError> errors) {
        this.errorCodes = errorCodes;
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.fieldErrorMap = new HashMap<>();

         errors.stream().forEach(error -> {
             fieldErrorMap.put(error.getField(), error.getDefaultMessage());
         });
    }
}
