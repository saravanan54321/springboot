package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PraticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PraticeApplication.class, args);
	}

}
//http://localhost:8080/users/get/2
//http://localhost:8080/users/put/2 - body {
//    "name":"Adithiya"
//}
//
//http://localhost:8080/users/delete/3
//http://localhost:8080/users/post
//body
//{
//		"name":"adhi"
//		}
//http://localhost:8080/users/all