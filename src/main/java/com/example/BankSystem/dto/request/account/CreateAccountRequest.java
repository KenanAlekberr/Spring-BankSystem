package com.example.BankSystem.dto.request.account;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class CreateAccountRequest {
    @NotNull(message = "User id cannot be null")
    Long userId;

    @NotBlank(message = "Account number cannot be blank")
    @Pattern(regexp = "\\d{6}", message = "Account number must be exactly 6 digits")
    String accountNumber;

    @NotNull(message = "Initial balance cannot be null")
    @DecimalMin(value = "0.00", message = "Balance cannot be negative")
    BigDecimal balance;
}