package com.keehwan.api.authentication.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UserAccountNotEnableException extends AuthenticationException {

    public UserAccountNotEnableException(String msg) {
        super(msg);
    }

    public UserAccountNotEnableException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
