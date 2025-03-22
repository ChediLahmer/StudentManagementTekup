package com.student.studentmanagement.Domain.Entities;

import com.student.studentmanagement.Domain.Enums.UserType;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Student extends EndUser {
    private String enrollmentNumber;  // Fixed spelling

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Enrollment> enrollments = new HashSet<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Mark> marks = new HashSet<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StudentAbsence> studentAbsences = new HashSet<>();

    // Default constructor needed by JPA
    public Student() {
    }

    public Student(String enrollmentNumber) {
        this.enrollmentNumber = enrollmentNumber;
    }

    public Student(UUID userId, String lastName, String name, String emailAddress, String password, UserType userType, String enrollmentNumber) {
        super(userId, lastName, name, emailAddress, password, userType);
        this.enrollmentNumber = enrollmentNumber;
    }

    // Getters and setters
    public String getEnrollmentNumber() {
        return enrollmentNumber;
    }

    public void setEnrollmentNumber(String enrollmentNumber) {
        this.enrollmentNumber = enrollmentNumber;
    }

    public Set<Enrollment> getEnrollments() {
        return enrollments;
    }

    public Set<Mark> getMarks() {
        return marks;
    }

    public Set<StudentAbsence> getStudentAbsences() {
        return studentAbsences;
    }

    // Helper methods for bidirectional relationships
    public void addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
        enrollment.setStudent(this);
    }

    public void removeEnrollment(Enrollment enrollment) {
        enrollments.remove(enrollment);
        enrollment.setStudent(null);
    }

    public void addMark(Mark mark) {
        marks.add(mark);
        mark.setStudent(this);
    }

    public void removeMark(Mark mark) {
        marks.remove(mark);
        mark.setStudent(null);
    }
}
