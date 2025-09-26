package com.example.BankSystem.exception.custom;

import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Getter
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class InsufficientException extends RuntimeException {
    String code;

    public InsufficientException(String code, String message) {
        super(message);
        this.code = code;
    }
}