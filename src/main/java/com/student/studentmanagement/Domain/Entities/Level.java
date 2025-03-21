package com.student.studentmanagement.Domain.Entities;

import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;
@Entity
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID levelId;
    private String levelName;
    @OneToMany(mappedBy = "level" , cascade = CascadeType.ALL , orphanRemoval = true)
    private Set<Course> courses;
}
