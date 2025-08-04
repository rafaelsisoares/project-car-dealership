package com.rafaelsisoares.car_dealership.controllers;

import com.rafaelsisoares.car_dealership.controllers.dto.SellDto;
import com.rafaelsisoares.car_dealership.services.SellService;
import com.rafaelsisoares.car_dealership.services.exceptions.CarNotFoundException;
import com.rafaelsisoares.car_dealership.services.exceptions.CarUnavailableException;
import com.rafaelsisoares.car_dealership.services.exceptions.PersonNotFoundException;
import com.rafaelsisoares.car_dealership.services.exceptions.SamePersonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sells")
@EnableMethodSecurity
public class SellController {
    private final SellService sellService;

    @Autowired
    public SellController(SellService sellService) {
        this.sellService = sellService;
    }

    @PostMapping("/car/{carId}/seller/{sellerId}/customer/{customerId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SELLER')")
    public ResponseEntity<SellDto> createSell(@PathVariable Long carId,
                                              @PathVariable Long sellerId,
                                              @PathVariable Long customerId)
            throws CarNotFoundException, PersonNotFoundException, SamePersonException, CarUnavailableException {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                SellDto.fromEntity(sellService.createSell(carId, sellerId, customerId))
        );
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SELLER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<SellDto>> findAllSells() {
        return ResponseEntity.status(HttpStatus.OK).body(
                sellService.findAllSells().stream().map(SellDto::fromEntity).toList()
        );
    }
}
