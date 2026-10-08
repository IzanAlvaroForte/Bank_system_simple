package com.example.bank_system_sample.Controller;

import com.example.bank_system_sample.DTO.Request.CustomerRequest.CustomerChangedPassRequest;
import com.example.bank_system_sample.DTO.Request.CustomerRequest.CustomerLoginRequest;
import com.example.bank_system_sample.DTO.Request.CustomerRequest.CustomerRegisterRequest;
import com.example.bank_system_sample.DTO.Request.CustomerRequest.CustomerUpdateRequest;
import com.example.bank_system_sample.DTO.Response.CustomerResponse.*;
import com.example.bank_system_sample.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<CustomerRegisterResponse> registerCustomer
            (@Valid @RequestBody CustomerRegisterRequest customerRegisterRequest) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerService.customerRegisterResponse(customerRegisterRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<CustomerLoginResponse> loginCustomer
            (@Valid @RequestBody CustomerLoginRequest customerLoginRequest) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(customerService.customerLoginResponse(customerLoginRequest));
    }

    @PutMapping("/{customerCode}")
    public ResponseEntity<CustomerUpdateResponse> updateCustomer
            (@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest,
             @PathVariable String customerCode) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(customerService.customerUpdateResponse(customerUpdateRequest, customerCode));
    }

    @PatchMapping("/{customerCode}/password")
    public ResponseEntity<CustomerChangedPassResponse> changedPassCustomer
            (@Valid @RequestBody CustomerChangedPassRequest customerChangedPassRequest,
             @PathVariable String customerCode) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(customerService.customerChangedPassResponse(customerChangedPassRequest, customerCode));
    }

    @GetMapping
    public ResponseEntity<Page<CustomerSummaryResponse>> getAllCustomers
            (@PageableDefault(size = 10,
                                page = 0,
                                sort = "createdAt",
                                direction = Sort.Direction.DESC) Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(customerService.getAllCustomers(pageable));
    }

    @GetMapping("/{customerCode}")
    public ResponseEntity<CustomerViewProfileResponse> viewProfileCustomer
            (@PathVariable String customerCode) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(customerService.getCustomerByCode(customerCode));
    }

}
