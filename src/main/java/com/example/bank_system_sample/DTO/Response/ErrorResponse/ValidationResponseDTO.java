package com.example.bank_system_sample.DTO.Response.ErrorResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationResponseDTO {

    private LocalDateTime timestamp;
    private int status;
    private Map<String, String> errors;
}
