package com.example.app.service;

import com.example.app.entity.Person;
import com.example.app.exception.PersonDoesNotExistException;
import com.example.app.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person getPersonById(final Long personId){
        Optional<Person> person = personRepository.findById(personId);
        if(person.isPresent()){
            return person.get();
        } else {
            throw new PersonDoesNotExistException("Person with ID " + personId + " does not exist");
        }
    }
}
