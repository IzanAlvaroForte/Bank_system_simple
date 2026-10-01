package com.example.bank_system_sample.Extras.Mappers;

import com.example.bank_system_sample.DTO.Response.TransactionResponse.*;
import com.example.bank_system_sample.Entity.Account;
import com.example.bank_system_sample.Entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

//    Response
    public TransactionDepositResponse depositToResponse (Transaction depositEntity) {

        return TransactionDepositResponse.builder()
                .amount(depositEntity.getAmount())
                .transactionCode(depositEntity.getTransactionCode())
                .confirmationMessage("Deposit successful")
                .createdAt(depositEntity.getCreatedAt())
                .build();
    }

    public TransactionWithdrawResponse withdrawToResponse (Transaction withdrawEntity) {

        return TransactionWithdrawResponse.builder()
                .amount(withdrawEntity.getAmount())
                .transactionCode(withdrawEntity.getTransactionCode())
                .confirmationMessage("Withdraw successful")
                .createdAt(withdrawEntity.getCreatedAt())
                .build();
    }

    public TransactionTransferResponse transferToResponse (Transaction transferEntity) {

        return TransactionTransferResponse.builder()
                .amount(transferEntity.getAmount())
                .fromAccountCode(accountCode(transferEntity.getFromAccount()))
                .toAccountCode(accountCode(transferEntity.getToAccount()))
                .transactionCode(transferEntity.getTransactionCode())
                .confirmationMessage("Transfer successful")
                .createdAt(transferEntity.getCreatedAt())
                .build();
    }

    public TransactionSummaryResponse summaryToResponse (Transaction summaryEntity) {

        return TransactionSummaryResponse.builder()
                .transactionCode(summaryEntity.getTransactionCode())
                .transactionType(summaryEntity.getTransactionType())
                .amount(summaryEntity.getAmount())
                .createdAt(summaryEntity.getCreatedAt())
                .build();
    }

    public TransactionDetailResponse detailToResponse (Transaction detailEntity) {

        return TransactionDetailResponse.builder()
                .transactionCode(detailEntity.getTransactionCode())
                .transactionType(detailEntity.getTransactionType())
                .transactionStatus(detailEntity.getTransactionStatus())
                .fromAccountCode(accountCode(detailEntity.getFromAccount()))
                .toAccountCode(accountCode(detailEntity.getToAccount()))
                .amount(detailEntity.getAmount())
                .createdAt(detailEntity.getCreatedAt())
                .build();
    }

    private String accountCode(Account account) {
        return account != null ? account.getAccountCode() : null;
    }

}
