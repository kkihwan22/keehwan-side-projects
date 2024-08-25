package com.keehwan.api.authentication.exceptions;

import org.springframework.security.core.AuthenticationException;

public class JwtExpireException extends AuthenticationException {

    public JwtExpireException(String msg) {
        super(msg);
    }

    public JwtExpireException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
