package com.example.BankSystem.controller;

import com.example.BankSystem.dto.request.account.CreateAccountRequest;
import com.example.BankSystem.dto.response.account.AccountResponse;
import com.example.BankSystem.service.abstraction.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/api/v1/account")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AccountController {
    AccountService accountService;

    @PostMapping("/post")
    public AccountResponse createAccount(@Valid @RequestBody CreateAccountRequest request) {
        return accountService.createAccount(request);
    }

    @GetMapping("/{id}")
    public AccountResponse getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @GetMapping("/getAllAccounts")
    public List<AccountResponse> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PostMapping("/{id}/deposit")
    public void deposit(@RequestParam Long accountId, @RequestParam BigDecimal amount) {
        accountService.deposit(accountId, amount);
    }

    @PostMapping("/{id}/withdraw")
    public void withdraw(@RequestParam Long accountId, @RequestParam BigDecimal amount) {
        accountService.withdraw(accountId, amount);
    }

    @PostMapping("/transfer")
    public void transfer(@RequestParam Long fromId, @RequestParam Long toId, @RequestParam BigDecimal amount) {
        accountService.transfer(fromId, toId, amount);
    }
}