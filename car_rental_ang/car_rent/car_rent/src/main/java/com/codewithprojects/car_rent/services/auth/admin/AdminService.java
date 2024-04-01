package com.codewithprojects.car_rent.services.auth.admin;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.codewithprojects.car_rent.dto.BookACarDto;
import com.codewithprojects.car_rent.dto.CarDto;
import com.codewithprojects.car_rent.dto.CarDtoListDto;
import com.codewithprojects.car_rent.dto.SearchCarDto;
import com.codewithprojects.car_rent.entity.Car;

public interface AdminService {
	
boolean postCar(CarDto carDto , MultipartFile imageFile) throws IOException;

List<CarDto>getAllCar();

void deleteCar(Long id);

CarDto getCarById(Long id);

boolean updateCar(Long carId,CarDto carDto);

List<BookACarDto> getBookings();

boolean changeBookingStatus(Long bookingId, String status);

//CarDtoListDto searchCar(SearchCarDto searchCarDto);
List<Car> searchCars(String brand, String type, String transmission, String color);
}
