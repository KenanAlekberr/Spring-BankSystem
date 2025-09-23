package com.example.BankSystem.dto.response;

import com.example.BankSystem.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
@Builder
public class UserResponse {
    Long id;
    String firstName;
    String lastName;
    String email;
    Status status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}