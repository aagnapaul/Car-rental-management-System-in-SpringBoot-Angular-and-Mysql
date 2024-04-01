package com.codewithprojects.car_rent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codewithprojects.car_rent.dto.BookACarDto;
import com.codewithprojects.car_rent.dto.CarDto;
import com.codewithprojects.car_rent.entity.Car;
import com.codewithprojects.car_rent.services.customer.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
@Autowired
CustomerService customerService;

@GetMapping("/cars")
public ResponseEntity<List<CarDto>>getAllCars(){
	List<CarDto> carDtoList = customerService.getAllCars();
	return ResponseEntity.ok(carDtoList);
	
}
@PostMapping("/car/book")
public ResponseEntity<Void> bookCar(@RequestBody BookACarDto bookACarDto) {
	System.out.println(bookACarDto);
    boolean success = customerService.bookACar(bookACarDto);
    if (success) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    } else {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
@GetMapping("/car/{carId}")
public ResponseEntity<CarDto> getCarById(@PathVariable long carId) {
    CarDto carDto = customerService.getcarById(carId);
    if (carDto != null) {
        return ResponseEntity.ok(carDto);
    } else {
        return ResponseEntity.notFound().build();
    }
}
@GetMapping("/car/bookings/{userId}")
public ResponseEntity<List<BookACarDto>> getBookingsById(@PathVariable Long userId){
	return ResponseEntity.ok(customerService.getBookingsByUserId(userId));
}
@GetMapping("/search")
public ResponseEntity<List<Car>> searchCars(@RequestParam(required = false) String brand,
                                            @RequestParam(required = false) String type,
                                            @RequestParam(required = false) String transmission,
                                            @RequestParam(required = false) String color) {
    List<Car> cars = customerService.searchCars(brand, type, transmission, color);
    return ResponseEntity.ok(cars);
}
}

