package com.example.test.exception;

import javax.naming.AuthenticationException;

import lombok.Getter;

@Getter
public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String message) {
        super(message);
    }
}
