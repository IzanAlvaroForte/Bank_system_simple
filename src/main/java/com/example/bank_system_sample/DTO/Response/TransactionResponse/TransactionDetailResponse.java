package com.example.bank_system_sample.DTO.Response.TransactionResponse;

import com.example.bank_system_sample.Entity.TransactionStatus;
import com.example.bank_system_sample.Entity.TransactionType;
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
public class TransactionDetailResponse {

    private String transactionCode;
    private TransactionType transactionType;
    private TransactionStatus transactionStatus;
    private String fromAccountCode;
    private String toAccountCode;
    private BigDecimal amount;
    private LocalDateTime createdAt;
}
