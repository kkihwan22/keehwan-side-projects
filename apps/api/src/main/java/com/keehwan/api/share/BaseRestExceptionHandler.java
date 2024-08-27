package com.keehwan.api.share;

import com.keehwan.api.rest.dto.ApiResponse;
import com.keehwan.api.rest.exceptions.BadRequestParameterException;
import com.keehwan.api.rest.exceptions.BaseRestException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.charset.StandardCharsets;

@RestControllerAdvice
public class BaseRestExceptionHandler {
    private static final MediaType CONTENT_TYPE = new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8);

    @ExceptionHandler(value = {BadRequestParameterException.class})
    public ResponseEntity<ApiResponse<Void>> handleBadRequestParameterException(BadRequestParameterException exception) {
        return ResponseEntity.status(exception.getHttpStatus())
                .contentType(CONTENT_TYPE)
                .body(ApiResponse.fromErrorCodesAndMetadata(exception.getErrorCodes(), exception.getFieldErrorMap()));
    }

    @ExceptionHandler(value = {BaseRestException.class})
    public ResponseEntity<ApiResponse<Void>> handleCommonRestException(
            BaseRestException exception
    ) {
        return ResponseEntity.status(exception.getHttpStatus())
                .contentType(CONTENT_TYPE)
                .body(ApiResponse.fromErrorCodes(exception.getErrorCodes()));
    }
}
