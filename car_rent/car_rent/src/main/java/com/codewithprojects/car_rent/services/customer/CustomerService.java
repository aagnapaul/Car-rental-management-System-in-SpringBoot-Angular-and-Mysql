package com.codewithprojects.car_rent.services.customer;

import java.util.List;

import com.codewithprojects.car_rent.dto.BookACarDto;
import com.codewithprojects.car_rent.dto.CarDto;
import com.codewithprojects.car_rent.entity.Car;

public interface CustomerService {
	
    List<CarDto>getAllCars();

    boolean bookACar(BookACarDto bookACarDto);
    
	CarDto getcarById(Long carId);
	
	
    List<BookACarDto> getBookingsByUserId(Long userId);	
    
    List<Car> searchCars(String brand, String type, String transmission, String color);
	
}
