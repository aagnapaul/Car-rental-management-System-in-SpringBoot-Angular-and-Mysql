package com.codewithprojects.car_rent.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithprojects.car_rent.dto.LoginRequest;
import com.codewithprojects.car_rent.dto.SignupRequest;
import com.codewithprojects.car_rent.dto.UserDto;

import com.codewithprojects.car_rent.services.auth.Auth;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired 
	Auth auth;
	 
	@PostMapping("/signup")
	public ResponseEntity<?> signupCustomer(@RequestBody SignupRequest signupRequest){
		UserDto createdCustomerDto = auth.createCustomer(signupRequest);
		
		String body;
		if(createdCustomerDto == null) return new ResponseEntity<>
		(body = "Customer not created,Come again later",HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(createdCustomerDto, HttpStatus.CREATED);
		
	}
	@PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        UserDto loggedInUser = auth.loginUser(loginRequest);

        if (loggedInUser != null) {
            return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Login failed. Invalid credentials.", HttpStatus.UNAUTHORIZED);
        }
    }
	
	 
}
