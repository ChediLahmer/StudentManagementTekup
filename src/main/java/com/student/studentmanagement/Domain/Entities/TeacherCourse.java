package com.student.studentmanagement.Domain.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"teacher_id", "course_id", "startDate", "endDate"})
})
public class TeacherCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID teacherCourseId;  // Changed from enrollmentId for clarity

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    private LocalDate startDate;
    private LocalDate endDate;

    public TeacherCourse() {
    }

    public UUID getTeacherCourseId() {
        return teacherCourseId;
    }

    public void setTeacherCourseId(UUID teacherCourseId) {
        this.teacherCourseId = teacherCourseId;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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