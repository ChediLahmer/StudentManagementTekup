package com.student.studentmanagement.Domain.Entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID courseId;
    private String courseName;
    @ManyToOne
    @JoinColumn(name = "level_id" , nullable = false)
    public Level level;
}
