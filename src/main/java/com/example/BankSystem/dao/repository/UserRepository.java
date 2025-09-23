package com.example.BankSystem.dao.repository;

import com.example.BankSystem.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Override
    @Query(value = "SELECT * FROM users u WHERE u.id = :id AND u.status IN ('ACTIVE', 'IN_PROGRESS')", nativeQuery = true)
    Optional<UserEntity> findById(Long id);
}