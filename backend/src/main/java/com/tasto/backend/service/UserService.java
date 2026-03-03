package com.tasto.backend.service;

import com.tasto.backend.dto.LoginUserRequest;
import com.tasto.backend.dto.LoginUserResponse;
import com.tasto.backend.dto.UserRequest;
import com.tasto.backend.dto.UserResponse;

public interface UserService {
    UserResponse registerUser(UserRequest userRequest);
    LoginUserResponse loginUser(LoginUserRequest loginUserRequest);
}
