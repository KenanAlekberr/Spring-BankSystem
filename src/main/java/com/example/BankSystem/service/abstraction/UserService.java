package com.example.BankSystem.service.abstraction;

import com.example.BankSystem.dto.request.user.CreateUserRequest;
import com.example.BankSystem.dto.request.user.UpdateUserRequest;
import com.example.BankSystem.dto.response.user.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(CreateUserRequest request);

    UserResponse getUserById(Long id);

    List<UserResponse> getAllUsers();

    UserResponse updateUser(Long id, UpdateUserRequest request);

    void deleteUser(Long id);
}