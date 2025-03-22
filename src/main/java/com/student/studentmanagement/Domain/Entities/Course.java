package com.student.studentmanagement.Domain.Entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID courseId;

    private String courseName;

    @ManyToOne
    @JoinColumn(name = "level_id", nullable = false)
    private Level level;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Enrollment> enrollments = new HashSet<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StudentAbsence> studentAbsences = new HashSet<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TeacherCourse> teacherCourses = new HashSet<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TeacherAbsence> teacherAbsences = new HashSet<>();

    public Course() {
    }

    public UUID getCourseId() {
        return courseId;
    }

    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Set<Enrollment> getEnrollments() {
        return enrollments;
    }

    public Set<StudentAbsence> getStudentAbsences() {
        return studentAbsences;
    }

    public Set<TeacherCourse> getTeacherCourses() {
        return teacherCourses;
    }

    public Set<TeacherAbsence> getTeacherAbsences() {
        return teacherAbsences;
    }
}