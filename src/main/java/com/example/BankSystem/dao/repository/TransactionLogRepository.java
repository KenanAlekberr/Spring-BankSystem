package com.example.BankSystem.dao.repository;

import com.example.BankSystem.dao.entity.TransactionLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionLogRepository extends JpaRepository<TransactionLogEntity, Long> {
    Optional<List<TransactionLogEntity>> findByFromAccountId(Long transactionId);
}