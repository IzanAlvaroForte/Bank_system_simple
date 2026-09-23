package com.example.bank_system_sample.Service;

import com.example.bank_system_sample.DTO.Request.AccountRequest.AccountOpenRequest;
import com.example.bank_system_sample.DTO.Request.AccountRequest.AccountStatusUpdateRequest;
import com.example.bank_system_sample.DTO.Response.AccountResponse.AccountListResponse;
import com.example.bank_system_sample.DTO.Response.AccountResponse.AccountOpenResponse;
import com.example.bank_system_sample.DTO.Response.AccountResponse.AccountStatusUpdateResponse;
import com.example.bank_system_sample.DTO.Response.AccountResponse.AccountViewResponse;
import com.example.bank_system_sample.Entity.Account;
import com.example.bank_system_sample.Extras.Mappers.AccountMapper;
import com.example.bank_system_sample.Repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private AccountMapper accountMapper;
    private AccountRepository accountRepository;

    @Transactional
    public AccountOpenResponse accountOpenResponse
            (AccountOpenRequest accountOpenRequest) {
        return new AccountOpenResponse();
    }

    @Transactional
    public AccountStatusUpdateResponse accountStatusUpdateResponse
            (AccountStatusUpdateRequest accountStatusUpdateRequest) {
        return new AccountStatusUpdateResponse();
    }

    public AccountViewResponse getAccountByCode(String accountCode) {
        Account account = accountRepository.findByAccountCode(accountCode)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return new AccountViewResponse();
    }

    public Page<AccountListResponse> getAllAccount(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return accountRepository.findAll(pageable)
                .map(account -> new AccountListResponse(
                        account.getAccountCode(),
                        account.getAccountType(),
                        account.getAccountStatus(),
                        account.getBalance(),
                        account.getCreatedAt()
                ));
    }

}
