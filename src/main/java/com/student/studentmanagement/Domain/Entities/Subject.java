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
    @ManyToMany(mappedBy = "subjects" ,  cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Level> levels = new HashSet<>();

    @OneToOne(mappedBy = "subject")
    private Teacher teacher;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EvaluationType> evaluationTypes = new HashSet<>();

    public Subject(Set<EvaluationType> evaluationTypes, Set<Level> levels, String subjectName) {
        this.evaluationTypes = evaluationTypes;
        this.levels = levels;
        this.subjectName = subjectName;
    }
    public Subject( Set<Level> levels, String subjectName) {
        this.levels = levels;
        this.subjectName = subjectName;
    }
    public Subject(String subjectName) {
        this.subjectName = subjectName;
    }
    public Subject( Set<Level> levels,Teacher teacher ,String subjectName) {
        this.levels = levels;
        this.subjectName = subjectName;
        this.teacher = teacher;
    }
    public Subject(Set<EvaluationType> evaluationTypes, Teacher teacher, Set<Level> levels, String subjectName) {
        this.evaluationTypes = evaluationTypes;
        this.teacher = teacher;
        this.levels = levels;
        this.subjectName = subjectName;
    }


    public Subject() {
    }

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