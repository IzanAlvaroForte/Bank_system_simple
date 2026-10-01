package com.example.bank_system_sample.Extras.ExceptionHandlers.CustomerHandler.Exceptions;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String accountCode) {
        super("Customer with account code " + accountCode + " not found");
    }
}
