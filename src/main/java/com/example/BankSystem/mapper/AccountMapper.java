package com.example.BankSystem.mapper;

import com.example.BankSystem.dao.entity.AccountEntity;
import com.example.BankSystem.dto.request.account.CreateAccountRequest;
import com.example.BankSystem.dto.response.account.AccountResponse;

import java.time.LocalDateTime;

import static com.example.BankSystem.enums.AccountStatus.ACTIVE;

public enum AccountMapper {
    ACCOUNT_MAPPER;

    public AccountEntity builderAccountEntity(CreateAccountRequest request) {
        return AccountEntity.builder()
                .userId(request.getUserId())
                .accountNumber(request.getAccountNumber())
                .balance(request.getBalance())
                .accountStatus(ACTIVE)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public AccountResponse builderAccountResponse(AccountEntity account) {
        return AccountResponse.builder()
                .id(account.getId())
                .userId(account.getUserId())
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .accountStatus(account.getAccountStatus())
                .createdAt(account.getCreatedAt())
                .updatedAt(account.getUpdatedAt())
                .build();
    }
}