package com.example.bank_system_sample.Extras.ExceptionHandlers.AccountHandler.Exceptions;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String accountCode) {
        super("Account with account code " + accountCode + " not found");
    }
}
