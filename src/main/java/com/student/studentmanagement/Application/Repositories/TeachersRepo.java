package com.student.studentmanagement.Application.Repositories;

import com.student.studentmanagement.Domain.Entities.Admin;
import com.student.studentmanagement.Domain.Entities.Subject;
import com.student.studentmanagement.Domain.Entities.Teacher;

import java.util.List;
import java.util.regex.Pattern;

public class TeachersRepo extends BaseRepository<Teacher>{
    public TeachersRepo(){
        super(Teacher.class);
    }
    public List<Teacher> getAll(){
       return super.getAll();
    }
    public boolean createTeacher(String lastName, String name, String emailAddress, String password, String grade) {
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
        if (grade == null || grade.trim().isEmpty()) {
            throw new IllegalArgumentException("Role cannot be null or empty.");
        }
        Teacher teacher= new Teacher(lastName,name,emailAddress,password ,grade);
        return executeInTransaction(session -> {
            session.save(teacher);
            return true;
        });
    }
    public void addSubject(Teacher teacher , Subject subject){
        Teacher toUpdateTeacher = super.getById(teacher.getUserId());
        toUpdateTeacher.setSubject(subject);
        super.save(toUpdateTeacher);
    }
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}
