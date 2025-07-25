package com.rafaelsisoares.car_dealership.repositories;

import com.rafaelsisoares.car_dealership.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
