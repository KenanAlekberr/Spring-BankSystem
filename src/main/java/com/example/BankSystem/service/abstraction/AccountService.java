package com.example.BankSystem.service.abstraction;

import com.example.BankSystem.dto.request.account.CreateAccountRequest;
import com.example.BankSystem.dto.response.account.AccountResponse;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    AccountResponse createAccount(CreateAccountRequest request);

    List<AccountResponse> getAllAccounts();

    AccountResponse getAccountById(Long id);

    void deposit(Long accountId, BigDecimal amount);

    void withdraw(Long accountId, BigDecimal amount);

    void transfer(Long fromId, Long toId, BigDecimal amount);
}