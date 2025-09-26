package com.example.BankSystem.dto.response.transaction;

import com.example.BankSystem.enums.TransactionType;
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
public class TransactionLogResponse {
    Long id;
    Long fromAccountId;
    Long toAccountId;
    TransactionType transactionType;
    BigDecimal balanceAfter;
    LocalDateTime createdAt;
}