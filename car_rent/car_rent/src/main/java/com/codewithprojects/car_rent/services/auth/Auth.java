package com.codewithprojects.car_rent.services.auth;

import com.codewithprojects.car_rent.dto.LoginRequest;
import com.codewithprojects.car_rent.dto.SignupRequest;
import com.codewithprojects.car_rent.dto.UserDto;


public interface Auth {
	UserDto createCustomer(SignupRequest signuprequest);
    UserDto loginUser(LoginRequest loginRequest);



	

}
