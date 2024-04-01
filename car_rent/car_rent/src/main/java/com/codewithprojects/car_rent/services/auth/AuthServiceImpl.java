package com.codewithprojects.car_rent.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codewithprojects.car_rent.dto.LoginRequest;
import com.codewithprojects.car_rent.dto.SignupRequest;
import com.codewithprojects.car_rent.dto.UserDto;
import com.codewithprojects.car_rent.entity.Users;
import com.codewithprojects.car_rent.enums.UserRole;
import com.codewithprojects.car_rent.repository.UserRepository;

import jakarta.annotation.PostConstruct;



@Service
public class AuthServiceImpl implements Auth{
	@Autowired
	UserRepository userrepository;
	
	
	@PostConstruct
    public void addDefaultAdminUser() {
        
        if (userrepository.findByEmail("admin@example.com") == null) {
          
            Users adminUser = new Users();
            adminUser.setName("Admin");
            adminUser.setEmail("admin@example.com");
            adminUser.setPassword("Admin123");
            adminUser.setUserrole(UserRole.Admin);

           
            userrepository.save(adminUser);
        }
    }
	@Override
	public UserDto createCustomer(SignupRequest signuprequest) {
		Users user=new Users();
		user.setName(signuprequest.getName());
		user.setEmail(signuprequest.getEmail());
		user.setPassword(signuprequest.getPassword());
		user.setUserrole(UserRole.Customer);
		Users createdUser=userrepository.save(user);
		UserDto userdto=new UserDto();
		userdto.setId(createdUser.getId());
				
		return userdto;
	}

	public UserDto loginUser(LoginRequest loginRequest) {
   
        Users user = userrepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());

        if (user != null) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setEmail(user.getEmail());
            userDto.setUserRole(user.getUserrole());
            return userDto;
        } else {
            return null; // Return null if login fails
        }
    }	
	
}
