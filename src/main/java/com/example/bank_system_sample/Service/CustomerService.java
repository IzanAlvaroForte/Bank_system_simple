package com.example.bank_system_sample.Service;

import com.example.bank_system_sample.DTO.Request.CustomerRequest.CustomerChangedPassRequest;
import com.example.bank_system_sample.DTO.Request.CustomerRequest.CustomerLoginRequest;
import com.example.bank_system_sample.DTO.Request.CustomerRequest.CustomerRegisterRequest;
import com.example.bank_system_sample.DTO.Request.CustomerRequest.CustomerUpdateRequest;
import com.example.bank_system_sample.DTO.Response.CustomerResponse.*;
import com.example.bank_system_sample.Entity.Customer;
import com.example.bank_system_sample.Extras.CodeGenerators;
import com.example.bank_system_sample.Extras.ExceptionHandlers.CustomerHandler.Exceptions.CustomerEmailAlreadyExistsException;
import com.example.bank_system_sample.Extras.ExceptionHandlers.CustomerHandler.Exceptions.CustomerNotFoundException;
import com.example.bank_system_sample.Extras.ExceptionHandlers.CustomerHandler.Exceptions.InvalidCredentialsException;
import com.example.bank_system_sample.Extras.Mappers.CustomerMapper;
import com.example.bank_system_sample.Repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final CodeGenerators codeGenerator;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public CustomerRegisterResponse customerRegisterResponse
            (CustomerRegisterRequest customerRegisterRequest) {

        if (customerRepository.existsByEmail(customerRegisterRequest.getEmail())) {
            throw new CustomerEmailAlreadyExistsException(customerRegisterRequest.getEmail());
        }

        Customer customer = customerMapper.registerToEntity(customerRegisterRequest);

        String code;
        do {
            code = codeGenerator.codeForCustomer();
        } while (customerRepository.existsByCustomerCode(code));
        customer.setCustomerCode(code);

        String hashedPassword = passwordEncoder.encode(customerRegisterRequest.getPassword());
        customer.setPassword(hashedPassword);

        customerRepository.save(customer);
        return customerMapper.registerToResponse(customer);
    }

    @Transactional(readOnly = true)
    public CustomerLoginResponse customerLoginResponse
            (CustomerLoginRequest customerLoginRequest) {
        Customer customer = customerRepository.findByEmail(customerLoginRequest.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException());

        if(!passwordEncoder.matches(customerLoginRequest.getPassword(), customer.getPassword())) {
            throw new InvalidCredentialsException();
        }

        return customerMapper.loginToResponse(customer);
    }

    @Transactional
    public CustomerUpdateResponse customerUpdateResponse
            (CustomerUpdateRequest customerUpdateRequest, String customerCode) {
        Customer customer = customerRepository.findByCustomerCode(customerCode)
                .orElseThrow(() -> new CustomerNotFoundException(customerCode));

        customer.setFirstName(customerUpdateRequest.getFirstName());
        customer.setLastName(customerUpdateRequest.getLastName());
        customer.setEmail(customerUpdateRequest.getEmail());
        customer.setPhone(customerUpdateRequest.getPhone());

        customerRepository.save(customer);

        return customerMapper.updateToResponse(customer);
    }

    @Transactional
    public CustomerChangedPassResponse customerChangedPassResponse
            (CustomerChangedPassRequest customerChangedPassRequest, String customerCode) {
        Customer customer = customerRepository.findByCustomerCode(customerCode)
                .orElseThrow(() -> new CustomerNotFoundException(customerCode));

        if(!passwordEncoder.matches(customerChangedPassRequest.getCurrentPassword(), customer.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String hashedPassword = passwordEncoder.encode(customerChangedPassRequest.getNewPassword());
        customer.setPassword(hashedPassword);

        customerRepository.save(customer);
        return customerMapper.changedPassToResponse(customer);
    }

    @Transactional(readOnly = true)
    public Page<CustomerSummaryResponse> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable)
                .map(customerMapper::summaryToResponse);
    }

    @Transactional(readOnly = true)
    public CustomerViewProfileResponse getCustomerByCode(String customerCode) {
        Customer customer = customerRepository.findByCustomerCode(customerCode)
                .orElseThrow(() -> new CustomerNotFoundException(customerCode));
        return customerMapper.viewProfToResponse(customer);
    }
}
