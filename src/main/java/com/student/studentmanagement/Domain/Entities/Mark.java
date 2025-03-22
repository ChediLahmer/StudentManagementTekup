package com.student.studentmanagement.Domain.Entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"eval_id", "student_id"})
})
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID markId;

    private float score;

    @ManyToOne
    @JoinColumn(name = "eval_id", nullable = false)
    private EvaluationType evaluationType;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    public Mark() {
    }

    // Getters and setters
    public UUID getMarkId() {
        return markId;
    }

    public void setMarkId(UUID markId) {
        this.markId = markId;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public EvaluationType getEvaluationType() {
        return evaluationType;
    }

    public void setEvaluationType(EvaluationType evaluationType) {
        this.evaluationType = evaluationType;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
