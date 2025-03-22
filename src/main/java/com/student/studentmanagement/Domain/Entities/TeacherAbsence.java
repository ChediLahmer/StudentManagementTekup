package com.student.studentmanagement.Domain.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"teacher_id", "course_id", "startDateTime", "endDateTime"})
})
public class TeacherAbsence {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID absenceId;  // Changed from enrollmentId for clarity

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    // Default constructor needed by JPA
    public TeacherAbsence() {
    }

    // Getters and setters
    public UUID getAbsenceId() {
        return absenceId;
    }

    public void setAbsenceId(UUID absenceId) {
        this.absenceId = absenceId;
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
