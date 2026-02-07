package com.example.Entitymappings.Controller;

import com.example.Entitymappings.DTO.StudentRequest;
import com.example.Entitymappings.Entity.Student;
import com.example.Entitymappings.Service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // GET student by studentId
    @GetMapping("/{studentId}")
    public StudentResponse getStudent(@PathVariable Long studentId) {
        return studentService.getStudent(studentId);
    }

    @PostMapping
    public StudentResponse postingStudent(@RequestBody StudentRequest request ){
        return studentService.saveStudentWithPassport(
                request.getStudentName(),
                request.getPassportNumber()
        );
    }
}
