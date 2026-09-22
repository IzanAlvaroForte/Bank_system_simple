package com.example.bank_system_sample.DTO.Response.TransactionResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionTransferResponse {

    private BigDecimal amount;
    private BigDecimal newBalance;
    private String toAccountCode;
    private String fromAccountCode;
    private String transactionCode;
    private String confirmationMessage;
    private LocalDateTime createdAt;
}
