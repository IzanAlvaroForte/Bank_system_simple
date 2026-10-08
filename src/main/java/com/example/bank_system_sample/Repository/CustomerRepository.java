package com.example.bank_system_sample.Repository;

import com.example.bank_system_sample.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByCustomerCode(String customerCode);
    Optional<Customer> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByCustomerCode(String customerCode);
}
