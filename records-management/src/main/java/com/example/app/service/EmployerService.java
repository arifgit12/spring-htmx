package com.example.app.service;

import com.example.app.entity.Employer;
import com.example.app.entity.Person;
import com.example.app.repository.EmployerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployerService {
    private final EmployerRepository employerRepository;

    public EmployerService(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
    }

    public Optional<Employer> getEmployerById(Long employerId) {
        return employerRepository.findById(employerId);
    }

    public void saveOrUpdateEmployer(Person person, Employer employer) {
        employer.setPerson(person);
        employerRepository.save(employer);
    }

    public void deleteEmployer(Long employerId) {
        employerRepository.deleteById(employerId);
    }
}
