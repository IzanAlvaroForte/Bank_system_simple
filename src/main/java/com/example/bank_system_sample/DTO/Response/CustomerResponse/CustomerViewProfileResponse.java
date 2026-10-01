package com.example.bank_system_sample.DTO.Response.CustomerResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerViewProfileResponse {

    private String customerCode;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
