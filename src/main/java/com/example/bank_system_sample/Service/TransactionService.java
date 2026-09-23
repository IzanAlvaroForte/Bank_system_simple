package com.example.bank_system_sample.Service;

import com.example.bank_system_sample.DTO.Request.TransactionRequest.TransactionDepositRequest;
import com.example.bank_system_sample.DTO.Request.TransactionRequest.TransactionTransferRequest;
import com.example.bank_system_sample.DTO.Request.TransactionRequest.TransactionWithdrawRequest;
import com.example.bank_system_sample.DTO.Response.TransactionResponse.*;
import com.example.bank_system_sample.Entity.Transaction;
import com.example.bank_system_sample.Extras.Mappers.TransactionMapper;
import com.example.bank_system_sample.Repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private TransactionMapper transactionMapper;
    private TransactionRepository transactionRepository;

    @Transactional
    public TransactionDepositResponse transactionDepositResponse
            (TransactionDepositRequest transactionDepositRequest) {
        return new TransactionDepositResponse();
    }

    @Transactional
    public TransactionWithdrawResponse transactionWithdrawResponse
            (TransactionWithdrawRequest transactionWithdrawRequest) {
        return new TransactionWithdrawResponse();
    }

    @Transactional
    public TransactionTransferResponse transactionTransferResponse
            (TransactionTransferRequest transactionTransferRequest) {
        return new TransactionTransferResponse();
    }

    public TransactionDetailResponse getTransactionDetail
            (String transactionCode) {
        Transaction Transaction = transactionRepository.findByTransactionCode(transactionCode)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        return new TransactionDetailResponse();
    }

    public Page<TransactionSummaryResponse> getAllTransactionSummary
            (int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return transactionRepository.findAll(pageable)
                .map(transaction -> new TransactionSummaryResponse(
                        transaction.getTransactionCode(),
                        transaction.getTransactionType(),
                        transaction.getAmount(),
                        transaction.getCreatedAt()
                ));
    }


}
