package com.example.Entitymappings.Controller;



public class PassportResponse {

    private Long id;
    private String passportNumber;
    private Long studentId;
    private String studentName;

    // No-args constructor
    public PassportResponse() {
    }

    // Optional convenience constructor
    public PassportResponse(Long id, String passportNumber,
                            Long studentId, String studentName) {
        this.id = id;
        this.passportNumber = passportNumber;
        this.studentId = studentId;
        this.studentName = studentName;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return "PassportResponse{" +
                "id=" + id +
                ", passportNumber='" + passportNumber + '\'' +
                ", studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                '}';
    }
}
