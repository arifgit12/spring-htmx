package com.example.app.service;

import com.example.app.entity.Person;
import com.example.app.entity.PersonAddress;
import com.example.app.repository.PersonAddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonAddressService {

    private final PersonAddressRepository personAddressRepository;

    public PersonAddressService(PersonAddressRepository personAddressRepository) {
        this.personAddressRepository = personAddressRepository;
    }

    public List<PersonAddress> findPersonAddressesByPersonId(final Long personId){
        return personAddressRepository.findByPerson_PersonId(personId);
    }

    public Optional<PersonAddress> getPersonAddressById(Long addressId) {
        return personAddressRepository.findById(addressId);
    }

    public void saveOrUpdatePersonAddress(Person person, PersonAddress personAddress) {
        personAddress.setPerson(person);
        personAddressRepository.save(personAddress);
    }

    public void deletePersonAddress(Long addressId) {
        personAddressRepository.deleteById(addressId);
    }
}
