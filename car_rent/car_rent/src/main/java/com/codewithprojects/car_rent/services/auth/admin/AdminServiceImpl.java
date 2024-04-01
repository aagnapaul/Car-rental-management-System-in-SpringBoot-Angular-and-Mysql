package com.codewithprojects.car_rent.services.auth.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.codewithprojects.car_rent.dto.BookACarDto;
import com.codewithprojects.car_rent.dto.CarDto;
import com.codewithprojects.car_rent.dto.CarDtoListDto;
import com.codewithprojects.car_rent.dto.SearchCarDto;
import com.codewithprojects.car_rent.entity.BookCar;
import com.codewithprojects.car_rent.entity.Car;
import com.codewithprojects.car_rent.enums.BookCarStatus;
import com.codewithprojects.car_rent.repository.BookACarRepository;
import com.codewithprojects.car_rent.repository.CarRepository;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AdminServiceImpl implements AdminService{
	 private static final Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);
	 
@Autowired
CarRepository carrepository;

@Autowired
BookACarRepository bookACarRepository;

@Override

public boolean postCar(CarDto carDto,MultipartFile imageFile) throws IOException{
	try {
		 log.info("Received CarDto: {}", carDto);
	Car car =new Car();
	car.setName(carDto.getName());
	car.setBrand(carDto.getBrand());
	car.setColor(carDto.getColor());
	car.setPrice(carDto.getPrice());
	car.setYear(carDto.getYear());
	car.setType(carDto.getType());
	car.setDescription(carDto.getDescription());
	car.setTransmission(carDto.getTransmission());
	if (carDto.getImageFile() != null) {
        car.setImage(carDto.getImageFile().getBytes());
    }
	carrepository.save(car);
	log.info("Car saved successfully: {}", car);
	return true;
	
	
}catch (Exception e) {
	log.error("Error processing carDto: {}", e.getMessage(), e);
	return false;
}

}

@Override
public List<CarDto> getAllCar() {
	
	return carrepository.findAll().stream().map(Car::getCarDto).collect(Collectors.toList());
}

@Override
public void deleteCar(Long id) {
	
	carrepository.deleteById(id);
	
}

@Override
public CarDto getCarById(Long id) {
	// TODO Auto-generated method stub
	Optional<Car> optionalCar=carrepository.findById(id);
	return optionalCar.map(Car::getCarDto).orElse(null);
	
}

@Override
public boolean updateCar(Long carId, CarDto carDto){
	Optional<Car>optionalCar = carrepository.findById(carId);
	if(optionalCar.isPresent()) {
		Car existingCar =optionalCar.get();
		existingCar.setPrice(carDto.getPrice());
		existingCar.setYear(carDto.getYear());
		existingCar.setType(carDto.getType());
		existingCar.setDescription(carDto.getDescription());
		existingCar.setTransmission(carDto.getTransmission());
		existingCar.setColor(carDto.getColor());
		existingCar.setName(carDto.getName());
		existingCar.setBrand(carDto.getBrand());
		carrepository.save(existingCar);
	}
	
	return false;
}

@Override
public List<BookACarDto> getBookings() {
	
	return bookACarRepository.findAll().stream().map(BookCar::getBookACarDto).collect(Collectors.toList());
}

@Override
public boolean changeBookingStatus(Long bookingId, String status) {
    Optional<BookCar> optionalBookcar = bookACarRepository.findById(bookingId);
    if (optionalBookcar.isPresent()) {
        BookCar existingbookCar = optionalBookcar.get();
        if (status.equals("APPROVED")) { 
            existingbookCar.setBookCarStatus(BookCarStatus.APPROVES);
        } else {
            existingbookCar.setBookCarStatus(BookCarStatus.REJECTED);
        }
        bookACarRepository.save(existingbookCar);
        return true;
    }
    return false;
}

//@Override
//public CarDtoListDto searchCar(SearchCarDto searchCarDto) {
//    if (searchCarDto == null) {
//        throw new IllegalArgumentException("Search criteria cannot be null");
//    }
//
//    try {
//        Car car = new Car();
//        car.setBrand(searchCarDto.getBrand());
//        car.setType(searchCarDto.getType());
//        car.setTransmission(searchCarDto.getTransmission());
//        car.setColor(searchCarDto.getColor());
//
//        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll()
//                .withMatcher("brand", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
//                .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
//                .withMatcher("transmission", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
//                .withMatcher("color", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
//
//        Example<Car> carExample = Example.of(car, exampleMatcher);
//
//        // Log the generated query
//        log.info("Generated query: {}", carExample);
//
//        List<Car> carList = carrepository.findAll(carExample);
//
//        CarDtoListDto carDtoListDto = new CarDtoListDto();
//        carDtoListDto.setCarDtoList(carList.stream().map(Car::getCarDto).collect(Collectors.toList()));
//
//        return carDtoListDto;
//    } catch (Exception e) {
//        log.error("Error occurred while searching for cars", e);
//        throw new RuntimeException("Error occurred while searching for cars", e);
//    }
//}
public List<Car> searchCars(String brand, String type, String transmission, String color) {
    return carrepository.searchCars(brand, type, transmission, color);
}
}
