package com.tasto.backend.service;

import com.tasto.backend.dto.RestaurantLoginRequest;
import com.tasto.backend.dto.RestaurantLoginResponse;
import com.tasto.backend.dto.RestaurantRequest;
import com.tasto.backend.dto.RestaurantResponse;
import com.tasto.backend.entity.Restaurant;
import com.tasto.backend.entity.RestaurantAddress;
import com.tasto.backend.exception.InvalidRequestException;
import com.tasto.backend.repository.RestaurantRepository;
import com.tasto.backend.utils.Validator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService{
    private final RestaurantRepository restaurantRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository,PasswordEncoder passwordEncoder,JwtService jwtService)
    {
        this.restaurantRepository=restaurantRepository;
        this.passwordEncoder=passwordEncoder;
        this.jwtService=jwtService;
    }
    @Override
    public RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest) {
        Validator.restaurantValidate(restaurantRequest);
        Optional<Restaurant> restaurant = restaurantRepository.findByEmail(restaurantRequest.getEmail());
        if(restaurant.isPresent())
        {
            throw new InvalidRequestException("Restaurant already exists");
        }
        Restaurant restaurant1 = new Restaurant();
        restaurant1.setName(restaurantRequest.getName());
        restaurant1.setEmail(restaurantRequest.getEmail());
        restaurant1.setDescription(restaurantRequest.getDescription());
        restaurant1.setMobile(restaurantRequest.getMobile());
        restaurant1.setOpeningTime(restaurantRequest.getOpeningTime());
        restaurant1.setClosingTime(restaurantRequest.getClosingTime());
        restaurant1.setRating(restaurantRequest.getRating());
        restaurant1.setRestaurantOpen(restaurant1.isRestaurantOpen());
        restaurant1.setAcceptOrder(restaurant1.isAcceptOrder());
        restaurant1.setTags(restaurantRequest.getTags());
        restaurant1.setAccountActive(true);

        String hashedPassword = passwordEncoder.encode(restaurantRequest.getPassword());
        restaurant1.setPassword(hashedPassword);
        if(restaurantRequest.getAddress()!=null) {
            RestaurantAddress address = new RestaurantAddress();
            address.setHouseNo(restaurantRequest.getAddress().getHouseNo());
            address.setStreet(restaurantRequest.getAddress().getStreet());
            address.setState(restaurantRequest.getAddress().getState());
            address.setCountry(restaurantRequest.getAddress().getCountry());
            address.setLatitude(restaurantRequest.getAddress().getLatitude());
            address.setLongitude(restaurantRequest.getAddress().getLongitude());

            restaurant1.setAddresses(address);
        }
        Restaurant newRestaurant = restaurantRepository.save(restaurant1);
        return new RestaurantResponse(true,"Restaurant Registered Successfully",newRestaurant);
    }

    public RestaurantLoginResponse restaurantLogin(RestaurantLoginRequest restaurantLoginRequest)
    {
        if(restaurantLoginRequest==null)
        {
            throw new InvalidRequestException("All fields required");
        }
       Optional<Restaurant> isRestaurantExist= restaurantRepository.findByEmail(restaurantLoginRequest.getEmail());
       if(!isRestaurantExist.isPresent())
       {
           throw new InvalidRequestException("Restaurant does not exist");
       }
       if(!isRestaurantExist.get().isAccountActive())
       {
          throw new InvalidRequestException("Account not active");
       }
       boolean passwordMatched = passwordEncoder.matches(restaurantLoginRequest.getPassword(),isRestaurantExist.get().getPassword());
       if(!passwordMatched)
       {
           throw new InvalidRequestException("Password does not match");
       }
      String token = jwtService.generateToken(isRestaurantExist.get().getEmail(),"restaurant");
       return new RestaurantLoginResponse(true,"Restaurant Logged in successfully",token);
    }

    @Override
    public RestaurantResponse getRestaurantById(Long id) {
        Optional<Restaurant> isRestaurantExist = restaurantRepository.findById(id);
        if(!isRestaurantExist.isPresent())
        {
            throw new InvalidRequestException("Restaurant does not found");
        }
        if(!isRestaurantExist.get().isAccountActive())
        {
            throw new InvalidRequestException("Account not active");
        }
        return new RestaurantResponse(true,"Restaurant Get Successfully",isRestaurantExist.get());
    }
    @Override
    public RestaurantResponse deleteRestaurantAccount()
    {
     String email=getLoggedInUserEmail();
     Optional<Restaurant>restaurant = restaurantRepository.findByEmail(email);
     if(!restaurant.isPresent())
     {
         throw new InvalidRequestException("Restaurant does not found");
     }
     if(restaurant.get().isAccountActive()==false)
     {
         throw new InvalidRequestException("Already account inactive");
     }
     restaurant.get().setAccountActive(false);
     restaurantRepository.save(restaurant.get());
     return new RestaurantResponse(true,"Your account has been deactivated successfully",null);
    }

    public String getLoggedInUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();   // This returns email
    }
}
