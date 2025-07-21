package com.rafaelsisoares.car_dealership.controllers.dto;

public record CarDto(Long id,
                     String brand,
                     String model,
                     String color,
                     Integer year,
                     String engineType,
                     Double price) {

    public CarDto fromEntity() {
        return new CarDto(id, brand, model, color, year, engineType, price);
    }
}
