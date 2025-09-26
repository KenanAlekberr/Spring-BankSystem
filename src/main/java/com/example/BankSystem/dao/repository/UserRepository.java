package com.example.BankSystem.dao.repository;

import com.example.BankSystem.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}