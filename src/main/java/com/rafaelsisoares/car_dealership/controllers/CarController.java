package com.rafaelsisoares.car_dealership.controllers;

import com.rafaelsisoares.car_dealership.controllers.dto.CarCreationDto;
import com.rafaelsisoares.car_dealership.controllers.dto.CarDto;
import com.rafaelsisoares.car_dealership.entities.Car;
import com.rafaelsisoares.car_dealership.services.CarService;
import com.rafaelsisoares.car_dealership.services.exceptions.CarNotFoundException;
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

    @GetMapping("/{id}")
    public ResponseEntity<Car> findCarById(@PathVariable Long id) throws CarNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(carService.findCarById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody CarCreationDto newCar) throws CarNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(carService.updateCar(id, newCar.toEntity()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Long id) throws CarNotFoundException {
        carService.deleteCar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }
}
