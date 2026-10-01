package com.example.bank_system_sample.Extras.ExceptionHandlers.CustomerHandler;

import com.example.bank_system_sample.DTO.Response.ErrorResponse.ErrorResponseDTO;
import com.example.bank_system_sample.Extras.ExceptionHandlers.CustomerHandler.Exceptions.CustomerEmailAlreadyExistsException;
import com.example.bank_system_sample.Extras.ExceptionHandlers.CustomerHandler.Exceptions.CustomerNotFoundException;
import com.example.bank_system_sample.Extras.ExceptionHandlers.CustomerHandler.Exceptions.InvalidCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomerGlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleCustomerNotFound
            (CustomerNotFoundException ex) {

        ErrorResponseDTO customerNotFoundResponse = new ErrorResponseDTO(
                LocalDateTime.now(),
                ex.getMessage(),
                HttpStatus.NOT_FOUND.toString()
        );
        return ResponseEntity.status(404).body(customerNotFoundResponse);
    }

    @ExceptionHandler(CustomerEmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> emailAlreadyExists
            (CustomerEmailAlreadyExistsException ex) {

        ErrorResponseDTO emailAlreadyExistResponse = new ErrorResponseDTO(
                LocalDateTime.now(),
                ex.getMessage(),
                HttpStatus.CONFLICT.toString()
        );

        return ResponseEntity.status(409).body(emailAlreadyExistResponse);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponseDTO> invalidCredentials
            (InvalidCredentialsException ex) {

        ErrorResponseDTO invalidCredentialsResponse = new ErrorResponseDTO(
                LocalDateTime.now(),
                ex.getMessage(),
                HttpStatus.UNAUTHORIZED.toString()
        );

        return ResponseEntity.status(401).body(invalidCredentialsResponse);
    }
}
