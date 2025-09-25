package com.example.BankSystem.controller;

import com.example.BankSystem.dto.response.transaction.TransactionLogResponse;
import com.example.BankSystem.service.abstraction.TransactionLogService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/api/v1/transaction-logs")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class TransactionLogController {
    TransactionLogService transactionLogService;

    @GetMapping("/account/{accountId}")
    public List<TransactionLogResponse> getByAccountId(@PathVariable Long accountId) {
        return transactionLogService.getByAccountId(accountId);
    }
}