package com.example.BankSystem.service.concrete;

import com.example.BankSystem.dao.entity.TransactionLogEntity;
import com.example.BankSystem.dao.repository.TransactionLogRepository;
import com.example.BankSystem.dto.response.transaction.TransactionLogResponse;
import com.example.BankSystem.exception.custom.NotFoundException;
import com.example.BankSystem.service.abstraction.TransactionLogService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.BankSystem.exception.ExceptionConstants.ACCOUNT_NOT_FOUND;
import static com.example.BankSystem.mapper.TransactionLogMapper.TRANSACTION_LOG_MAPPER;
import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class TransactionLogServiceHandler implements TransactionLogService {
    TransactionLogRepository transactionLogRepository;

    @Override
    public List<TransactionLogResponse> getByAccountId(Long accountId) {
        List<TransactionLogEntity> transactionLogEntities = transactionLogRepository.findByFromAccountId(accountId)
                .orElseThrow(() -> new NotFoundException(ACCOUNT_NOT_FOUND.getCode(), ACCOUNT_NOT_FOUND.getMessage()));

        List<TransactionLogResponse> responses = new ArrayList<>();

        for (TransactionLogEntity transactionLogEntity : transactionLogEntities) {
            responses.add(TRANSACTION_LOG_MAPPER.buildTransactionLogResponse(transactionLogEntity));
        }

        return responses;
    }

    @Override
    public void save(TransactionLogEntity transactionLogEntity) {
        transactionLogRepository.save(transactionLogEntity);
    }
}