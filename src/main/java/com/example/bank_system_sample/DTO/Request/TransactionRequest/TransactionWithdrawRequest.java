package com.example.bank_system_sample.DTO.Request.TransactionRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionWithdrawRequest {

    @NotBlank(message = "Account code is required")
    private String accountCode;

    @NotBlank(message = "Amount is required")
    @Positive(message = "Must be greater than zero")
    private BigDecimal amount;
}
