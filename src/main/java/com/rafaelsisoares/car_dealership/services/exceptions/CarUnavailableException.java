package com.rafaelsisoares.car_dealership.services.exceptions;

public class CarUnavailableException extends BadRequestException{
    public CarUnavailableException() {
        super("Este carro nao esta disponivel");
    }
}
