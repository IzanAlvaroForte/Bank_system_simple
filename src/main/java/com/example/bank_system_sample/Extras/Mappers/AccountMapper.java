package com.example.bank_system_sample.Extras.Mappers;


import com.example.bank_system_sample.DTO.Request.AccountRequest.AccountOpenRequest;

import com.example.bank_system_sample.DTO.Response.AccountResponse.AccountListResponse;
import com.example.bank_system_sample.DTO.Response.AccountResponse.AccountOpenResponse;
import com.example.bank_system_sample.DTO.Response.AccountResponse.AccountStatusUpdateResponse;
import com.example.bank_system_sample.DTO.Response.AccountResponse.AccountViewResponse;
import com.example.bank_system_sample.Entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    //    ToEntity
    public Account accountOpenToEntity
    (AccountOpenRequest openRequest) {
        return Account.builder()
                .accountType(openRequest.getAccountType())
                .build();
    }

    //    Responses
    public AccountListResponse accountListToResponse
            (Account listAccount) {

        return AccountListResponse.builder()
                .accountCode(listAccount.getAccountCode())
                .accountType(listAccount.getAccountType())
                .accountStatus(listAccount.getAccountStatus())
                .balance(listAccount.getBalance())
                .createdAt(listAccount.getCreatedAt())
                .build();
    }

    public AccountViewResponse accountViewToResponse
            (Account viewAccount) {
        return AccountViewResponse.builder()
                .accountCode(viewAccount.getAccountCode())
                .accountType(viewAccount.getAccountType())
                .accountStatus(viewAccount.getAccountStatus())
                .balance(viewAccount.getBalance())
                .createdAt(viewAccount.getCreatedAt())
                .build();
    }

    public AccountStatusUpdateResponse statusUpdateToResponse
            (Account statusUpdate) {

        return AccountStatusUpdateResponse.builder()
                .accountStatus(statusUpdate.getAccountStatus())
                .confirmationMessage("Status updated successful")
                .updatedAt(statusUpdate.getUpdatedAt())
                .build();
    }

    public AccountOpenResponse openToResponse(
            Account openAccount) {
        return AccountOpenResponse.builder()
                .accountCode(openAccount.getAccountCode())
                .accountType(openAccount.getAccountType())
                .accountStatus(openAccount.getAccountStatus())
                .balance(openAccount.getBalance())
                .createdAt(openAccount.getCreatedAt())
                .build();
    }

}
