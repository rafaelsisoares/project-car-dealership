package com.rafaelsisoares.car_dealership.controllers.dto;

import com.rafaelsisoares.car_dealership.entities.Person;

public record PersonDto(Long id, String firstName, String lastName, String username, String role) {
    public static PersonDto fromEntity(Person person) {
        return new PersonDto(person.getId(),
                person.getFirstName(),
                person.getLastName(),
                person.getUsername(),
                person.getRole());
    }
}
