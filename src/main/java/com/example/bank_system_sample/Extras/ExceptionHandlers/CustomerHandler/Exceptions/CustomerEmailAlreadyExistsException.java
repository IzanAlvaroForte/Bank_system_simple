package com.example.bank_system_sample.Extras.ExceptionHandlers.CustomerHandler.Exceptions;

public class CustomerEmailAlreadyExistsException extends RuntimeException {

    public CustomerEmailAlreadyExistsException(String email) {
        super("Customer with email " + email + " already exists");
    }
}
