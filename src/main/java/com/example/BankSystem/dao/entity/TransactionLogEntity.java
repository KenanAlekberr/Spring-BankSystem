package com.example.BankSystem.dao.entity;

import com.example.BankSystem.enums.TransactionType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Table(name = "transaction_logs")
@Builder
public class TransactionLogEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;
    Long accountId;

    @Enumerated(STRING)
    TransactionType transactionType;

    BigDecimal amount;
    BigDecimal balanceAfter;

    @CreationTimestamp
    LocalDateTime createdAt;
}