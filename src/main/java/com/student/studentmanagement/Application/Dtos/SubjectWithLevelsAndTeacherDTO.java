package com.student.studentmanagement.Application.Dtos;

import java.util.UUID;

public class SubjectWithLevelsAndTeacherDTO {
    private UUID subjectId;
    private String subjectName;
    private String levelNames; // Comma-separated
    private String teacherName;
    public SubjectWithLevelsAndTeacherDTO() {}
    public SubjectWithLevelsAndTeacherDTO(
            UUID subjectId,
            String subjectName,
            String levelNames,
            String teacherName
    ) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.levelNames = levelNames;
        this.teacherName = teacherName;
    }

    public UUID getSubjectId() { return subjectId; }
    public String getSubjectName() { return subjectName; }
    public String getLevelNames() { return levelNames; }
    public String getTeacherName() { return teacherName; }
}