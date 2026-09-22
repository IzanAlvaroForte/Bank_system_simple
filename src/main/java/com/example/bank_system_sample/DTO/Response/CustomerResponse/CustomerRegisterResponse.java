package com.example.bank_system_sample.DTO.Response.CustomerResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRegisterResponse {

    private String firstName;
    private String lastName;
    private String customerCode;
    private String email;
    private LocalDateTime createdAt;
    private String confirmationMessage;
}
