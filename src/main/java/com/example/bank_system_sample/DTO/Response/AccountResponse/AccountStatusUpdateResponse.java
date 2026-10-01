package com.example.bank_system_sample.DTO.Response.AccountResponse;

import com.example.bank_system_sample.Entity.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AccountStatusUpdateResponse {

    private AccountStatus accountStatus;
    private String confirmationMessage;
    private LocalDateTime updatedAt;
}
