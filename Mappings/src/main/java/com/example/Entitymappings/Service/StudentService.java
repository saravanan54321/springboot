package com.example.Entitymappings.Service;




import com.example.Entitymappings.Controller.StudentResponse;
import com.example.Entitymappings.Entity.Passport;
import com.example.Entitymappings.Entity.Student;
import com.example.Entitymappings.Repositiory.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }



    @Transactional
    public StudentResponse saveStudentWithPassport(String studentName, String passportNumber) {

        // 1️⃣ Create student
        Student student = new Student();
        student.setName(studentName);

        // 2️⃣ Create passport
        Passport passport = new Passport();
        passport.setPassportNumber(passportNumber);

        // 3️⃣ Link bidirectional

        //What happens before saving (only linking objects in memory)
        //student.setPassport(passport);
        //passport.setStudent(student);
        //
        //
        //Both entities exist only in memory (Java objects)
        //
        //They are not yet persisted in the database
        //
        //They are managed only if you are inside a transaction and attached to a persistence context
        //
        //So just calling setPassport / setStudent does NOT write to DB yet
        student.setPassport(passport);// Student side
        passport.setStudent(student);     // Passport owning side

        // 4️⃣ Save using owning side
        // You can save either Student or Passport; because of CascadeType.ALL, it works
        // will it save both the side
        //What actually happens with CascadeType.ALL
        //
        //CascadeType.ALL tells Hibernate:
        //
        //“When I save this entity, also save all the related entities.”
        //
        //Now the order is automatic inside Hibernate:
        //
        //Hibernate sees you are saving Student
        //
        //Student has Passport linked
        //
        //Passport is the owning side, cascade = ALL
        //
        //Hibernate first inserts Student (so ID is generated)
        //
        //Then inserts Passport with student_id = generated ID

        // i got this doubt  cascade All is responsible to save db ?
        //Role of CascadeType.ALL
        //
        //Cascade ALL does not automatically save by itself
        //
        //It tells Hibernate:
        //
        //“When I save the parent entity, also save all associated child entities automatically.”
        //
        //Example:
        //
        //studentRepository.save(student);  // triggers cascade → saves Passport too
        //
        //
        //Without save():

        //Nothing goes to the database
        //
        //Only linked in memory

        //cascacde will comes it into picture only if you save something if you fetch something it will not comes into picture am i right?
        //Yes. You are right.
        // Let me state it cleanly and precisely, no extra noise.
        //
        //✅ What CascadeType actually does
        //Cascade affects ONLY write operations, not read operations
//conclusion
        //cascade tells what are all the entities needs to be stored in db
        //save method is used to save the data in db
        Student savedStudent = studentRepository.save(student);

        // 5️⃣ Map Entity → DTO
        StudentResponse response = new StudentResponse();
        response.setId(savedStudent.getId());
        response.setName(savedStudent.getName());

        if (savedStudent.getPassport() != null) {
            response.setPassportNumber(savedStudent.getPassport().getPassportNumber());
        }

        return response;
    }




    // GET student by studentId
    @Transactional(readOnly = true)
    public StudentResponse getStudent(Long studentId) {
        // hibernate will execute this queury
//        Hibernate internally does something like:
//
//        SELECT s.*, p.*
//                FROM student s
//        LEFT JOIN passport p ON p.student_id = s.id
//        WHERE s.id = ?
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        StudentResponse response = new StudentResponse();
        response.setId(student.getId());
        response.setName(student.getName());

        if (student.getPassport() != null) {
            response.setPassportNumber(student.getPassport().getPassportNumber());
        }

        return response;
    }

}

