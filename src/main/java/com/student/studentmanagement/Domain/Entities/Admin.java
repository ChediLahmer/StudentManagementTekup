package com.student.studentmanagement.Domain.Entities;

import com.student.studentmanagement.Domain.Enums.UserType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.util.UUID;
@Entity
public class Admin extends EndUser {
    private String role;
    public Admin() {}

    public Admin(String role) {
        this.role = role;
    }

    public Admin(String lastName, String name, String emailAddress, String password, UserType userType, String role) {
        super(lastName, name, emailAddress, password, userType);
        this.role = role;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
