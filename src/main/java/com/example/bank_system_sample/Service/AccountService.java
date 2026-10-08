package com.example.bank_system_sample.Service;

import com.example.bank_system_sample.DTO.Request.AccountRequest.AccountOpenRequest;
import com.example.bank_system_sample.DTO.Request.AccountRequest.AccountStatusUpdateRequest;
import com.example.bank_system_sample.DTO.Response.AccountResponse.AccountListResponse;
import com.example.bank_system_sample.DTO.Response.AccountResponse.AccountOpenResponse;
import com.example.bank_system_sample.DTO.Response.AccountResponse.AccountStatusUpdateResponse;
import com.example.bank_system_sample.DTO.Response.AccountResponse.AccountViewResponse;
import com.example.bank_system_sample.Entity.Account;
import com.example.bank_system_sample.Entity.AccountStatus;
import com.example.bank_system_sample.Entity.Customer;
import com.example.bank_system_sample.Extras.CodeGenerators;
import com.example.bank_system_sample.Extras.ExceptionHandlers.AccountHandler.Exceptions.AccountNotFoundException;
import com.example.bank_system_sample.Extras.ExceptionHandlers.CustomerHandler.Exceptions.CustomerNotFoundException;
import com.example.bank_system_sample.Extras.Mappers.AccountMapper;
import com.example.bank_system_sample.Repository.AccountRepository;
import com.example.bank_system_sample.Repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;
    private final CodeGenerators codeGenerators;
    private final CustomerRepository customerRepository;

    @Transactional
    public AccountOpenResponse accountOpenResponse
            (AccountOpenRequest accountOpenRequest, String customerCode) {

        Customer customer = customerRepository.findByCustomerCode(customerCode)
                .orElseThrow(() -> new CustomerNotFoundException(customerCode));

        Account account = accountMapper.accountOpenToEntity(accountOpenRequest);
        account.setCustomer(customer);
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setBalance(BigDecimal.ZERO);

        String prefix = account.getAccountType().getPrefix();
        String code;
        do {
            code = codeGenerators.accountCode(prefix);
        } while (accountRepository.existsByAccountCode(code));
        account.setAccountCode(code);

        accountRepository.save(account);
        return accountMapper.openToResponse(account);
    }

    @Transactional
    public AccountStatusUpdateResponse accountStatusUpdateResponse
            (AccountStatusUpdateRequest accountStatusUpdateRequest,
             String accountCode) {

        Account account = accountRepository.findByAccountCode(accountCode)
                .orElseThrow(() -> new AccountNotFoundException(accountCode));
        account.setAccountStatus(accountStatusUpdateRequest.getAccountStatus());

        if (accountStatusUpdateRequest.getAccountStatus() == AccountStatus.CLOSED) {
            account.setClosedAt(LocalDateTime.now());
        }
        accountRepository.save(account);
        return accountMapper.statusUpdateToResponse(account);
    }

    @Transactional(readOnly = true)
    public AccountViewResponse getAccountByCode
            (String accountCode) {

        Account account = accountRepository.findByAccountCode(accountCode)
                .orElseThrow(() -> new AccountNotFoundException(accountCode));

        return accountMapper.accountViewToResponse(account);
    }

    @Transactional(readOnly = true)
    public Page<AccountListResponse> getAllAccounts(
            Pageable pageable,
            String customerCode) {
        Customer customer = customerRepository.findByCustomerCode(customerCode)
                .orElseThrow(() -> new CustomerNotFoundException(customerCode));

        return accountRepository.findByCustomerId(customer.getId(), pageable)
                .map(accountMapper::accountListToResponse);
    }

}
