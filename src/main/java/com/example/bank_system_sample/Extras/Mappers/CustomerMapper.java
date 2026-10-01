package com.example.bank_system_sample.Extras.Mappers;

import com.example.bank_system_sample.DTO.Request.CustomerRequest.CustomerRegisterRequest;
import com.example.bank_system_sample.DTO.Response.CustomerResponse.*;
import com.example.bank_system_sample.Entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

//    Request
    public Customer registerToEntity (CustomerRegisterRequest registerRequest) {

        return Customer.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .phone(registerRequest.getPhone())
                .build();
    }

//    Responses
    public CustomerChangedPassResponse changedPassToResponse (Customer changedPassEntity) {

        return CustomerChangedPassResponse.builder()
                .confirmationMessage("Password changed successful")
                .updatedAt(changedPassEntity.getUpdatedAt())
                .build();
    }

    public CustomerLoginResponse loginToResponse (Customer loginEntity) {

        return CustomerLoginResponse.builder()
                .firstName(loginEntity.getFirstName())
                .lastName(loginEntity.getLastName())
                .email(loginEntity.getEmail())
                .customerCode(loginEntity.getCustomerCode())
                .confirmationMessage("Login successful")
                .build();
    }

    public CustomerRegisterResponse registerToResponse (Customer registerEntity) {

        return CustomerRegisterResponse.builder()
                .firstName(registerEntity.getFirstName())
                .lastName(registerEntity.getLastName())
                .customerCode(registerEntity.getCustomerCode())
                .email(registerEntity.getEmail())
                .createdAt(registerEntity.getCreatedAt())
                .confirmationMessage("Registered successful")
                .build();
    }

    public CustomerSummaryResponse summaryToResponse (Customer summaryEntity) {

        return CustomerSummaryResponse.builder()
                .customerCode(summaryEntity.getCustomerCode())
                .firstName(summaryEntity.getFirstName())
                .lastName(summaryEntity.getLastName())
                .email(summaryEntity.getEmail())
                .build();
    }

    public CustomerUpdateResponse updateToResponse (Customer updateEntity) {

        return CustomerUpdateResponse.builder()
                .firstName(updateEntity.getFirstName())
                .lastName(updateEntity.getLastName())
                .email(updateEntity.getEmail())
                .phone(updateEntity.getPhone())
                .confirmationMessage("Updated successful")
                .updatedAt(updateEntity.getUpdatedAt())
                .build();
    }

    public CustomerViewProfileResponse viewProfToResponse (Customer viewProfileEntity) {

        return CustomerViewProfileResponse.builder()
                .customerCode(viewProfileEntity.getCustomerCode())
                .firstName(viewProfileEntity.getFirstName())
                .lastName(viewProfileEntity.getLastName())
                .email(viewProfileEntity.getEmail())
                .phone(viewProfileEntity.getPhone())
                .build();
    }
}
