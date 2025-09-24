package com.example.BankSystem.service.concrete;

import com.example.BankSystem.dao.entity.UserEntity;
import com.example.BankSystem.dao.repository.UserRepository;
import com.example.BankSystem.dto.request.user.CreateUserRequest;
import com.example.BankSystem.dto.request.user.UpdateUserRequest;
import com.example.BankSystem.dto.response.user.UserResponse;
import com.example.BankSystem.exception.NotFoundException;
import com.example.BankSystem.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.BankSystem.enums.Status.ACTIVE;
import static com.example.BankSystem.enums.Status.DELETED;
import static com.example.BankSystem.enums.Status.IN_PROGRESS;
import static com.example.BankSystem.exception.ExceptionConstants.USER_NOT_FOUND;
import static com.example.BankSystem.mapper.UserMapper.USER_MAPPER;
import static lombok.AccessLevel.PRIVATE;

@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserServiceHandler implements UserService {
    UserRepository userRepository;

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        UserEntity user = USER_MAPPER.buildUserEntity(request);
        userRepository.save(user);

        return USER_MAPPER.buildUserResponse(user);
    }

    @Override
    public UserResponse getUserById(Long id) {
        UserEntity user = fetchUserIfExist(id);

        return USER_MAPPER.buildUserResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();

        for (UserEntity user : users) {
            if (user.getStatus() == ACTIVE || user.getStatus() == IN_PROGRESS) {
                UserResponse response = USER_MAPPER.buildUserResponse(user);
                userResponses.add(response);
            }
        }

        return userResponses;
    }

    @Override
    public UserResponse updateUser(Long id, UpdateUserRequest request) {
        UserEntity user = fetchUserIfExist(id);
        USER_MAPPER.updateUser(user, request);

        userRepository.save(user);

        return USER_MAPPER.buildUserResponse(user);
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity user = fetchUserIfExist(id);
        user.setStatus(DELETED);
        userRepository.save(user);
    }

    private UserEntity fetchUserIfExist(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new NotFoundException(USER_NOT_FOUND.getCode(), USER_NOT_FOUND.getMessage()));
    }
}