package com.example.bank_system_sample.Repository;

import com.example.bank_system_sample.Entity.Account;
import com.example.bank_system_sample.Entity.AccountStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountCode(String accountCode);
    Boolean existsByAccountCode(String accountCode);
    Page<Account> findByCustomerId(Long customerId, Pageable pageable);
    List<Account> findByStatusAndLastTransactionAtBefore(AccountStatus status, LocalDateTime lastTransactionAt);
}
