package com.student.studentmanagement.Domain.Entities;

import com.student.studentmanagement.Domain.Enums.EvalType;
import jakarta.persistence.*;

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
    @Enumerated(EnumType.ORDINAL)
    private EvalType evalType;
    @ManyToMany
    private Set<Mark> marks;
}
