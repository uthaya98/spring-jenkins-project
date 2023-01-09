package com.ocpdemo.demo.people;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Table
@Entity
public class People {
    @Id
    private Long Id;

    @SequenceGenerator(
            name= "people_sequence",
            sequenceName = "people_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "people_sequence"
    )

    private String name;
    private String email;
    private String place;
    private LocalDate dob;

    @Transient
    private Integer age;

    public People() {
    }

    public People(Long id, String name, String email, String place, LocalDate dob) {
        Id = id;
        this.name = name;
        this.email = email;
        this.place = place;
        this.dob = dob;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setAge(Integer age){
        this.age = age;
    }

    public Integer getAge(){
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "People{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", place='" + place + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}
