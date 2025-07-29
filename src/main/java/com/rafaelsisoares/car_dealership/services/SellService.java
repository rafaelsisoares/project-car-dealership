package com.rafaelsisoares.car_dealership.services;

import com.rafaelsisoares.car_dealership.entities.Car;
import com.rafaelsisoares.car_dealership.entities.Person;
import com.rafaelsisoares.car_dealership.entities.Sell;
import com.rafaelsisoares.car_dealership.repositories.SellRepository;
import com.rafaelsisoares.car_dealership.services.exceptions.CarNotFoundException;
import com.rafaelsisoares.car_dealership.services.exceptions.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SellService {
    private final SellRepository sellRepository;
    private final CarService carService;
    private final PersonService personService;

    @Autowired
    public SellService(SellRepository sellRepository, CarService carService, PersonService personService) {
        this.sellRepository = sellRepository;
        this.carService = carService;
        this.personService = personService;
    }

    public Sell createSell(Long carId, Long sellerId, Long customerId) throws CarNotFoundException, PersonNotFoundException {
        Car car = carService.findCarById(carId);
        Person seller = personService.findPersonById(sellerId);
        Person customer = personService.findPersonById(customerId);

        Sell newSell = new Sell(seller, customer, car, LocalDate.now());
        return sellRepository.save(newSell);
    }

    public List<Sell> findAllSells() {
        return sellRepository.findAll();
    }
}
