package com.example.bank_system_sample.DTO.Response.CustomerResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSummaryResponse {

    private String customerCode;
    private String firstName;
    private String lastName;
    private String email;
}
