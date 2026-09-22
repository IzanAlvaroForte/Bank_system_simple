package com.example.bank_system_sample.DTO.Response.TransactionResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDepositResponse {

    private BigDecimal amount;
    private BigDecimal newBalance;
    private String transactionCode;
    private String confirmationMessage;
    private LocalDateTime createdAt;
}
