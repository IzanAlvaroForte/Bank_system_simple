package com.example.bank_system_sample.DTO.Response.AccountResponse;

import com.example.bank_system_sample.Entity.AccountStatus;
import com.example.bank_system_sample.Entity.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountOpenResponse {

    private String accountCode;
    private AccountType accountType;
    private AccountStatus accountStatus;
    private BigDecimal balance;
    private LocalDateTime createdAt;
}
