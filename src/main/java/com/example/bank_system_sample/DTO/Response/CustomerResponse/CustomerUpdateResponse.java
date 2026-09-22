package com.example.bank_system_sample.DTO.Response.CustomerResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUpdateResponse {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String confirmationMessage;
    private LocalDateTime updatedAt;
}
