package com.student.studentmanagement.Application.Repositories;

import com.student.studentmanagement.Domain.Entities.EvaluationType;
import com.student.studentmanagement.Domain.Entities.Subject;
import com.student.studentmanagement.Domain.Enums.EvalType;

public class EvaluationTypesRepo extends BaseRepository<EvaluationType>{
    public EvaluationTypesRepo(){
        super(EvaluationType.class);
    }

    public void createEvalType(Subject subject , EvalType evalType){
        if(subject == null){
            throw new IllegalArgumentException("subject cannot be null.");
        }
        if(evalType == null){
            throw new IllegalArgumentException("evalType cannot be null.");
        }
        super.save(new EvaluationType(subject ,evalType));
    }
}
