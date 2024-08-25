package com.keehwan.api.rest.exceptions;

import com.keehwan.api.consts.ErrorCodes;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;

public class BaseRestException extends RuntimeException {
    @NotNull
    private final ErrorCodes errorCodes;

    @NotNull
    private final HttpStatus httpStatus;

    public BaseRestException(
            @NotNull ErrorCodes errorCodes,
            @NotNull HttpStatus httpStatus
    ) {
        this.errorCodes = errorCodes;
        this.httpStatus = httpStatus;
    }

    @NotNull
    public ErrorCodes getErrorCodes() {
        return errorCodes;
    }

    @NotNull
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}