package com.rafaelsisoares.car_dealership.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "cars")
public class Car {
    private Long id;
    private String brand;
    private String model;
    private Integer year;
    private String engineType;
    private Double price;
}
