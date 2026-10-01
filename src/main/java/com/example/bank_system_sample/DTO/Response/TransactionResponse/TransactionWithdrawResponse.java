package com.example.bank_system_sample.DTO.Response.TransactionResponse;

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
public class TransactionWithdrawResponse {

    private BigDecimal amount;
    private String transactionCode;
    private String confirmationMessage;
    private LocalDateTime createdAt;
}
