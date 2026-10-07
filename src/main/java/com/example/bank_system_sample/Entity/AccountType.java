package com.example.bank_system_sample.Entity;

public enum AccountType {
    SAVINGS("SA"),
    CHECKING("CH"),
    FIXED_DEPOSIT("FD");

    private final String prefix;

    AccountType(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
