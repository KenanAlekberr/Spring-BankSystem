package com.example.BankSystem.service.abstraction;

import com.example.BankSystem.dto.response.transaction.TransactionLogResponse;

import java.util.List;

public interface TransactionLogService {
    List<TransactionLogResponse> getByAccountId(Long accountId);
}