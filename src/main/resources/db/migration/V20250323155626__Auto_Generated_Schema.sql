drop table if exists Admin;
drop table if exists EndUser;
drop table if exists EvaluationType;
drop table if exists Level;
drop table if exists level_subject;
drop table if exists Mark;
drop table if exists Student;
drop table if exists StudentAbsence;
drop table if exists Subject;
drop table if exists Teacher;
drop table if exists TeacherAbsence;
create table Admin (
        role varchar(255),
        userId uniqueidentifier not null,
        primary key (userId)
    );
create table EndUser (
        userId uniqueidentifier not null,
        emailAddress varchar(255),
        lastName varchar(255),
        name varchar(255),
        password varchar(255),
        userType varchar(255) check (userType in ('ADMIN','TEACHER','STUDENT')),
        primary key (userId)
    );
create table EvaluationType (
        evaluationId uniqueidentifier not null,
        evalType varchar(255) check (evalType in ('EXAM','DS','PROJECT')),
        subject_id uniqueidentifier not null,
        primary key (evaluationId)
    );
create table Level (
        levelId uniqueidentifier not null,
        levelName varchar(255),
        primary key (levelId)
    );
create table level_subject (
        level_id uniqueidentifier not null,
        subject_id uniqueidentifier not null,
        primary key (level_id, subject_id)
    );
create table Mark (
        markId uniqueidentifier not null,
        score float(24) not null,
        eval_id uniqueidentifier not null,
        student_id uniqueidentifier not null,
        primary key (markId)
    );
create table Student (
        endDate date,
        enrollmentNumber varchar(255),
        startDate date,
        userId uniqueidentifier not null,
        level_id uniqueidentifier,
        primary key (userId)
    );
create table StudentAbsence (
        absenceId uniqueidentifier not null,
        endDateTime datetime2(6),
        startDateTime datetime2(6),
        student_id uniqueidentifier not null,
        primary key (absenceId)
    );
create table Subject (
        subjectId uniqueidentifier not null,
        subjectName varchar(255),
        primary key (subjectId)
    );
create table Teacher (
        grade varchar(255),
        userId uniqueidentifier not null,
        subject_id uniqueidentifier,
        primary key (userId)
    );
create table TeacherAbsence (
        absenceId uniqueidentifier not null,
        endDateTime datetime2(6),
        startDateTime datetime2(6),
        teacher_id uniqueidentifier not null,
        primary key (absenceId)
    );
create unique nonclustered index UKi6a65utp2ty8mfdf56s8c9he5 
       on EvaluationType (subject_id, evalType) where evalType is not null;
alter table Mark 
       add constraint UKksplrmdkkbo72voeuikvpbs99 unique (eval_id, student_id);
create unique nonclustered index UKbj0376rt9khqewrcsnv2h5kh9 
       on StudentAbsence (student_id, startDateTime, endDateTime) where startDateTime is not null and endDateTime is not null;
create unique nonclustered index UK_8sy7v0sgi8aq6h2rt1pjyswrd 
       on Teacher (subject_id) where subject_id is not null;
create unique nonclustered index UKjwqgrle7qc3dxxx5juuls2edb 
       on TeacherAbsence (teacher_id, startDateTime, endDateTime) where startDateTime is not null and endDateTime is not null;
alter table Admin 
       add constraint FKrvpw49v1gv04f0c7krsqeprnk 
       foreign key (userId) 
       references EndUser;
alter table EvaluationType 
       add constraint FKd61thnps79ijhy0q24njlmktb 
       foreign key (subject_id) 
       references Subject;
alter table level_subject 
       add constraint FKpepfp8r5gjl0oievq0vhseo31 
       foreign key (subject_id) 
       references Subject;
alter table level_subject 
       add constraint FK5q1i0mcor0x95s9rltcclfnq6 
       foreign key (level_id) 
       references Level;
alter table Mark 
       add constraint FKawn59vr3spiwerh5p667sia2u 
       foreign key (eval_id) 
       references EvaluationType;
alter table Mark 
       add constraint FKm4o7m5fgtpve3u4cit85sch1n 
       foreign key (student_id) 
       references Student;
alter table Student 
       add constraint FKed02l8h0lb6bchf8wl9k67oxm 
       foreign key (level_id) 
       references Level;
alter table Student 
       add constraint FKmao6qqqxbsa8p2b667ri66ttp 
       foreign key (userId) 
       references EndUser;
alter table StudentAbsence 
       add constraint FKau0bqaboimnnhrccdg1nnj7p5 
       foreign key (student_id) 
       references Student;
alter table Teacher 
       add constraint FK6e33amanj82xu1aebk6jwb1sg 
       foreign key (subject_id) 
       references Subject;
alter table Teacher 
       add constraint FKm2k2trigxg8nxxpa1qmn9q3ih 
       foreign key (userId) 
       references EndUser;
alter table TeacherAbsence 
       add constraint FKjuqe108orvvuajttlwthjd6hc 
       foreign key (teacher_id) 
       references Teacher;
