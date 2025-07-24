package com.rafaelsisoares.car_dealership.controllers;

import com.rafaelsisoares.car_dealership.controllers.dto.PersonCreationDto;
import com.rafaelsisoares.car_dealership.controllers.dto.PersonDto;
import com.rafaelsisoares.car_dealership.entities.Person;
import com.rafaelsisoares.car_dealership.services.PersonService;
import com.rafaelsisoares.car_dealership.services.exceptions.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<PersonDto> createPerson(@RequestBody PersonCreationDto person) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                PersonDto.fromEntity(personService.createPerson(person.toEntity()))
        );
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> findAllPersons() {
        return ResponseEntity.status(HttpStatus.OK).body(
                personService.findAllPersons().stream().map(PersonDto::fromEntity).toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> findPersonById(@PathVariable Long id) throws PersonNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(
                PersonDto.fromEntity(personService.findPersonById(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> updatePerson(@PathVariable Long id, @RequestBody PersonCreationDto person) throws PersonNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(
                PersonDto.fromEntity(personService.updatePerson(id, person.toEntity()))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }
}
