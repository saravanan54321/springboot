package com.example.Entitymappings.Repositiory;
// PassportRepository.java

import com.example.Entitymappings.Entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassportRepository extends JpaRepository<Passport, Long> {

    //SELECT * FROM passport WHERE student_id = ?
    Optional<Passport> findByStudentId(Long studentId);
}
