package com.example.UtilityProject.Model;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Double ID;

    @Column(name = "FirstName", nullable = false)
    private String FirstName;

    @Column(name = "LastName", nullable = false)
    private String LastName;

    @Column(name = "Age", nullable = false)
    private Double Age;

    @Column(name = "Email", nullable = false)
    private String Email;

    @Column(name = "PhoneNumber", nullable = false)
    private String PhoneNumber;

    public Employee(Double ID, String firstName, String lastName, Double age, String email, String phoneNumber) {
        this.ID = ID;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Age = age;
        this.Email = email;
        this.PhoneNumber = phoneNumber;
    }

    public Employee() {

    }

    public Double getID() {
        return ID;
    }

    public void setID(Double ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public Double getAge() {
        return Age;
    }

    public void setAge(Double age) {
        Age = age;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }
}
