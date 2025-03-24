package com.student.studentmanagement.Application.Repositories;

import com.student.studentmanagement.Domain.Entities.Level;
import com.student.studentmanagement.Domain.Entities.Subject;

import java.util.List;

public class LevelsRepo extends BaseRepository<Level> {

    public LevelsRepo() {
        super(Level.class);
    }

    public List<Level> getAll(){
         return super.getAll();
    }
    public void createLevel(String levelName){
        if (levelName == null || levelName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty.");
        }
        super.save(new Level(levelName));
    }
    public void addSubject(Subject subject, Level level) {
        executeInTransaction(session -> {
            Level managedLevel = session.merge(level);
            Subject managedSubject = session.merge(subject);

            managedLevel.addSubject(managedSubject);
            session.flush();
            return null;
        });
    }
    public void updateLevel(Level level){
        if(level == null){
            throw new IllegalArgumentException("Level cannot be null");
        }
        super.update(level);
    }
    public void deleteLevel(Level level){
        if(level == null){
            throw new IllegalArgumentException("Level cannot be null");
        }
        super.delete(level);
    }
}
