package com.student.studentmanagement.Domain.Entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID levelId;

    private String levelName;

    @OneToMany(mappedBy = "level", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Course> courses = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "level_subject",
            joinColumns = @JoinColumn(name = "level_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> subjects = new HashSet<>();

    public Level() {
    }

    public Level(String name) {
        this.levelName = name;
    }

    // Getters and setters
    public UUID getLevelId() {
        return levelId;
    }

    public void setLevelId(UUID levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void addCourse(Course course) {
        courses.add(course);
        course.setLevel(this);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
        course.setLevel(null);
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
        subject.getLevels().add(this);
    }

    public void removeSubject(Subject subject) {
        subjects.remove(subject);
        subject.getLevels().remove(this);
    }
}
