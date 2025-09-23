package com.example.BankSystem.mapper;

import com.example.BankSystem.dao.entity.UserEntity;
import com.example.BankSystem.dto.request.user.CreateUserRequest;
import com.example.BankSystem.dto.request.user.UpdateUserRequest;
import com.example.BankSystem.dto.response.UserResponse;

import java.time.LocalDateTime;

import static com.example.BankSystem.enums.Status.ACTIVE;
import static com.example.BankSystem.enums.Status.IN_PROGRESS;

public enum UserMapper {
    USER_MAPPER;

    public UserEntity buildUserEntity(CreateUserRequest request) {
        return UserEntity.builder().
                firstName(request.getFirstName()).
                lastName(request.getLastName()).
                email(request.getEmail()).
                status(ACTIVE).
                createdAt(LocalDateTime.now()).
                updatedAt(LocalDateTime.now()).
                build();
    }

    public UserResponse buildUserResponse(UserEntity user) {
        return UserResponse.builder().id(user.getId()).firstName(user.getFirstName()).lastName(user.getLastName()).email(user.getEmail()).status(user.getStatus()).createdAt(user.getCreatedAt()).updatedAt(user.getUpdatedAt()).build();
    }

    public void updateUser(UserEntity user, UpdateUserRequest request) {
        if (request.getFirstName() != null && !request.getFirstName().isBlank())
            user.setFirstName(request.getFirstName());

        if (request.getLastName() != null && !request.getLastName().isBlank())
            user.setLastName(request.getLastName());

        if (request.getEmail() != null && !request.getEmail().isBlank())
            user.setEmail(request.getEmail());

        user.setStatus(IN_PROGRESS);
    }
}