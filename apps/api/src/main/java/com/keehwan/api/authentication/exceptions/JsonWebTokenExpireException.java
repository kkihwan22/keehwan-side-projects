package com.keehwan.api.authentication.exceptions;

import org.springframework.security.core.AuthenticationException;

public class JsonWebTokenExpireException extends AuthenticationException {

    public JsonWebTokenExpireException(String msg) {
        super(msg);
    }

    public JsonWebTokenExpireException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
