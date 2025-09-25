package com.example.BankSystem.dao.repository;

import com.example.BankSystem.dao.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}