package com.tasto.backend.service;

import com.tasto.backend.dto.UserRequest;
import com.tasto.backend.dto.UserResponse;
import com.tasto.backend.entity.Address;
import com.tasto.backend.entity.UserModel;
import com.tasto.backend.exception.InvalidRequestException;
import com.tasto.backend.repository.UserRepository;
import com.tasto.backend.utils.Validator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder)
    {
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }
    public UserResponse registerUser(UserRequest userRequest)
    {
        Validator.validateUserRequest(userRequest);
        Optional<UserModel> isUserExist= userRepository.findByEmail(userRequest.getEmail());
        if(isUserExist.isPresent()) {
            throw new IllegalArgumentException("User already exist");
        }

        UserModel user = new UserModel();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        String hashedPassword = passwordEncoder.encode(userRequest.getPassword());
        user.setPassword(hashedPassword);
        if(userRequest.getAddress()!=null) {
            for(Address address : userRequest.getAddress()) {
                Address newAddress = new Address();
                newAddress.setFullName(address.getFullName());
                newAddress.setAddressType(address.getAddressType());
                newAddress.setDefault(address.isDefault());
                newAddress.setHouseNo(address.getHouseNo());
                newAddress.setStreet(address.getStreet());
                newAddress.setLandmark(address.getLandmark());
                newAddress.setCity(address.getCity());
                newAddress.setPinCode(address.getPinCode());
                newAddress.setState(address.getState());
                newAddress.setCountry(address.getCountry());
                newAddress.setLongitude(address.getLongitude());
                newAddress.setLatitude(address.getLatitude());
                user.addAddress(address);
            }
        }
        UserModel newUser = userRepository.save(user);
        return new UserResponse(true,"User Registered Successfully",newUser);
    }

}
