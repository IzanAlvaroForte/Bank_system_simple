package com.example.bank_system_sample.Service;

import com.example.bank_system_sample.DTO.Request.CustomerRequest.CustomerChangedPassRequest;
import com.example.bank_system_sample.DTO.Request.CustomerRequest.CustomerLoginRequest;
import com.example.bank_system_sample.DTO.Request.CustomerRequest.CustomerRegisterRequest;
import com.example.bank_system_sample.DTO.Request.CustomerRequest.CustomerUpdateRequest;
import com.example.bank_system_sample.DTO.Response.CustomerResponse.*;
import com.example.bank_system_sample.Entity.Customer;
import com.example.bank_system_sample.Extras.Mappers.CustomerMapper;
import com.example.bank_system_sample.Repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private CustomerMapper customerMapper;
    private CustomerRepository customerRepository;

    @Transactional
    public CustomerRegisterResponse customerRegisterResponse
            (CustomerRegisterRequest customerRegisterRequest) {
        return new CustomerRegisterResponse();
    }

    @Transactional
    public CustomerLoginResponse customerLoginResponse
            (CustomerLoginRequest customerLoginRequest) {
        return new CustomerLoginResponse();
    }

    @Transactional
    public CustomerUpdateResponse customerUpdateResponse
            (CustomerUpdateRequest customerUpdateRequest) {
        return new CustomerUpdateResponse();
    }

    @Transactional
    public CustomerChangedPassResponse customerChangedPassResponse
            (CustomerChangedPassRequest customerChangedPassRequest) {
        return new CustomerChangedPassResponse();
    }

    public Page<CustomerSummaryResponse> getAllCustomers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return customerRepository.findAll(pageable)
                .map(customer -> new CustomerSummaryResponse(
                        customer.getCustomerCode(),
                        customer.getFirstName(),
                        customer.getLastName(),
                        customer.getEmail()
                ));
    }

    public CustomerViewProfileResponse getCustomerByCode(String customerCode) {
        Customer customer = customerRepository.findByCustomerCode(customerCode)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return new CustomerViewProfileResponse();
    }
}
