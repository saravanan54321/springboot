package com.example.Entitymappings.Service;

// PassportService.java

import com.example.Entitymappings.Controller.PassportResponse;
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
@Transactional
public PassportResponse savePassport(PassportRequest request) {

    // 1️⃣ Create Student
    Student student = new Student();
    student.setName(request.getStudentName());

    // 2️⃣ Create Passport
    Passport passport = new Passport();
    passport.setPassportNumber(request.getPassportNumber());

    // 3️⃣ Link only owning side first
    passport.setStudent(student);

    // Check memory before inverse side
    System.out.println("Before setting inverse side:");
    System.out.println("student.getPassport() -> " + student.getPassport()); // null ❌
    System.out.println("passport.getStudent().getName() -> " + passport.getStudent().getName()); // Alice ✅

    // 4️⃣ Now link inverse side
    // this one is optional for dB only java side it is required
    // without this i can get the data from both side form db  using this mappining
    // alone  passport.setStudent(student); this is mandatory for db
    student.setPassport(passport);

    // Check memory after inverse side
    System.out.println("After setting inverse side:");
    System.out.println("student.getPassport().getPassportNumber() -> " + student.getPassport().getPassportNumber()); // P123 ✅
    System.out.println("passport.getStudent().getName() -> " + passport.getStudent().getName()); // Alice ✅

    // 5️⃣ Save (owning side)
    Passport savedPassport = passportRepository.save(passport);

    // 6️⃣ Map Entity → Response DTO
    PassportResponse response = new PassportResponse();
    response.setId(savedPassport.getId());
    response.setPassportNumber(savedPassport.getPassportNumber());
    response.setStudentId(savedPassport.getStudent().getId());
    response.setStudentName(savedPassport.getStudent().getName());

    return response;
}
// ------------------------i am feching details from passport side-----------------------------------

    // for get bidirectional linke is not requried from java side it will fetch data from db and links
   // using passport id
public PassportResponse getPassport(Long passportId) {
    //select * from passport where passport_id  = ?
    Passport p = passportRepository.findById(passportId)
            .orElseThrow(() -> new RuntimeException("Passport not found"));

    PassportResponse response = new PassportResponse();

    response.setId(p.getId());
    response.setPassportNumber(p.getPassportNumber());

    if (p.getStudent() != null) {
        response.setStudentId(p.getStudent().getId());
        response.setStudentName(p.getStudent().getName());
    }

    return response;
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
    @Transactional(readOnly = true)
    public PassportResponse getPassportByStudentId(Long studentId) {
      //select * from passport where student_id = ?

                Passport p = passportRepository.findByStudentId(studentId)
                .orElseThrow(() ->
                        new RuntimeException("Passport not found for student id: " + studentId)
                );

        PassportResponse response = new PassportResponse();
        response.setId(p.getId());
        response.setPassportNumber(p.getPassportNumber());

        if (p.getStudent() != null) {
            response.setStudentId(p.getStudent().getId());
            response.setStudentName(p.getStudent().getName());
        }

        return response;
    }


//------------------------------------------------------------------------------------------------




}

