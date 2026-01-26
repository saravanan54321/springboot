package com.example.Entitymappings.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    //mappedBy = "student"-> go and check the passort entity Student field we have
    //declared this as student that why we are giving here . Note same feild name we should give
    // otherwise it will throw error
    // what mapped means -Declares this side as NON-OWNING side of the relationship.
    //The foreign key is NOT here
    // we are just telling to spring this is just used for navigation
    // if you didnt put this it wil create foreign key in student table
    // if you removed and tried this
    //Now both sides are treated as owning sides by JPA.

    //Two owning sides = JPA thinks there are two separate relationships
    //
    //Hibernate will try to create TWO foreign keys,
    //What happens when you save
    //student.setPassport(passport);
    //passport.setStudent(student);
    //return studentRepository.save(student);
    //
    //Step by step
    //
    //Hibernate sees Student has Passport
    //
    //Hibernate sees Passport has Student
    //
    //Both sides are “owning” → confusion about which FK to use
    //
    //Hibernate will either:
    //
    //Fail with SchemaException / multiple FK mapping conflict
    //
    //Or create extra unnecessary table if DB allows it
    @OneToOne(mappedBy = "student")
    //edhu outpu vanthu continuues ha print agita erunthuthu athan potum
    @JsonBackReference
    private Passport passport;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
