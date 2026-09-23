package com.example.bank_system_sample.DTO.Response.TransactionResponse;

import com.example.bank_system_sample.Entity.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionSummaryResponse {

    private String transactionCode;
    private TransactionType transactionType;
    private BigDecimal amount;
    private LocalDateTime createdAt;
}
