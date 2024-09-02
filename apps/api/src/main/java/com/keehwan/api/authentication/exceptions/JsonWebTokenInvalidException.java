package com.keehwan.api.authentication.exceptions;

import org.springframework.security.core.AuthenticationException;

public class JsonWebTokenInvalidException extends AuthenticationException {

    public JsonWebTokenInvalidException(String msg) {
        super(msg);
    }

    public JsonWebTokenInvalidException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
