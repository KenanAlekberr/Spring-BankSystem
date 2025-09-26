package com.example.BankSystem.service.concrete;

import com.example.BankSystem.dao.entity.AccountEntity;
import com.example.BankSystem.dao.entity.TransactionLogEntity;
import com.example.BankSystem.dao.repository.AccountRepository;
import com.example.BankSystem.dao.repository.TransactionLogRepository;
import com.example.BankSystem.dto.request.account.CreateAccountRequest;
import com.example.BankSystem.dto.response.account.AccountResponse;
import com.example.BankSystem.exception.custom.InsufficientAccountException;
import com.example.BankSystem.exception.custom.NotFoundException;
import com.example.BankSystem.service.abstraction.AccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.example.BankSystem.enums.AccountStatus.ACTIVE;
import static com.example.BankSystem.enums.AccountStatus.IN_PROGRESS;
import static com.example.BankSystem.enums.TransactionType.DEPOSIT;
import static com.example.BankSystem.enums.TransactionType.TRANSFER;
import static com.example.BankSystem.enums.TransactionType.WITHDRAW;
import static com.example.BankSystem.exception.ExceptionConstants.ACCOUNT_NOT_FOUND;
import static com.example.BankSystem.exception.ExceptionConstants.INSUFFICIENT_ACCOUNT;
import static com.example.BankSystem.mapper.AccountMapper.ACCOUNT_MAPPER;
import static java.math.BigDecimal.ZERO;
import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AccountServiceHandler implements AccountService {
    AccountRepository accountRepository;
    TransactionLogRepository transactionLogRepository;

    @Override
    public AccountResponse createAccount(CreateAccountRequest request) {
        AccountEntity entity = ACCOUNT_MAPPER.builderAccountEntity(request);
        accountRepository.save(entity);

        return ACCOUNT_MAPPER.builderAccountResponse(entity);
    }

    @Override
    public List<AccountResponse> getAllAccounts() {
        List<AccountEntity> accountEntities = accountRepository.findAll();
        List<AccountResponse> responses = new ArrayList<>();

        for (AccountEntity account : accountEntities) {
            if (account.getAccountStatus() == ACTIVE || account.getAccountStatus() == IN_PROGRESS)
                responses.add(ACCOUNT_MAPPER.builderAccountResponse(account));
        }

        return responses;
    }

    @Override
    public AccountResponse getAccountById(Long id) {
        AccountEntity entity = fetchAccountIfExist(id);
        return ACCOUNT_MAPPER.builderAccountResponse(entity);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deposit(Long accountId, BigDecimal amount) {
        AccountEntity account = fetchAccountIfExist(accountId);

        BigDecimal newBalance = account.getBalance().add(amount);
        account.setBalance(newBalance);
        accountRepository.save(account);

        TransactionLogEntity log = TransactionLogEntity.builder()
                .fromAccountId(accountId)
                .toAccountId(accountId)
                .transactionType(DEPOSIT)
                .balanceAfter(newBalance)
                .build();

        transactionLogRepository.save(log);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void withdraw(Long accountId, BigDecimal amount) {
        AccountEntity account = fetchAccountIfExist(accountId);

        if (amount.compareTo(ZERO) <= 0 || account.getBalance().compareTo(amount) < 0)
            throw new InsufficientAccountException(INSUFFICIENT_ACCOUNT.getCode(), INSUFFICIENT_ACCOUNT.getMessage());

        BigDecimal newBalance = account.getBalance().subtract(amount);
        account.setBalance(newBalance);
        accountRepository.save(account);

        TransactionLogEntity log = TransactionLogEntity.builder()
                .fromAccountId(accountId)
                .toAccountId(accountId)
                .transactionType(WITHDRAW)
                .balanceAfter(newBalance)
                .build();

        transactionLogRepository.save(log);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void transfer(Long fromId, Long toId, BigDecimal amount) {
        withdraw(fromId, amount);
        deposit(toId, amount);

        AccountEntity account = fetchAccountIfExist(fromId);

        TransactionLogEntity log = TransactionLogEntity.builder()
                .fromAccountId(fromId)
                .toAccountId(toId)
                .transactionType(TRANSFER)
                .balanceAfter(account.getBalance())
                .build();

        transactionLogRepository.save(log);
    }

    private AccountEntity fetchAccountIfExist(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ACCOUNT_NOT_FOUND.getCode(), ACCOUNT_NOT_FOUND.getMessage()));
    }
}