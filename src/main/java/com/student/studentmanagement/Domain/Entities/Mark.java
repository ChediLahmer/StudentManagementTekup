package com.student.studentmanagement.Domain.Entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
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
}
