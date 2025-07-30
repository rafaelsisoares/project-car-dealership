package com.rafaelsisoares.car_dealership.services.exceptions;

public class SamePersonException extends BadRequestException{
    public SamePersonException() {
        super("Vendedor e comprador sao a mesma pessoa.");
    }
}
