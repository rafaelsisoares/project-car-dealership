package com.rafaelsisoares.car_dealership.services.exceptions;

public class PersonNotFoundException extends NotFoundException{
    public PersonNotFoundException() {
        super("Esse usuario nao foi encontrado.");
    }
}
