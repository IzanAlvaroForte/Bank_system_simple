package com.example.bank_system_sample.Service;

import com.example.bank_system_sample.DTO.Request.TransactionRequest.TransactionDepositRequest;
import com.example.bank_system_sample.DTO.Request.TransactionRequest.TransactionTransferRequest;
import com.example.bank_system_sample.DTO.Request.TransactionRequest.TransactionWithdrawRequest;
import com.example.bank_system_sample.DTO.Response.TransactionResponse.*;
import com.example.bank_system_sample.Entity.*;
import com.example.bank_system_sample.Extras.CodeGenerators;
import com.example.bank_system_sample.Extras.ExceptionHandlers.AccountHandler.Exceptions.AccountNotActiveException;
import com.example.bank_system_sample.Extras.ExceptionHandlers.AccountHandler.Exceptions.AccountNotFoundException;
import com.example.bank_system_sample.Extras.ExceptionHandlers.AccountHandler.Exceptions.InsufficientFundsException;
import com.example.bank_system_sample.Extras.ExceptionHandlers.TransactionHandler.Exceptions.InvalidAmountException;
import com.example.bank_system_sample.Extras.ExceptionHandlers.TransactionHandler.Exceptions.TransactionNotFoundException;
import com.example.bank_system_sample.Extras.Mappers.TransactionMapper;
import com.example.bank_system_sample.Repository.AccountRepository;
import com.example.bank_system_sample.Repository.TransactionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
            throw new InvalidAmountException("Amount must be greater than 0");
        }

        if (account.getAccountStatus() != AccountStatus.ACTIVE) {
            throw new RuntimeException(accountCode);
        }

        account.setBalance(account.getBalance().add(transactionDepositRequest.getAmount()));
        account.setLastTransactionAt(LocalDateTime.now());
        accountRepository.save(account);

        String code;
        do {
            code =codeGenerators.transactionCode();
        } while (transactionRepository.existsByTransactionCode(code));

        Transaction transaction = Transaction.builder()
                .transactionCode(code)
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
            (TransactionWithdrawRequest transactionWithdrawRequest,
             String accountCode) {
        Account account = accountRepository.findByAccountCode(accountCode)
                .orElseThrow(() -> new AccountNotFoundException(accountCode));

        if (transactionWithdrawRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Amount must be greater than zero");
        }

        if (account.getBalance().compareTo(transactionWithdrawRequest.getAmount()) < 0) {
            throw new InsufficientFundsException(transactionWithdrawRequest.getAmount());
        }

        if (account.getAccountStatus() != AccountStatus.ACTIVE) {
            throw new AccountNotActiveException(accountCode);
        }



        account.setBalance(account.getBalance().subtract(transactionWithdrawRequest.getAmount()));
        account.setLastTransactionAt(LocalDateTime.now());
        accountRepository.save(account);

        String code;
        do {
            code = codeGenerators.transactionCode();
        } while (transactionRepository.existsByTransactionCode(code));

        Transaction transaction = Transaction.builder()
                .transactionCode(code)
                .transactionType(TransactionType.WITHDRAW)
                .transactionStatus(TransactionStatus.SUCCESS)
                .fromAccount(account)
                .amount(transactionWithdrawRequest.getAmount())
                .build();

        transactionRepository.save(transaction);
        return transactionMapper.withdrawToResponse(transaction);


    }

    @Transactional
    public TransactionTransferResponse transactionTransferResponse
            (TransactionTransferRequest transactionTransferRequest) {
        Account fromAccount = accountRepository.findByAccountCode(transactionTransferRequest.getFromAccountCode())
                .orElseThrow(() -> new AccountNotFoundException(transactionTransferRequest.getFromAccountCode()));
        Account toAccount = accountRepository.findByAccountCode(transactionTransferRequest.getToAccountCode())
                .orElseThrow(() -> new AccountNotFoundException(transactionTransferRequest.getToAccountCode()));

        if (transactionTransferRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Amount must be greater than zero");
        }

        if (fromAccount.getBalance().compareTo(transactionTransferRequest.getAmount()) < 0) {
            throw new InsufficientFundsException(transactionTransferRequest.getAmount());
        }

        if (fromAccount.getAccountCode().equals(toAccount.getAccountCode())) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

        if (fromAccount.getAccountStatus() != AccountStatus.ACTIVE) {
            throw new AccountNotActiveException(fromAccount.getAccountCode());
        }
        if (toAccount.getAccountStatus() != AccountStatus.ACTIVE) {
            throw new AccountNotActiveException(toAccount.getAccountCode());
        }


        String code;
        do {
            code = codeGenerators.transactionCode();
        } while (transactionRepository.existsByTransactionCode(code));

        fromAccount.setBalance(fromAccount.getBalance().subtract(transactionTransferRequest.getAmount()));
        fromAccount.setLastTransactionAt(LocalDateTime.now());

        toAccount.setBalance(toAccount.getBalance().add(transactionTransferRequest.getAmount()));
        toAccount.setLastTransactionAt(LocalDateTime.now());

        accountRepository.saveAll(List.of(fromAccount, toAccount));

        Transaction transaction = Transaction.builder()
                .transactionCode(code)
                .transactionType(TransactionType.TRANSFER)
                .transactionStatus(TransactionStatus.SUCCESS)
                .fromAccount(fromAccount)
                .toAccount(toAccount)
                .amount(transactionTransferRequest.getAmount())
                .build();

        transactionRepository.save(transaction);
        return transactionMapper.transferToResponse(transaction);
    }

    @Transactional(readOnly = true)
    public TransactionDetailResponse getTransactionDetail
            (String transactionCode) {
        Transaction transaction = transactionRepository.findByTransactionCode(transactionCode)
                .orElseThrow(() -> new TransactionNotFoundException(transactionCode));
        return transactionMapper.detailToResponse(transaction);
    }

    @Transactional(readOnly = true)
    public Page<TransactionSummaryResponse> getTransactionHistory(
            String accountCode, Pageable pageable) {

        Account account = accountRepository.findByAccountCode(accountCode)
                .orElseThrow(() -> new AccountNotFoundException(accountCode));

        return transactionRepository
                .findByFromAccountIdOrAccountId(account.getId(), account.getId(), pageable)
                .map(transactionMapper::summaryToResponse);
    }
}
