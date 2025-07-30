package com.rafaelsisoares.car_dealership.controllers.dto;

import com.rafaelsisoares.car_dealership.entities.Sell;

import java.time.LocalDate;

public record SellDto(Long id, CarDto car, PersonDto seller, PersonDto customer, LocalDate date) {
    public static SellDto fromEntity(Sell sell) {
        return new SellDto(
                sell.getId(),
                CarDto.fromEntity(sell.getCar()),
                PersonDto.fromEntity(sell.getSeller()),
                PersonDto.fromEntity(sell.getCustomer()),
                sell.getDate()
        );
    }
}
