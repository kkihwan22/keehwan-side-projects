package com.keehwan.share.domain.code;

import lombok.Getter;

public enum JsonWebTokenType {

    ACCESS(60 * 60 * 1000L),
    REFRESH(30 * 24 *60 * 60 * 1000L),
    EMAIL_VERITY(7* 24* 60* 60 * 1000L),
    ;

    @Getter
    long expiredMs;

    JsonWebTokenType(long expiredMs) {
        this.expiredMs = expiredMs;
    }
}
