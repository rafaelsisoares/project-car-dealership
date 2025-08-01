package com.rafaelsisoares.car_dealership.services;

import com.rafaelsisoares.car_dealership.entities.Car;
import com.rafaelsisoares.car_dealership.entities.Person;
import com.rafaelsisoares.car_dealership.entities.Sell;
import com.rafaelsisoares.car_dealership.repositories.SellRepository;
import com.rafaelsisoares.car_dealership.services.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    public Sell createSell(Long carId, Long sellerId, Long customerId)
            throws CarNotFoundException,
            PersonNotFoundException,
            SamePersonException,
            CarUnavailableException {
        if (sellerId.equals(customerId)) {
            throw new SamePersonException();
        }
        Car car = carService.findCarById(carId);
        Person seller = personService.findPersonById(sellerId);
        Person customer = personService.findPersonById(customerId);

        if (!car.getAvailable()) {
            throw new CarUnavailableException();
        }

        Sell newSell = new Sell(seller, customer, car, LocalDate.now());
        carService.changeAvailability(carId);
        return sellRepository.save(newSell);
    }

    public List<Sell> findAllSells() {
        return sellRepository.findAll();
    }

    public Sell findSellById(Long id) throws SellNotFoundException {
        Optional<Sell> sell = sellRepository.findById(id);

        if(sell.isEmpty()) {
            throw new SellNotFoundException();
        }
        return sell.get();
    }

    public Sell updateSell(Long sellId, Long carId, Long sellerId, Long customerId) throws SellNotFoundException, CarNotFoundException, PersonNotFoundException {
        Sell sellFromDb = findSellById(sellId);

        sellFromDb.setCar(carService.findCarById(carId));
        sellFromDb.setSeller(personService.findPersonById(sellerId));
        sellFromDb.setCustomer(personService.findPersonById(customerId));

        return sellRepository.save(sellFromDb);
    }

    public void deleteSell(Long id) throws SellNotFoundException {
        Sell sell = findSellById(id);

        sellRepository.delete(sell);
    }
}
