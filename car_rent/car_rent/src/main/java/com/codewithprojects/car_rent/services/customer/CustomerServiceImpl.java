package com.codewithprojects.car_rent.services.customer;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codewithprojects.car_rent.dto.BookACarDto;
import com.codewithprojects.car_rent.dto.CarDto;
import com.codewithprojects.car_rent.entity.BookCar;
import com.codewithprojects.car_rent.entity.Car;
import com.codewithprojects.car_rent.entity.Users;
import com.codewithprojects.car_rent.enums.BookCarStatus;
import com.codewithprojects.car_rent.repository.BookACarRepository;
import com.codewithprojects.car_rent.repository.CarRepository;
import com.codewithprojects.car_rent.repository.UserRepository;

@Service
public class CustomerServiceImpl implements CustomerService{	
@Autowired
CarRepository carRepository;
@Autowired
UserRepository userRepository;
@Autowired

BookACarRepository bookACarRepository;
@Override
public List<CarDto> getAllCars() {
	
	return carRepository.findAll().stream().map(Car::getCarDto).collect(Collectors.toList());
}

@Override
public boolean bookACar(BookACarDto bookACarDto) {
	  
System.out.println(bookACarDto.getDays());

     Optional<Car> optionalCar = carRepository.findById(bookACarDto.getCarId());
     Optional<Users> optionalUser = userRepository.findById(bookACarDto.getUserId());
    
     Long carId = bookACarDto.getCarId();
     Long userId = bookACarDto.getUserId();
     System.out.println("Car ID: " + carId + ", User ID: " + userId); 
     if (optionalCar.isPresent() && optionalUser.isPresent()) {
         Car existingCar = optionalCar.get(); 
         BookCar bookCar = new BookCar();
         bookCar.setUser(optionalUser.get());
         bookCar.setCar(existingCar);
         bookCar.setFromDate(bookACarDto.getFromDate());
         bookCar.setToDate(bookACarDto.getToDate());
         bookCar.setBookCarStatus(BookCarStatus.PENDING);
         long diffInMilliSeconds = bookACarDto.getToDate().getTime() - bookACarDto.getFromDate().getTime();
         long days = TimeUnit.MILLISECONDS.toDays(diffInMilliSeconds); 
         bookCar.setDays(days);
         bookCar.setPrice(existingCar.getPrice() * days);
         bookACarRepository.save(bookCar);
         return true;
     }
     else {
         return false;
     }
}
@Override
public CarDto getcarById(Long carId) {
	// TODO Auto-generated method stub
	Optional<Car>optionalcar = carRepository.findById(carId);
	return optionalcar.map(Car::getCarDto).orElse(null);

}
@Override
public List<BookACarDto> getBookingsByUserId(Long userId) {
    return bookACarRepository.findAllByUserId(userId)
                              .stream()
                              .map(BookCar::getBookACarDto)
                              .collect(Collectors.toList());
}
public List<Car> searchCars(String brand, String type, String transmission, String color) {
    return carRepository.searchCars(brand, type, transmission, color);
}
}
