package com.rafaelsisoares.car_dealership.controllers.dto;

import com.rafaelsisoares.car_dealership.entities.Person;

public record PersonCreationDto(String firstName, String lastName, String username, String password, String role) {
    public Person toEntity() {
        return new Person(firstName, lastName, username, password, role);
    }
}
