package com.rafaelsisoares.car_dealership.controllers;

import com.rafaelsisoares.car_dealership.controllers.dto.CarCreationDto;
import com.rafaelsisoares.car_dealership.controllers.dto.CarDto;
import com.rafaelsisoares.car_dealership.entities.Car;
import com.rafaelsisoares.car_dealership.services.CarService;
import com.rafaelsisoares.car_dealership.services.exceptions.CarNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@EnableMethodSecurity
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CarDto> createCar(@RequestBody CarCreationDto car) {
        Car newCar = carService.createCar(car.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(CarDto.fromEntity(newCar));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SELLER') or hasAuthority('CUSTOMER')")
    public ResponseEntity<List<CarDto>> findAllCars() {
        List<Car> cars = carService.findAllCars();
        return ResponseEntity.status(HttpStatus.OK)
                .body(cars.stream().map(CarDto::fromEntity).toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SELLER') or hasAuthority('CUSTOMER')")
    public ResponseEntity<Car> findCarById(@PathVariable Long id) throws CarNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(carService.findCarById(id));
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<CarDto>> findCarsByBrand(@PathVariable String brand) {
        return ResponseEntity.status(HttpStatus.OK).body(
                carService.findCarsByBrand(brand).stream().map(CarDto::fromEntity).toList()
        );
    }

    @GetMapping("/model/{model}")
    public ResponseEntity<CarDto> findCarByModel(@PathVariable String model) throws CarNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(CarDto.fromEntity(carService.findCarByModel(model)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody CarCreationDto newCar) throws CarNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(carService.updateCar(id, newCar.toEntity()));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteCar(@PathVariable Long id) throws CarNotFoundException {
        carService.deleteCar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }

    @PutMapping("/{id}/available")
    public ResponseEntity<?> changeAvailability(@PathVariable Long id) throws CarNotFoundException {
        carService.changeAvailability(id);
        return ResponseEntity.status(HttpStatus.OK).body("Disponibilidade alterada.");
    }
}
