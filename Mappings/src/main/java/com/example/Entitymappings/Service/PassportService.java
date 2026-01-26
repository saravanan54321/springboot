package com.example.Entitymappings.Service;

// PassportService.java

import com.example.Entitymappings.DTO.PassportRequest;
import com.example.Entitymappings.Entity.Passport;
import com.example.Entitymappings.Entity.Student;
import com.example.Entitymappings.Repositiory.PassportRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PassportService {

    private final PassportRepository passportRepository;


    public PassportService(PassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }
// tried  unidirectional -> for bidirection check studenservice
    public Passport savePassport(PassportRequest request) {

        Student student = new Student();
        student.setName(request.getStudentName());

        Passport passport = new Passport();
        passport.setPassportNumber(request.getPassportNumber());
        passport.setStudent(student);

        return passportRepository.save(passport);
    }
// ------------------------i am feching details from passport side-----------------------------------
   // using passport id
    public Passport getPassport(Long passportId) {
        Passport p =passportRepository.findById(passportId)
                .orElseThrow(() -> new RuntimeException("Passport not found"));

        System.out.println(p);
        System.out.println(p.getStudent());
        return  p;
    }

    //fetching details using studentid in the passport table
    //findByStudentId-> i have defined this in PassportRepository jpa repository-> go and check
    // why we cant use same findbyid here
    //What findById() really does ->SELECT * FROM passport WHERE id = ?
    //What findByStudentId() really does->SELECT * FROM passport WHERE student_id = ?
    //Why you CANNOT use findById() with studentId->studentId == passport.id
    //findById() works only for the entity’s own primary key, never for foreign keys.
    //We need findByStudentId() because the passport is linked to student via a foreign key;
    // findById() can only search by the passport’s primary key, not by the associated student’s ID.
    @Transactional
    public Passport getPassportByStudentId(Long studentId) {

        return passportRepository.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("Passport not found for student id: " + studentId));
    }

//------------------------------------------------------------------------------------------------

    //feching details from Student side-> checks Studentservice


}

