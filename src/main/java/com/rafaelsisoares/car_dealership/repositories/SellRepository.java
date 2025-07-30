package com.rafaelsisoares.car_dealership.repositories;

import com.rafaelsisoares.car_dealership.entities.Sell;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellRepository extends JpaRepository<Sell, Long> {
}
