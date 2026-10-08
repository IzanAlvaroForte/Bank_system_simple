package com.example.bank_system_sample.Controller;

import com.example.bank_system_sample.DTO.Request.AccountRequest.AccountOpenRequest;
import com.example.bank_system_sample.DTO.Request.AccountRequest.AccountStatusUpdateRequest;
import com.example.bank_system_sample.DTO.Response.AccountResponse.AccountListResponse;
import com.example.bank_system_sample.DTO.Response.AccountResponse.AccountOpenResponse;
import com.example.bank_system_sample.DTO.Response.AccountResponse.AccountStatusUpdateResponse;
import com.example.bank_system_sample.DTO.Response.AccountResponse.AccountViewResponse;
import com.example.bank_system_sample.Service.AccountService;
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
@RequestMapping(value = "/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/{customerCode}")
    public ResponseEntity<AccountOpenResponse> openAccount
            (@Valid @RequestBody AccountOpenRequest accountOpenRequest,
             @PathVariable String customerCode) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(accountService.accountOpenResponse(accountOpenRequest, customerCode));
    }

    @PatchMapping("/{accountCode}/status")
    public ResponseEntity<AccountStatusUpdateResponse> statusUpdate
            (@Valid @RequestBody AccountStatusUpdateRequest  accountStatusUpdateRequest,
             @PathVariable String accountCode) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.accountStatusUpdateResponse(
                        accountStatusUpdateRequest,
                        accountCode
                ));
    }

    @GetMapping("/{accountCode}")
    public ResponseEntity<AccountViewResponse> viewAccount
            (@PathVariable String accountCode) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.getAccountByCode(accountCode));
    }

    @GetMapping("/customer/{customerCode}")
    public ResponseEntity<Page<AccountListResponse>> listAccounts
            (@PageableDefault
                     (size = 10,
                         page = 0,
                         sort = "createdAt",
                         direction = Sort.Direction.DESC) Pageable pageable,
             @PathVariable String customerCode) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.getAllAccounts(
                        pageable,
                        customerCode
                ));
    }
}
