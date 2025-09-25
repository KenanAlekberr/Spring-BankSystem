package com.example.BankSystem.exception.custom;

import lombok.Getter;

@Getter
public class InsufficientAccountException extends RuntimeException {
    private final String code;

    public InsufficientAccountException(String code, String message) {
        super(message);
        this.code = code;
    }
}