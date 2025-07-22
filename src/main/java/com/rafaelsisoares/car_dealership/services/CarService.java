package com.rafaelsisoares.car_dealership.services;

import com.rafaelsisoares.car_dealership.entities.Car;
import com.rafaelsisoares.car_dealership.repositories.CarRepository;
import com.rafaelsisoares.car_dealership.services.exceptions.CarNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public List<Car> findAllCars() {
        return carRepository.findAll();
    }

    public Car findCarById(Long id) throws CarNotFoundException {
        Optional<Car> car = carRepository.findById(id);

        if (car.isEmpty()) {
            throw new CarNotFoundException();
        }
        return car.get();
    }

    public Car updateCar(Long id, Car car) throws CarNotFoundException {
        Car carFromDb = findCarById(id);

        carFromDb.setBrand(car.getBrand());
        carFromDb.setModel(car.getModel());
        carFromDb.setColor(car.getColor());
        carFromDb.setYear(car.getYear());
        carFromDb.setEngineType(car.getEngineType());
        carFromDb.setPrice(car.getPrice());

        return carRepository.save(carFromDb);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
