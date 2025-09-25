package com.example.BankSystem.mapper;

import com.example.BankSystem.dao.entity.TransactionLogEntity;
import com.example.BankSystem.dto.response.transaction.TransactionLogResponse;

public enum TransactionLogMapper {
    TRANSACTION_LOG_MAPPER;

    public TransactionLogResponse buildTransactionLogResponse(TransactionLogEntity log) {
        return TransactionLogResponse.builder()
                .id(log.getId())
                .accountId(log.getAccountId())
                .transactionType(log.getTransactionType())
                .amount(log.getAmount())
                .balanceAfter(log.getBalanceAfter())
                .createdAt(log.getCreatedAt())
                .build();
    }
}