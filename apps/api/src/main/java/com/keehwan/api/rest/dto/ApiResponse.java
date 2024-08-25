package com.keehwan.api.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.keehwan.api.consts.ErrorCodes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(
        @NotNull long code,
        @NotNull String message,
        @Nullable T data,
        @Nullable Map<String, Object> metadata
) {
    public static <T> ApiResponse<T> just(T data) {
        return new ApiResponse<>(0L, "success", data, null);
    }

    public static <T> ApiResponse<T> fromErrorCodes(ErrorCodes errorCodes) {
        return new ApiResponse<>(errorCodes.code, errorCodes.message, null, null);
    }

    public static <T> ApiResponse<T> fromErrorCodesAndMetadata(ErrorCodes errorCodes, Map<String,Object> metadata) {
        return new ApiResponse<>(errorCodes.code, errorCodes.message, null, metadata);
    }
}
