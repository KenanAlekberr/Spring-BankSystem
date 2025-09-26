package com.example.BankSystem.mapper;

import com.example.BankSystem.dao.entity.UserEntity;
import com.example.BankSystem.dto.request.user.CreateUserRequest;
import com.example.BankSystem.dto.request.user.UpdateUserRequest;
import com.example.BankSystem.dto.response.user.UserResponse;
import io.micrometer.common.util.StringUtils;

import java.time.LocalDateTime;

import static com.example.BankSystem.enums.UserStatus.ACTIVE;
import static com.example.BankSystem.enums.UserStatus.IN_PROGRESS;

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
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .status(user.getStatus())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public void updateUser(UserEntity user, UpdateUserRequest request) {
        if (StringUtils.isNotEmpty(request.getFirstName()))
            user.setFirstName(request.getFirstName());

        if (StringUtils.isNotEmpty(request.getLastName()))
            user.setLastName(request.getLastName());

        if (StringUtils.isNotEmpty(request.getEmail()))
            user.setEmail(request.getEmail());

        user.setStatus(IN_PROGRESS);
    }
}