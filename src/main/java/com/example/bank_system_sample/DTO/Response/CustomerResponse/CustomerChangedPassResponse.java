package com.example.bank_system_sample.DTO.Response.CustomerResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerChangedPassResponse {

    private String confirmationMessage;
    private LocalDateTime updatedAt;
}
