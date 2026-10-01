package com.example.bank_system_sample.Extras.ExceptionHandlers.CustomerHandler.Exceptions;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("invalid email or password");
    }
}
