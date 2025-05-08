package com.example.app.repository;

import com.example.app.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    void findByUserEmail(String email);
}
