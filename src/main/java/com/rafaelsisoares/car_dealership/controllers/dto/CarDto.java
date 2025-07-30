package com.rafaelsisoares.car_dealership.controllers.dto;

import com.rafaelsisoares.car_dealership.entities.Car;

public record CarDto(Long id,
                     String brand,
                     String model,
                     String color,
                     Integer year,
                     String engineType,
                     Double price,
                     Boolean isAvailable) {

    public static CarDto fromEntity(Car car) {
        return new CarDto(car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getColor(),
                car.getYear(),
                car.getEngineType(),
                car.getPrice(),
                car.getAvailable());
    }
}
