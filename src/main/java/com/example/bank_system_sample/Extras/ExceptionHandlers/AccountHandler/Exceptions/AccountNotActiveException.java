package com.example.bank_system_sample.Extras.ExceptionHandlers.AccountHandler.Exceptions;

public class AccountNotActiveException extends RuntimeException {
    public AccountNotActiveException(String accountCode) {
        super("Account is not active: " + accountCode);
    }
}
