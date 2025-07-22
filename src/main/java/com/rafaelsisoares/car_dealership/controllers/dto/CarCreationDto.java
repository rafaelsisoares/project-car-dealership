package com.rafaelsisoares.car_dealership.controllers.dto;

import com.rafaelsisoares.car_dealership.entities.Car;

public record CarCreationDto(String brand,
                             String model,
                             String color,
                             Integer year,
                             String engineType,
                             Double price) {

    public Car toEntity() {
        return new Car(brand, model, color, year, engineType, price);
    }
}
