package com.example.demo.dto;



public class UserDTO {

    private Long id;
    private String name;

    // Constructor
    public UserDTO() {}

    public UserDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }



}
