
    alter table Admin 
       drop constraint if exists FKjoav33p64suikub3369fpajy4;

    alter table Course 
       drop constraint if exists FKh38cc75ivx0bay0sea8d38ydb;

    alter table Enrollement 
       drop constraint if exists FKb10n8cy3h5epn7htm94kd2qym;

    alter table Enrollement 
       drop constraint if exists FKklqfhh2lwkr0cqvo2dfbpiyv0;

    alter table EvaluationType 
       drop constraint if exists FKd61thnps79ijhy0q24njlmktb;

    alter table EvaluationType_Mark 
       drop constraint if exists FKdgvkwbb8k4tk11okuvckuw733;

    alter table EvaluationType_Mark 
       drop constraint if exists FKjl3nlq7yula5gwud9fiiee0cv;

    alter table Level 
       drop constraint if exists FK23q53g43yhds33snunhy3adf5;

    alter table Mark 
       drop constraint if exists FKawn59vr3spiwerh5p667sia2u;

    alter table Mark 
       drop constraint if exists FKm4o7m5fgtpve3u4cit85sch1n;

    alter table Student 
       drop constraint if exists FKr73v1vjwwpravyp3wp48onosw;

    alter table StudentAbsence 
       drop constraint if exists FKp5wl7xhwk2mj7o4f50w0hpvjo;

    alter table StudentAbsence 
       drop constraint if exists FKau0bqaboimnnhrccdg1nnj7p5;

    alter table Teacher 
       drop constraint if exists FK6e33amanj82xu1aebk6jwb1sg;

    alter table Teacher 
       drop constraint if exists FK8s4q95fn9avwq7t029oolp2gb;

    alter table TeacherAbsence 
       drop constraint if exists FK6bkfs2fj7oqmtbd145mp684lj;

    alter table TeacherAbsence 
       drop constraint if exists FKjuqe108orvvuajttlwthjd6hc;

    alter table TeacherCourse 
       drop constraint if exists FKsfe9fu1ltmgvbpu6arpu0c7dy;

    alter table TeacherCourse 
       drop constraint if exists FKg78wfr8cvqwoksyq9gtk8xqd4;

    drop table if exists Admin;

    drop table if exists Course;

    drop table if exists Enrollement;

    drop table if exists EvaluationType;

    drop table if exists EvaluationType_Mark;

    drop table if exists Level;

    drop table if exists Mark;

    drop table if exists Student;

    drop table if exists StudentAbsence;

    drop table if exists Subject;

    drop table if exists Teacher;

    drop table if exists TeacherAbsence;

    drop table if exists TeacherCourse;

    drop table if exists User;

    create table Admin (
        Role varchar(255),
        userId uniqueidentifier not null,
        primary key (userId)
    );

    create table Course (
        courseId uniqueidentifier not null,
        courseName varchar(255),
        level_id uniqueidentifier not null,
        primary key (courseId)
    );

    create table Enrollement (
        enrollmentId uniqueidentifier not null,
        endDate date,
        startDate date,
        course_id uniqueidentifier not null,
        student_id uniqueidentifier not null,
        primary key (enrollmentId)
    );

    create table EvaluationType (
        evaluationId uniqueidentifier not null,
        evalType smallint check (evalType between 0 and 2),
        subject_id uniqueidentifier not null,
        primary key (evaluationId)
    );

    create table EvaluationType_Mark (
        EvaluationType_evaluationId uniqueidentifier not null,
        marks_markId uniqueidentifier not null,
        primary key (EvaluationType_evaluationId, marks_markId)
    );

    create table Level (
        levelId uniqueidentifier not null,
        levelName varchar(255),
        subject_id uniqueidentifier,
        primary key (levelId)
    );

    create table Mark (
        markId uniqueidentifier not null,
        score float(24) not null,
        eval_id uniqueidentifier not null,
        student_id uniqueidentifier not null,
        primary key (markId)
    );

    create table Student (
        enrollementNumber varchar(255),
        userId uniqueidentifier not null,
        primary key (userId)
    );

    create table StudentAbsence (
        enrollmentId uniqueidentifier not null,
        From datetime2(6),
        To datetime2(6),
        course_id uniqueidentifier not null,
        student_id uniqueidentifier not null,
        primary key (enrollmentId)
    );

    create table Subject (
        subjectId uniqueidentifier not null,
        subjectName varchar(255),
        primary key (subjectId)
    );

    create table Teacher (
        Grade varchar(255),
        userId uniqueidentifier not null,
        subject_id uniqueidentifier,
        primary key (userId)
    );

    create table TeacherAbsence (
        enrollmentId uniqueidentifier not null,
        From datetime2(6),
        To datetime2(6),
        course_id uniqueidentifier not null,
        teacher_id uniqueidentifier not null,
        primary key (enrollmentId)
    );

    create table TeacherCourse (
        enrollmentId uniqueidentifier not null,
        endDate date,
        startDate date,
        course_id uniqueidentifier not null,
        teacher_id uniqueidentifier not null,
        primary key (enrollmentId)
    );

    create table User (
        user_type varchar(31) not null,
        userId uniqueidentifier not null,
        emailAddress varchar(255),
        lastName varchar(255),
        name varchar(255),
        password varchar(255),
        userType smallint check (userType between 0 and 2),
        primary key (userId)
    );

    create unique nonclustered index UKsmeepxrdn1p85ba9lequ8ad9k 
       on Enrollement (student_id, startDate, endDate) where startDate is not null and endDate is not null;

    create unique nonclustered index UKi6a65utp2ty8mfdf56s8c9he5 
       on EvaluationType (subject_id, evalType) where evalType is not null;

    create unique nonclustered index UK9xjm8knsftrbtpbxihua8vjl2 
       on StudentAbsence (student_id, From, To) where From is not null and To is not null;

    create unique nonclustered index UK_8sy7v0sgi8aq6h2rt1pjyswrd 
       on Teacher (subject_id) where subject_id is not null;

    alter table Admin 
       add constraint FKjoav33p64suikub3369fpajy4 
       foreign key (userId) 
       references User;

    alter table Course 
       add constraint FKh38cc75ivx0bay0sea8d38ydb 
       foreign key (level_id) 
       references Level;

    alter table Enrollement 
       add constraint FKb10n8cy3h5epn7htm94kd2qym 
       foreign key (course_id) 
       references Course;

    alter table Enrollement 
       add constraint FKklqfhh2lwkr0cqvo2dfbpiyv0 
       foreign key (student_id) 
       references Student;

    alter table EvaluationType 
       add constraint FKd61thnps79ijhy0q24njlmktb 
       foreign key (subject_id) 
       references Subject;

    alter table EvaluationType_Mark 
       add constraint FKdgvkwbb8k4tk11okuvckuw733 
       foreign key (marks_markId) 
       references Mark;

    alter table EvaluationType_Mark 
       add constraint FKjl3nlq7yula5gwud9fiiee0cv 
       foreign key (EvaluationType_evaluationId) 
       references EvaluationType;

    alter table Level 
       add constraint FK23q53g43yhds33snunhy3adf5 
       foreign key (subject_id) 
       references Subject;

    alter table Mark 
       add constraint FKawn59vr3spiwerh5p667sia2u 
       foreign key (eval_id) 
       references EvaluationType;

    alter table Mark 
       add constraint FKm4o7m5fgtpve3u4cit85sch1n 
       foreign key (student_id) 
       references Student;

    alter table Student 
       add constraint FKr73v1vjwwpravyp3wp48onosw 
       foreign key (userId) 
       references User;

    alter table StudentAbsence 
       add constraint FKp5wl7xhwk2mj7o4f50w0hpvjo 
       foreign key (course_id) 
       references Course;

    alter table StudentAbsence 
       add constraint FKau0bqaboimnnhrccdg1nnj7p5 
       foreign key (student_id) 
       references Student;

    alter table Teacher 
       add constraint FK6e33amanj82xu1aebk6jwb1sg 
       foreign key (subject_id) 
       references Subject;

    alter table Teacher 
       add constraint FK8s4q95fn9avwq7t029oolp2gb 
       foreign key (userId) 
       references User;

    alter table TeacherAbsence 
       add constraint FK6bkfs2fj7oqmtbd145mp684lj 
       foreign key (course_id) 
       references Course;

    alter table TeacherAbsence 
       add constraint FKjuqe108orvvuajttlwthjd6hc 
       foreign key (teacher_id) 
       references Teacher;

    alter table TeacherCourse 
       add constraint FKsfe9fu1ltmgvbpu6arpu0c7dy 
       foreign key (course_id) 
       references Course;

    alter table TeacherCourse 
       add constraint FKg78wfr8cvqwoksyq9gtk8xqd4 
       foreign key (teacher_id) 
       references Teacher;
