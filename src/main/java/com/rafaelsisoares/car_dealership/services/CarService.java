package com.rafaelsisoares.car_dealership.services;

import com.rafaelsisoares.car_dealership.entities.Car;
import com.rafaelsisoares.car_dealership.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Car findCarById(Long id) {
        return carRepository.findById(id).orElseThrow(new CarNotFoundException());
    }

    public Car updateCar(Long id, Car car) {
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
