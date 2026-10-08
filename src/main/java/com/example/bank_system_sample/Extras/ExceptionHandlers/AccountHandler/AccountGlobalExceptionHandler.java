package com.example.bank_system_sample.Extras.ExceptionHandlers.AccountHandler;

import com.example.bank_system_sample.DTO.Response.ErrorResponse.ErrorResponseDTO;
import com.example.bank_system_sample.Extras.ExceptionHandlers.AccountHandler.Exceptions.AccountNotActiveException;
import com.example.bank_system_sample.Extras.ExceptionHandlers.AccountHandler.Exceptions.AccountNotFoundException;
import com.example.bank_system_sample.Extras.ExceptionHandlers.AccountHandler.Exceptions.InsufficientFundsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class AccountGlobalExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> accountNotFound
            (AccountNotFoundException ex) {

        ErrorResponseDTO accountNotFoundResponse = new ErrorResponseDTO(
                LocalDateTime.now(),
                ex.getMessage(),
                HttpStatus.NOT_FOUND.toString()
        );

        return ResponseEntity.status(404).body(accountNotFoundResponse);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<ErrorResponseDTO> insufficientFunds
            (InsufficientFundsException ex) {

        ErrorResponseDTO lackFundsResponse = new ErrorResponseDTO(
                LocalDateTime.now(),
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.toString()
        );

        return ResponseEntity.status(400).body(lackFundsResponse);
    }

    public ResponseEntity<ErrorResponseDTO> accountNotActive
            (AccountNotActiveException ex) {

        ErrorResponseDTO accountNotActiveResponse = new ErrorResponseDTO(
                LocalDateTime.now(),
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.toString()
        );

        return ResponseEntity.status(400).body(accountNotActiveResponse);
    }
}
