package com.example.bank_system_sample.Service;

import com.example.bank_system_sample.DTO.Request.TransactionRequest.TransactionDepositRequest;
import com.example.bank_system_sample.DTO.Request.TransactionRequest.TransactionTransferRequest;
import com.example.bank_system_sample.DTO.Request.TransactionRequest.TransactionWithdrawRequest;
import com.example.bank_system_sample.DTO.Response.TransactionResponse.*;
import com.example.bank_system_sample.Entity.*;
import com.example.bank_system_sample.Extras.CodeGenerators;
import com.example.bank_system_sample.Extras.ExceptionHandlers.AccountHandler.Exceptions.AccountNotFoundException;
import com.example.bank_system_sample.Extras.ExceptionHandlers.TransactionHandler.Exceptions.TransactionNotFoundException;
import com.example.bank_system_sample.Extras.Mappers.TransactionMapper;
import com.example.bank_system_sample.Repository.AccountRepository;
import com.example.bank_system_sample.Repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionMapper transactionMapper;
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final CodeGenerators codeGenerators;

    @Transactional
    public TransactionDepositResponse transactionDepositResponse
            (TransactionDepositRequest transactionDepositRequest,
             String accountCode) {

        Account account = accountRepository.findByAccountCode(accountCode)
                .orElseThrow(() -> new AccountNotFoundException(accountCode));

        if (transactionDepositRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }

        if (account.getAccountStatus() != AccountStatus.ACTIVE) {
            throw new RuntimeException(accountCode);
        }

        account.setBalance(account.getBalance().add(transactionDepositRequest.getAmount()));
        account.setLastTransactionAt(LocalDateTime.now());
        accountRepository.save(account);

        Transaction transaction = Transaction.builder()
                .transactionCode(codeGenerators.transactionCode())
                .transactionType(TransactionType.DEPOSIT)
                .transactionStatus(TransactionStatus.SUCCESS)
                .toAccount(account)
                .amount(transactionDepositRequest.getAmount())
                .build();

        transactionRepository.save(transaction);
        return transactionMapper.depositToResponse(transaction);

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
