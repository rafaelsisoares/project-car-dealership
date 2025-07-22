package com.rafaelsisoares.car_dealership.services.exceptions;

public class CarNotFoundException extends NotFoundException{

    public CarNotFoundException() {
        super("Esse carro não foi encontrado.");
    }
}
