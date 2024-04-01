package com.codewithprojects.car_rent.controller;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.codewithprojects.car_rent.dto.BookACarDto;
import com.codewithprojects.car_rent.dto.CarDto;
import com.codewithprojects.car_rent.entity.Car;
import com.codewithprojects.car_rent.repository.CarRepository;
import com.codewithprojects.car_rent.services.auth.admin.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	AdminService adminservice;
	@Autowired
	CarRepository carrepository;
	@PostMapping("/car")
	public ResponseEntity<?> postCar(@RequestParam("imageFile") MultipartFile imageFile, @ModelAttribute CarDto carDto) throws IOException{
		try {
	        Boolean success = adminservice.postCar(carDto, imageFile);
	        if (success) {
	            return ResponseEntity.status(HttpStatus.CREATED).build();
	        } else {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to save car details");
	        }
	    } catch (IOException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the request");
	    }
	}
	@GetMapping("/cars")
	public ResponseEntity<?>getAllCars(){
		return ResponseEntity.ok(adminservice.getAllCar());
	}
	@DeleteMapping("/car/{id}")
	public ResponseEntity<Void>deleteCar(@PathVariable Long id){
		adminservice.deleteCar(id);
		return ResponseEntity.ok(null);
	}
	@GetMapping("/car/{id}")
	public ResponseEntity<CarDto> getCarById(@PathVariable Long id){
		CarDto carDto = adminservice.getCarById(id);
		return ResponseEntity.ok(carDto);
	}

	@PutMapping("/car/{carId}")
	public ResponseEntity<String> updateCar(@PathVariable Long carId, @RequestBody CarDto carDto) {
	    boolean updated = adminservice.updateCar(carId, carDto);

	    if (updated) {
	        return ResponseEntity.ok("Car updated successfully");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car with ID " + carId + " not found");
	    }
	} 
	@GetMapping("/car/bookings")
	public ResponseEntity<List<BookACarDto>>getBookings(){
		return ResponseEntity.ok(adminservice.getBookings());
	}
	@GetMapping("/car/booking/{bookingId}/{status}")
	public ResponseEntity<?> changeBookingStatus(@PathVariable Long bookingId, @PathVariable String status) {
	    boolean success = adminservice.changeBookingStatus(bookingId, status);
	    if (success) {
	        return ResponseEntity.ok().build();
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
//	@PostMapping("/car/search")
//	public ResponseEntity<?>searchCar(@RequestBody SearchCarDto searchCarDto){
//		return ResponseEntity.ok(adminservice.searchCar(searchCarDto));
//	}
	 @GetMapping("/search")
	    public ResponseEntity<List<Car>> searchCars(@RequestParam(required = false) String brand,
	                                                @RequestParam(required = false) String type,
	                                                @RequestParam(required = false) String transmission,
	                                                @RequestParam(required = false) String color) {
	        List<Car> cars = adminservice.searchCars(brand, type, transmission, color);
	        return ResponseEntity.ok(cars);
	    }
}