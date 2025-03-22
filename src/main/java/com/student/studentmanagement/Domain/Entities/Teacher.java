package com.student.studentmanagement.Domain.Entities;

import com.student.studentmanagement.Domain.Enums.UserType;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Teacher extends EndUser {
    private String grade;  // Changed from Grade to follow Java naming conventions

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TeacherCourse> teacherCourses = new HashSet<>();

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TeacherAbsence> teacherAbsences = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    // Default constructor needed by JPA
    public Teacher() {
    }

    public Teacher(String grade) {
        this.grade = grade;
    }

    public Teacher(UUID userId, String lastName, String name, String emailAddress, String password, UserType userType, String grade) {
        super(userId, lastName, name, emailAddress, password, userType);
        this.grade = grade;
    }

    // Getters and setters
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Set<TeacherCourse> getTeacherCourses() {
        return teacherCourses;
    }

    public Set<TeacherAbsence> getTeacherAbsences() {
        return teacherAbsences;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
        if (subject != null) {
            subject.setTeacher(this);
        }
    }
}
