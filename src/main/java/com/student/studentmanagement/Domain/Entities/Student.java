package com.student.studentmanagement.Domain.Entities;

import com.student.studentmanagement.Domain.Enums.UserType;
import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

public class Student extends User{
    private String enrollementNumber;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Enrollement> enrollments;
    public Student(String enrollementNumber) {
        this.enrollementNumber = enrollementNumber;
    }

    public Student(UUID userId, String lastName, String name, String emailAddress, String password, UserType userType, String enrollementNumber) {
        super(userId, lastName, name, emailAddress, password, userType);
        this.enrollementNumber = enrollementNumber;
    }
}
