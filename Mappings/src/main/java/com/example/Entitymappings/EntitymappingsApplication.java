package com.example.Entitymappings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EntitymappingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntitymappingsApplication.class, args);
	}

}
//Passport
//Post -http://localhost:8080/passports- {
//  "studentName": "mom",
//  "passportNumber": "994012355"
//}
//Passport get -http://localhost:8080/passports/student/6
//Passport get -http://localhost:8080/passports/4
//
//Students
//Post - http://localhost:8080/students
//{
//  "studentName": "alice",
//  "passportNumber": "1234"
//}
//Student get
//http://localhost:8080/students/6