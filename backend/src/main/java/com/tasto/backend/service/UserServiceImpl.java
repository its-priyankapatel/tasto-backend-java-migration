package com.tasto.backend.service;

import com.tasto.backend.dto.*;
import com.tasto.backend.entity.Address;
import com.tasto.backend.entity.UserModel;
import com.tasto.backend.exception.InvalidRequestException;
import com.tasto.backend.repository.UserRepository;
import com.tasto.backend.utils.Validator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder,JwtService jwtService)
    {
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.jwtService=jwtService;
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
                newAddress.setDefaultAddress(address.isDefaultAddress());
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
        return new UserResponse(true,"User Registered Successfully",responseMapper(newUser));
    }
    private UserDto responseMapper(UserModel newUser)
    {
        UserDto user=new UserDto();
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        user.setPhone(newUser.getPhone());

        ArrayList<AddressDto> addressList =new ArrayList<>();
        for(Address ad: newUser.getAddresses())
        {
            AddressDto address = new AddressDto();
            address.setId(ad.getId());
            address.setAddressType(ad.getAddressType());
            address.setFullName(ad.getFullName());
            address.setHouseNo(ad.getHouseNo());
            address.setLandmark(ad.getLandmark());
            address.setStreet(ad.getStreet());
            address.setCity(ad.getCity());
            address.setState(ad.getState());
            address.setCountry(ad.getCountry());
            address.setPinCode(ad.getPinCode());
            address.setLatitude(ad.getLatitude());
            address.setLongitude(ad.getLongitude());
            addressList.add(address);

        }
        user.setAddresses(addressList);
        return user;
    }

    public LoginUserResponse loginUser(LoginUserRequest loginUserRequest)
    {
        if(loginUserRequest.getEmail()==null || loginUserRequest.getEmail().isBlank())
        {
           throw new InvalidRequestException("Email is required");
        }
        if(loginUserRequest.getPassword()==null|| loginUserRequest.getPassword().isBlank())
        {
            throw new InvalidRequestException("Password is required");
        }
      Optional<UserModel>isUserExist =userRepository.findByEmail(loginUserRequest.getEmail());
      if(!isUserExist.isPresent())
      {
          throw new InvalidRequestException("User does not exist");
      }
      boolean passwordMatches = passwordEncoder.matches(loginUserRequest.getPassword(),isUserExist.get().getPassword());
      if(!passwordMatches)
      {
         throw new InvalidRequestException("Password is not correct");
      }
      String token = jwtService.generateToken(isUserExist.get().getEmail(),"user");
      return new LoginUserResponse(true,"User Logged In Successfully",token);
    }

}
