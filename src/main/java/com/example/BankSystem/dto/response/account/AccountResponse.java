package com.example.BankSystem.dto.response.account;

import com.example.BankSystem.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
@Builder
public class AccountResponse {
    Long id;
    Long userId;
    String accountNumber;
    BigDecimal balance;
    AccountStatus accountStatus;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}