package com.tasto.backend.controller;

import com.tasto.backend.dto.LoginUserRequest;
import com.tasto.backend.dto.LoginUserResponse;
import com.tasto.backend.dto.UserRequest;
import com.tasto.backend.dto.UserResponse;
import com.tasto.backend.repository.UserRepository;
import com.tasto.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService)
    {
        this.userService=userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest)
    {
        UserResponse response = userService.registerUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("login")
    public ResponseEntity<LoginUserResponse> userLogin(@RequestBody LoginUserRequest loginUserRequest)
    {
        LoginUserResponse response = userService.loginUser(loginUserRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
