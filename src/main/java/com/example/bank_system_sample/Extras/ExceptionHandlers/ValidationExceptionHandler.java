package com.example.bank_system_sample.Extras.ExceptionHandlers;

import com.example.bank_system_sample.DTO.Response.ErrorResponse.ValidationResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationResponseDTO> validationHandler
            (MethodArgumentNotValidException ex) {

        Map<String, String> errors = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
                errors.put(fieldError.getField(), fieldError.getDefaultMessage())
        );

        ValidationResponseDTO validationResponseDTO = new ValidationResponseDTO(
                LocalDateTime.now(),
                400,
                errors
        );

        return ResponseEntity.status(400).body(validationResponseDTO);
    }
}
