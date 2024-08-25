package com.keehwan.api.authentication.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UserAccountLockedException extends AuthenticationException {

    public UserAccountLockedException(String msg) {
        super(msg);
    }

    public UserAccountLockedException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
