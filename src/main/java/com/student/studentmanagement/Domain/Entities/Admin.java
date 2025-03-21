package com.student.studentmanagement.Domain.Entities;

import com.student.studentmanagement.Domain.Enums.UserType;
import jakarta.persistence.Entity;

import java.util.UUID;
@Entity
public class Admin extends User{
    private String Role;

    public Admin(String role) {
        Role = role;
    }

    public Admin(UUID userId, String lastName, String name, String emailAddress, String password, UserType userType, String role) {
        super(userId, lastName, name, emailAddress, password, userType);
        Role = role;
    }
}
