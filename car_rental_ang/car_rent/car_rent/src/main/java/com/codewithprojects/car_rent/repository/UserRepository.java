package com.codewithprojects.car_rent.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codewithprojects.car_rent.entity.Users;
import com.codewithprojects.car_rent.enums.UserRole;

@Repository
public interface UserRepository extends JpaRepository<Users,Long>{

	Users findByEmailAndPassword(String email, String password);
	List<Users> findByUserrole(UserRole userrole);
	
	 Users findByEmail(String email);
	


		

}
