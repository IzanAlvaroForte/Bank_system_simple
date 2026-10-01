package com.example.bank_system_sample.Extras.ExceptionHandlers.TransactionHandler;

import com.example.bank_system_sample.DTO.Response.ErrorResponse.ErrorResponseDTO;
import com.example.bank_system_sample.Extras.ExceptionHandlers.TransactionHandler.Exceptions.InvalidAmountException;
import com.example.bank_system_sample.Extras.ExceptionHandlers.TransactionHandler.Exceptions.TransactionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class TransactionGlobalExceptionHandler {

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> transactionNotFound
            (TransactionNotFoundException ex) {

        ErrorResponseDTO transactionNotFoundResponse = new ErrorResponseDTO(
                LocalDateTime.now(),
                ex.getMessage(),
                HttpStatus.NOT_FOUND.toString()
        );

        return ResponseEntity.status(404).body(transactionNotFoundResponse);
    }

    @ExceptionHandler(InvalidAmountException.class)
    public ResponseEntity<ErrorResponseDTO> invalidAmount
            (InvalidAmountException ex) {

        ErrorResponseDTO invalidAmountResponse = new ErrorResponseDTO(
                LocalDateTime.now(),
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.toString()
        );

        return ResponseEntity.status(400).body(invalidAmountResponse);
    }
}
