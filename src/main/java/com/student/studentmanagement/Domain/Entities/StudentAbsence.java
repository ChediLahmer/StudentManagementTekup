package com.student.studentmanagement.Domain.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student_id", "course_id", "startDateTime", "endDateTime"})
})
public class StudentAbsence {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID absenceId;  // Changed from enrollmentId for clarity

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public StudentAbsence() {
    }

    public UUID getAbsenceId() {
        return absenceId;
    }

    public void setAbsenceId(UUID absenceId) {
        this.absenceId = absenceId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }
}