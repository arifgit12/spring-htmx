package com.example.app;

import com.example.app.entity.*;
import com.example.app.repository.PersonRepository;
import com.example.app.repository.StudentRepository;
import com.example.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
        // Create roles
        Role adminRole = userService.createRole("ADMIN");
        Role userRole = userService.createRole("USER");

        // Create a person
        Person person = new Person();
        person.setPrefix("Mr.");
        person.setFirstName("Arif");
        person.setMiddleName("Ali");
        person.setLastName("Mondal");
        person.setSuffix("III");
        person.setPersonType(PersonType.FATHER);
        person.setEmploymentStatus(EmploymentStatus.EMPLOYED);

        // Create emails
        Email personalEmail = new Email();
        personalEmail.setEmailAddress("arifmondal.personal@mail.com");
        personalEmail.setType(EmailType.PERSONAL);
        personalEmail.setPerson(person);

        Email workEmail = new Email();
        workEmail.setEmailAddress("arifmondal.work@mail.com");
        workEmail.setType(EmailType.WORK);
        workEmail.setPerson(person);

        Set<Email> emails = new HashSet<>();
        emails.add(personalEmail);
        emails.add(workEmail);
        person.setEmails(emails);

        // Create phone numbers
        PhoneNumber homePhone = new PhoneNumber();
        homePhone.setNumber("123-456-7890");
        homePhone.setType(PhoneType.HOME);
        homePhone.setPerson(person);

        PhoneNumber mobilePhone = new PhoneNumber();
        mobilePhone.setNumber("098-765-4321");
        mobilePhone.setType(PhoneType.MOBILE);
        mobilePhone.setPerson(person);

        Set<PhoneNumber> phoneNumbers = new HashSet<>();
        phoneNumbers.add(homePhone);
        phoneNumbers.add(mobilePhone);
        person.setPhoneNumbers(phoneNumbers);

        // Create and associate an employer if employed
        if (person.getEmploymentStatus().equals(EmploymentStatus.UNEMPLOYED)) {
            Employer employer = new Employer();
            employer.setBusinessName("TechCorp");
            employer.setAddress1("123 Tech Lane");
            employer.setCity("Innovation City");
            employer.setState("TechState");
            employer.setCountry("Techland");
            employer.setZip("12345");
            employer.setSupervisorName("Sarah Supervisor");
            employer.setSupervisorPhoneNumber("321-654-0987");
            employer.setSupervisorEmail("sarah.supervisor@techcorp.com");

            // Use the helper method to add the employer to the person
            person.addEmployer(employer);
        }


        // Create and add addresses to the person
        PersonAddress mailingAddress = new PersonAddress();
        mailingAddress.setAddressType(AddressType.MAILING);
        mailingAddress.setAddress1("789 Maple Street");
        mailingAddress.setAddress2("Apt 101");
        mailingAddress.setCity("Hometown");
        mailingAddress.setState("HomeState");
        mailingAddress.setZip("56789");
        mailingAddress.setCountryCd("US");
        mailingAddress.setPerson(person);  // Associate with person

        PersonAddress physicalAddress = new PersonAddress();
        physicalAddress.setAddressType(AddressType.PHYSICAL);
        physicalAddress.setAddress1("456 Oak Avenue");
        physicalAddress.setCity("Worktown");
        physicalAddress.setState("WorkState");
        physicalAddress.setZip("98765");
        physicalAddress.setCountryCd("US");
        physicalAddress.setPerson(person);  // Associate with person

        Set<PersonAddress> addresses = new HashSet<>();
        addresses.add(mailingAddress);
        addresses.add(physicalAddress);
        person.setAddresses(addresses);  // Associate addresses with person

        // Save the person (including the employers and addresses)
        personRepository.save(person);

        // Create a user with a hashed password and assign roles
        Set<Role> roles = new HashSet<>();
//        roles.add(adminRole);
        roles.add(userRole);
        User user = userService.createUser("arifmondal@gmail.com", "securepassword", person, roles);

        // Create a student
        Student student = new Student();
        student.setFirstName("Arif");
        student.setMiddleName("Ali");
        student.setLastName("Mondal");
        student.setSuffix("");
        student.setSex(Sex.MALE);
        student.setLastSchoolAttended("Old School");
        student.setCurrentGrade("5th");

        Set<Person> guardians = new HashSet<>();
        guardians.add(person);
        student.setGuardians(guardians);

        // Save the student
        studentRepository.save(student);
    }
}
