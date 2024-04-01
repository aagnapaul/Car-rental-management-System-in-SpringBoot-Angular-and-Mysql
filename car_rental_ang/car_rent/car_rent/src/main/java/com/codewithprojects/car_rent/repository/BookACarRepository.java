package com.codewithprojects.car_rent.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.codewithprojects.car_rent.entity.BookCar;
@Repository
public interface BookACarRepository extends JpaRepository<BookCar, Long>{

	List<BookCar> findAllByUserId(Long userId);
	

}
