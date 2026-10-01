package com.example.bank_system_sample.Extras.ExceptionHandlers.TransactionHandler.Exceptions;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(String message) {
        super(message);
    }
}
