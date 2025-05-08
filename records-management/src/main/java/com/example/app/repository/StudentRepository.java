package com.example.app.repository;

import com.example.app.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByGuardians_PersonId(Long personId);
}
