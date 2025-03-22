package com.student.studentmanagement.Domain.Entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID subjectId;

    private String subjectName;

    @ManyToMany(mappedBy = "subjects")
    private Set<Level> levels = new HashSet<>();

    @OneToOne(mappedBy = "subject")
    private Teacher teacher;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EvaluationType> evaluationTypes = new HashSet<>();

    // Default constructor needed by JPA
    public Subject() {
    }

    // Getters and setters
    public UUID getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(UUID subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Set<Level> getLevels() {
        return levels;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Set<EvaluationType> getEvaluationTypes() {
        return evaluationTypes;
    }
}