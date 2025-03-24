package com.student.studentmanagement.Domain.Entities;

import com.student.studentmanagement.Domain.Enums.UserType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Student extends EndUser {
    private String enrollmentNumber;  // Fixed spelling


    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Mark> marks = new HashSet<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StudentAbsence> studentAbsences = new HashSet<>();
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;

    public Student() {
    }

    public Student(String enrollmentNumber) {
        this.enrollmentNumber = enrollmentNumber;
    }

    public Student(String lastName, String name, String emailAddress, String password,  String enrollmentNumber) {
        super(lastName, name, emailAddress, password, UserType.STUDENT);
        this.enrollmentNumber = enrollmentNumber;
    }

    public String getEnrollmentNumber() {
        return enrollmentNumber;
    }

    public void setEnrollmentNumber(String enrollmentNumber) {
        this.enrollmentNumber = enrollmentNumber;
    }


    public Set<Mark> getMarks() {
        return marks;
    }

    public Set<StudentAbsence> getStudentAbsences() {
        return studentAbsences;
    }




    public void addMark(Mark mark) {
        marks.add(mark);
        mark.setStudent(this);
    }

    public void removeMark(Mark mark) {
        marks.remove(mark);
        mark.setStudent(null);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
