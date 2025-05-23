package com.example.app.repository;

import com.example.app.entity.PersonAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonAddressRepository extends JpaRepository<PersonAddress, Long> {
    List<PersonAddress> findByPerson_PersonId(Long personId);
}

