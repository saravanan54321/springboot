package com.example.Entitymappings.Controller;




import com.example.Entitymappings.DTO.PassportRequest;
import com.example.Entitymappings.Entity.Passport;
import com.example.Entitymappings.Service.PassportService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passports")
public class PassportController {

    private final PassportService passportService;

    public PassportController(PassportService passportService) {
        this.passportService = passportService;
    }

    @PostMapping
    public Passport createPassport(@RequestBody PassportRequest request) {
        return passportService.savePassport(request);
    }
   //http://localhost:8080/passports/1
    @GetMapping("/{id}")
    public Passport getPassport(@PathVariable Long id) {
        return passportService.getPassport(id);
    }

    //http://localhost:8080/passports/student/1
    @GetMapping("/student/{studentId}")
    public Passport getPassportByStudentId(@PathVariable Long studentId) {
        return passportService.getPassportByStudentId(studentId);
    }
}

