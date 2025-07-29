package com.rafaelsisoares.car_dealership.services.exceptions;

public class SellNotFoundException extends NotFoundException{
    public SellNotFoundException() {
        super("Essa venda nao foi encontrada.");
    }
}
