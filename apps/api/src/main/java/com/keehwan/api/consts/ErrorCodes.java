package com.keehwan.api.consts;

import org.jetbrains.annotations.NotNull;

public enum ErrorCodes {
    UNKNOWN("try again.!", 1L),
    BAD_REQUEST("잘못된 요청입니다. 확인 후 다시 시도하세요.", 10L),
    UNAUTHORIZED_ACCESS("로그인이 필요합니다.", 100L),



    ;


    public final @NotNull String message;
    public final @NotNull Long code;

    ErrorCodes(@NotNull String message, @NotNull Long code) {
        this.message = message;
        this.code = code;
    }

}
