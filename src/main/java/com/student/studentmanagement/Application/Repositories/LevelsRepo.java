package com.student.studentmanagement.Application.Repositories;

import com.student.studentmanagement.Domain.Entities.Level;

import java.util.List;

public class LevelsRepo extends BaseRepository<Level> {

    public LevelsRepo() {
        super(Level.class);
    }

    public List<Level> getAll(){
         return super.getAll();
    }
}
