package com.student.studentmanagement.Domain.Entities;

import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID subjectId;
    private String subjectName;
    @OneToMany(mappedBy = "subject" , cascade = CascadeType.ALL , orphanRemoval = true)
    private Set<Level> levels;
    @OneToOne(mappedBy = "subject" , cascade = CascadeType.ALL , orphanRemoval = true)
    public Teacher teacher;
}
