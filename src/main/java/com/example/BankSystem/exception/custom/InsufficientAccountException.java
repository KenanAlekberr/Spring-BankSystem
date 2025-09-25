package com.example.BankSystem.exception.custom;

public class InsufficientAccountException extends RuntimeException {
    private final String code;

    public InsufficientAccountException(String message, String code) {
        super(message);
        this.code = code;
    }
}