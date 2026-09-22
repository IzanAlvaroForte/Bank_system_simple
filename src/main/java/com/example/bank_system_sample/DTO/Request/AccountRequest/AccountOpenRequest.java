package com.example.bank_system_sample.DTO.Request.AccountRequest;
import com.example.bank_system_sample.Entity.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountOpenRequest {

    @NotNull(message = "Account type is required")
    private AccountType accountType;
}
