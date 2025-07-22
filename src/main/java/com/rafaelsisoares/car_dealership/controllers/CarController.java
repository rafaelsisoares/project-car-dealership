package com.rafaelsisoares.car_dealership.controllers;

import com.rafaelsisoares.car_dealership.controllers.dto.CarCreationDto;
import com.rafaelsisoares.car_dealership.controllers.dto.CarDto;
import com.rafaelsisoares.car_dealership.entities.Car;
import com.rafaelsisoares.car_dealership.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<CarDto> createCar(@RequestBody CarCreationDto car) {
        Car newCar = carService.createCar(car.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(CarDto.fromEntity(newCar));
    }

    @GetMapping
    public ResponseEntity<List<CarDto>> findAllCars() {
        List<Car> cars = carService.findAllCars();
        return ResponseEntity.status(HttpStatus.OK)
                .body(cars.stream().map(CarDto::fromEntity).toList());
    }
}
