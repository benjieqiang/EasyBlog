package com.ben.domain.auth.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-04  20:24
 * @Description: TODO
 * @Version: 1.0
 */
public class UsernameOrPasswordNullException extends AuthenticationException {
    public UsernameOrPasswordNullException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UsernameOrPasswordNullException(String msg) {
        super(msg);
    }
}

