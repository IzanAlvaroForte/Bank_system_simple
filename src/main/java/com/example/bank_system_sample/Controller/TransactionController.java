package com.example.bank_system_sample.Controller;

import com.example.bank_system_sample.DTO.Request.TransactionRequest.TransactionDepositRequest;
import com.example.bank_system_sample.DTO.Request.TransactionRequest.TransactionTransferRequest;
import com.example.bank_system_sample.DTO.Request.TransactionRequest.TransactionWithdrawRequest;
import com.example.bank_system_sample.DTO.Response.TransactionResponse.*;
import com.example.bank_system_sample.Service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/{accountCode}/deposit")
    public ResponseEntity<TransactionDepositResponse> depositTransaction
            (@Valid @RequestBody TransactionDepositRequest transactionDepositRequest,
             @PathVariable String accountCode) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(transactionService.transactionDepositResponse(
                        transactionDepositRequest,
                        accountCode
                ));
    }


    @PostMapping("/{accountCode}/withdraw")
    public ResponseEntity<TransactionWithdrawResponse> withdrawTransaction(
            @Valid @RequestBody TransactionWithdrawRequest transactionWithdrawRequest,
            @PathVariable String accountCode) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(transactionService.transactionWithdrawResponse(transactionWithdrawRequest, accountCode));


    }

    @PostMapping("/transfer")
    public ResponseEntity<TransactionTransferResponse> transferTransaction(
            @Valid @RequestBody TransactionTransferRequest transactionTransferRequest) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(transactionService.transactionTransferResponse(transactionTransferRequest));
    }

    @GetMapping("/{transactionCode}")
    public ResponseEntity<TransactionDetailResponse> getTransactionDetail
            (@PathVariable String transactionCode) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(transactionService.getTransactionDetail(transactionCode));
    }

    @GetMapping("/{accountCode}/summary")
    public ResponseEntity<Page<TransactionSummaryResponse>> summaryTransaction
            (@PageableDefault(size = 10,
                                 page = 0,
                                 sort = "createdAt",
                                 direction = Sort.Direction.DESC) Pageable pageable,
             @PathVariable String accountCode) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(transactionService.getTransactionHistory(
                        pageable,
                        accountCode
                ));
    }
}
