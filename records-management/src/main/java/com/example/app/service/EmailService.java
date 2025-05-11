package com.example.app.service;

import com.example.app.entity.Email;
import com.example.app.entity.Person;
import com.example.app.repository.EmailRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailService {
    private final EmailRepository emailRepository;

    public EmailService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public Optional<Email> getEmailById(Long emailId) {
        return emailRepository.findById(emailId);
    }

    public void saveOrUpdateEmail(Person person, Email email) {
        email.setPerson(person);
        emailRepository.save(email);
    }

    public void deleteEmail(Long emailId) {
        emailRepository.deleteById(emailId);
    }
}
