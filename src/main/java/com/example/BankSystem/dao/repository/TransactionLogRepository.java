package com.example.BankSystem.dao.repository;

import com.example.BankSystem.dao.entity.TransactionLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionLogRepository extends JpaRepository<TransactionLogEntity, Long> {
    List<TransactionLogEntity> findByFromAccountId(Long transactionId);
}