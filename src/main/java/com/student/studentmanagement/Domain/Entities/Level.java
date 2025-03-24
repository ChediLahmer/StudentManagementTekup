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
    @OneToMany(mappedBy = "level")
    private Set<Student> students;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE} , fetch = FetchType.EAGER)
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
    public Set<Student> getStudents(){
        return this.students;
    }
    public void setStudents(Set<Student> students){
        this.students = students;
    }
    public void removeStudent(Student student){
        this.students.remove(student);
    }
    public void adddStudent(Student student){
        this.students.add(student);
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



    public Set<Subject> getSubjects() {
        return subjects;
    }
    public void setSubjects(Set<Subject> subjects){
        this.setSubjects(subjects);
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
