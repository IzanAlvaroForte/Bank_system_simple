package com.example.bank_system_sample.DTO.Request.AccountRequest;

import com.example.bank_system_sample.Entity.AccountStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountStatusUpdateRequest {

    @NotNull(message = "New account status is required")
    private AccountStatus accountStatus;
}
