package com.example.Entitymappings.Repositiory;

// StudentRepository.java

import com.example.Entitymappings.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
