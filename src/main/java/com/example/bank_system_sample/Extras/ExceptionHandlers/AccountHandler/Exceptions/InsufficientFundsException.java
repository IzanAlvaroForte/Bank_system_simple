package com.example.bank_system_sample.Extras.ExceptionHandlers.AccountHandler.Exceptions;

import java.math.BigDecimal;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(BigDecimal amount) {
        super("Insufficient funds: " + amount);
    }
}
