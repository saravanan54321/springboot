package com.example.Entitymappings.Entity;

// Passport.java


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "passport")
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passportNumber;
  //
  // were we have to mention cascadetype.all only in owning side or non owning side ?
  //  Always put cascade on the side that owns the foreign key (@JoinColumn).
    //Why not non-owning side?
    //mappedBy tells Hibernate: "this is just a mirror, do not control FK"


//    If you put cascade here:
//
//
//    Saving Student may not save Passport correctly
//
//
//    Foreign key in Passport may not be set
//
//
//    Leads to inconsistent DB state

    @OneToOne(cascade = CascadeType.ALL)
    // student_id column present in passport table
    @JoinColumn(name = "student_id")
    //edhu outpu vanthu continuues ha print agita erunthuthu athan potum

    private Student student;

    public Long getId() {
        return id;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", passportNumber='" + passportNumber + '\'' +
                ", studentId=" + (student != null ? student.getId() : null) +
                '}';
    }
}
