package com.example.bank_system_sample.Extras.ExceptionHandlers.TransactionHandler.Exceptions;

public class InvalidAmountException extends RuntimeException {
    public InvalidAmountException(String message) {
        super(message);
    }
}
