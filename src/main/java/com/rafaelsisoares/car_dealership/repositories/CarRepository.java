package com.rafaelsisoares.car_dealership.repositories;

import com.rafaelsisoares.car_dealership.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByBrand(String brand);

    Optional<Car> findByModel(String model);
}
