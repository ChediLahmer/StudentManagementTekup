package com.student.studentmanagement.Domain.Entities;

import com.student.studentmanagement.Domain.Enums.UserType;
import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;
@Entity
public class Teacher extends EndUser {
    private String Grade;
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TeacherCourse> teacherCourses;
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TeacherAbsence> teacherAbsences;
    @OneToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
    public Teacher(String grade) {
        Grade = grade;
    }
    public Teacher(UUID userId, String lastName, String name, String emailAddress, String password, UserType userType, String grade) {
        super(userId, lastName, name, emailAddress, password, userType);
        Grade = grade;
    }
}
