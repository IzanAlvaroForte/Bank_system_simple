package com.example.bank_system_sample.Repository;

import com.example.bank_system_sample.Entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findByTransactionCode(String transactionCode);
    Page<Transaction> findByFromAccountIdOrToAccountId(Long fromAccountId,
                                                       Long toAccountId,
                                                       Pageable pageable);

    boolean existsByTransactionCode(String transactionCode);

}
