package com.codewithprojects.car_rent.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codewithprojects.car_rent.entity.Car;
@Repository
public interface CarRepository extends JpaRepository<Car, Long>{

    @Query("SELECT c FROM Car c WHERE " +
            "(:brand IS NULL OR LOWER(c.brand) LIKE LOWER(CONCAT('%', :brand, '%'))) " +
            "AND (:type IS NULL OR LOWER(c.type) LIKE LOWER(CONCAT('%', :type, '%'))) " +
            "AND (:transmission IS NULL OR LOWER(c.transmission) LIKE LOWER(CONCAT('%', :transmission, '%'))) " +
            "AND (:color IS NULL OR LOWER(c.color) LIKE LOWER(CONCAT('%', :color, '%')))")
    List<Car> searchCars(String brand, String type, String transmission, String color);

	
}
