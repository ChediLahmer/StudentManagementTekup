package com.student.studentmanagement.Application.Repositories;

import com.student.studentmanagement.Domain.Entities.Admin;
import com.student.studentmanagement.Domain.Entities.EndUser;
import com.student.studentmanagement.Domain.Entities.Level;
import com.student.studentmanagement.Domain.Enums.UserType;
import com.student.studentmanagement.Domain.Helpers.PasswordUtil;

import java.util.regex.Pattern;

public class AdminRepo extends BaseRepository<Admin> {


    public AdminRepo() {
        super(Admin.class);
    }

    public boolean createAdmin(String lastName, String name, String emailAddress, String password, String role) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (emailAddress == null || !isValidEmail(emailAddress)) {
            throw new IllegalArgumentException("Invalid email address.");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }
        if (role == null || role.trim().isEmpty()) {
            throw new IllegalArgumentException("Role cannot be null or empty.");
        }
        Admin admin= new Admin(lastName,name,emailAddress,password, UserType.ADMIN, role);
        return executeInTransaction(session -> {
            session.save(admin);
            return true;
        });
    }
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}