package com.rafaelsisoares.car_dealership.services;

import com.rafaelsisoares.car_dealership.entities.Person;
import com.rafaelsisoares.car_dealership.repositories.PersonRepository;
import com.rafaelsisoares.car_dealership.services.exceptions.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    private String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public Person createPerson(Person person) {
        String hashedPassword = encodePassword(person.getPassword());

        person.setPassword(hashedPassword);
        return personRepository.save(person);
    }

    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }

    public Person findPersonById(Long id) throws PersonNotFoundException {
        Optional<Person> person = personRepository.findById(id);

        if (person.isEmpty()) {
            throw new PersonNotFoundException();
        }

        return person.get();
    }

    public Person updatePerson(Long id, Person person) throws PersonNotFoundException {
        Person personFromDb = findPersonById(id);

        personFromDb.setFirstName(person.getFirstName());
        personFromDb.setLastName(person.getLastName());
        personFromDb.setUsername(person.getUsername());
        personFromDb.setPassword(encodePassword(person.getPassword()));
        personFromDb.setRole(person.getRole());

        return personRepository.save(personFromDb);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}
