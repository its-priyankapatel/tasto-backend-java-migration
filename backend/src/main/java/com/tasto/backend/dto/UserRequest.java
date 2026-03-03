package com.tasto.backend.dto;

import com.tasto.backend.entity.Address;
import lombok.Data;

import java.util.List;

@Data
public class UserRequest {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String role;
    private List<Address> address;
}
