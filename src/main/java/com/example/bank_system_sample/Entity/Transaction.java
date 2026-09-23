package com.example.bank_system_sample.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table (name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "from_account_id")
    private Account fromAccount;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "to_account_id")
    private Account toAccount;

    @Column (name = "amount", nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column (name = "transaction_status", nullable = false)
    private TransactionStatus transactionStatus;

    @Enumerated(EnumType.STRING)
    @Column (name = "transaction_type", nullable = false)
    private TransactionType transactionType;

    @Column (name = "transaction_code", unique = true, nullable = false)
    private String transactionCode;

    @CreationTimestamp
    @Column (name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
