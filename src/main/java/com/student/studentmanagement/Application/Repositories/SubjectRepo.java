package com.student.studentmanagement.Application.Repositories;

import com.student.studentmanagement.Application.Dtos.SubjectWithLevelsAndTeacherDTO;
import com.student.studentmanagement.Domain.Entities.EvaluationType;
import com.student.studentmanagement.Domain.Entities.Level;
import com.student.studentmanagement.Domain.Entities.Subject;
import com.student.studentmanagement.Domain.Entities.Teacher;
import com.student.studentmanagement.Domain.Enums.EvalType;
import org.hibernate.transform.Transformers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class SubjectRepo extends BaseRepository<Subject>{
    private EvaluationTypesRepo evaluationTypesRepo = new EvaluationTypesRepo();
    private LevelsRepo levelsRepo = new LevelsRepo();
    private TeachersRepo teachersRepo = new TeachersRepo();
    public SubjectRepo() {
        super(Subject.class);
    }
    public void createSubject(Set<EvalType> evaluationTypes, Teacher teacher, Set<Level> levels, String subjectName){
        if( evaluationTypes == null ||evaluationTypes.size() == 0){
            throw new IllegalArgumentException("evaluationTypes cannot be null or empty.");
        }
        if(levels == null || levels.size() ==0){
            throw new IllegalArgumentException("levels cannot be null or empty.");
        }
        if(subjectName == null || subjectName.trim().isEmpty()){
            throw new IllegalArgumentException("subjectName cannot be null or empty.");
        }
        Subject subject =  new Subject(subjectName);
        super.save(subject);
        for(Level level : levels){
            levelsRepo.addSubject(subject , level);
        }
        Subject savedSubject = super.save(subject);
        for(EvalType evalType : evaluationTypes){
            evaluationTypesRepo.createEvalType(savedSubject , evalType);
        }
        if(teacher != null) {
            teachersRepo.addSubject(teacher, subject);
        }
    }
    public List<SubjectWithLevelsAndTeacherDTO> getAllSubjectsWithLevelsAndTeachers() {
        return executeInTransaction(session -> {
            String sql = """
                    SELECT\s
                                s.subjectId AS subjectId,
                                s.subjectName AS subjectName,
                                STUFF(
                                    (SELECT ', ' + l.levelName
                                     FROM level l
                                     INNER JOIN level_subject ls ON l.levelId = ls.level_id
                                     WHERE ls.subject_id = s.subjectId
                                     FOR XML PATH('')
                                    ), 1, 2, '') AS levelNames,
                                CONCAT(e.name, ' ', e.lastName) AS teacherName
                            FROM\s
                                subject s
                            LEFT JOIN\s
                                teacher t ON s.subjectId = t.subject_id
                            LEFT JOIN\s
                                EndUser e ON t.userId = e.userId\s
                            GROUP BY\s
                                s.subjectId, s.subjectName, e.name, e.lastName
            """;

            List<Object[]> results = session.createNativeQuery(sql).getResultList();
            List<SubjectWithLevelsAndTeacherDTO> subjects = new ArrayList<>();

            for (Object[] row : results) {
                UUID subjectId = UUID.fromString((String) row[0]); // Convert String to UUID
                String subjectName = (String) row[1];
                String levelNames = (String) row[2];
                String teacherName = (String) row[3];

                subjects.add(new SubjectWithLevelsAndTeacherDTO(subjectId, subjectName,levelNames ,teacherName));
            }

            return subjects;
        });
    }
}
