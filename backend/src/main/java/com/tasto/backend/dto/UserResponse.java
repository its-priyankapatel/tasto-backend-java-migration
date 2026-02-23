package com.tasto.backend.dto;

import com.tasto.backend.entity.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private boolean success;
    private String message;
    private UserModel user;
}
