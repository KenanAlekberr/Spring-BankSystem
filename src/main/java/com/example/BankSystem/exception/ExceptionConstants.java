package com.example.BankSystem.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionConstants {
    UNEXPECTED_EXCEPTION("UNEXPECTED_EXCEPTION", "Unexpected exception occurred"),
    USER_NOT_FOUND("USER_NOT_FOUND", "User not found by id"),
    HTTP_METHOD_IS_NOT_CORRECT("HTTP_METHOD_IS_NOT_CORRECT", "http method is not correct");

    private final String code;
    private final String message;

    public String getMessage(Object... param) {
        return String.format(message, param);
    }
}