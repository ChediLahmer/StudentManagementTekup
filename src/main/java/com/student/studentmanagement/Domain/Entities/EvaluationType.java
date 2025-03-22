package com.student.studentmanagement.Domain.Entities;

import com.student.studentmanagement.Domain.Enums.EvalType;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"subject_id", "evalType"})
})
public class EvaluationType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID evaluationId;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Enumerated(EnumType.STRING)  // Changed to STRING for better maintainability
    private EvalType evalType;

    @OneToMany(mappedBy = "evaluationType", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Mark> marks = new HashSet<>();

    public EvaluationType() {
    }

    public UUID getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(UUID evaluationId) {
        this.evaluationId = evaluationId;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public EvalType getEvalType() {
        return evalType;
    }

    public void setEvalType(EvalType evalType) {
        this.evalType = evalType;
    }

    public Set<Mark> getMarks() {
        return marks;
    }
}
