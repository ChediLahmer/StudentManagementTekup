drop table if exists Admin;
drop table if exists Course;
drop table if exists EndUser;
drop table if exists Enrollment;
drop table if exists EvaluationType;
drop table if exists Level;
drop table if exists level_subject;
drop table if exists Mark;
drop table if exists Student;
drop table if exists StudentAbsence;
drop table if exists Subject;
drop table if exists Teacher;
drop table if exists TeacherAbsence;
drop table if exists TeacherCourse;
create table Admin (
        role varchar(255),
        userId uniqueidentifier not null,
        primary key (userId)
    );
create table Course (
        courseId uniqueidentifier not null,
        courseName varchar(255),
        level_id uniqueidentifier not null,
        primary key (courseId)
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
create table Enrollment (
        enrollmentId uniqueidentifier not null,
        endDate date,
        startDate date,
        course_id uniqueidentifier not null,
        student_id uniqueidentifier not null,
        primary key (enrollmentId)
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
        enrollmentNumber varchar(255),
        userId uniqueidentifier not null,
        primary key (userId)
    );
create table StudentAbsence (
        absenceId uniqueidentifier not null,
        endDateTime datetime2(6),
        startDateTime datetime2(6),
        course_id uniqueidentifier not null,
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
        course_id uniqueidentifier not null,
        teacher_id uniqueidentifier not null,
        primary key (absenceId)
    );
create table TeacherCourse (
        teacherCourseId uniqueidentifier not null,
        endDate date,
        startDate date,
        course_id uniqueidentifier not null,
        teacher_id uniqueidentifier not null,
        primary key (teacherCourseId)
    );
create unique nonclustered index UK6h58yemmenj346i1u6e61lp8f 
       on Enrollment (student_id, course_id, startDate, endDate) where startDate is not null and endDate is not null;
create unique nonclustered index UKi6a65utp2ty8mfdf56s8c9he5 
       on EvaluationType (subject_id, evalType) where evalType is not null;
alter table Mark 
       add constraint UKksplrmdkkbo72voeuikvpbs99 unique (eval_id, student_id);
create unique nonclustered index UK38idxmleyputl2mx8v5n6csiv 
       on StudentAbsence (student_id, course_id, startDateTime, endDateTime) where startDateTime is not null and endDateTime is not null;
create unique nonclustered index UK_8sy7v0sgi8aq6h2rt1pjyswrd 
       on Teacher (subject_id) where subject_id is not null;
create unique nonclustered index UKef2fewmhp6ro2eex4rpauscpk 
       on TeacherAbsence (teacher_id, course_id, startDateTime, endDateTime) where startDateTime is not null and endDateTime is not null;
create unique nonclustered index UKipt4ulwoq22y2w5161e1di4br 
       on TeacherCourse (teacher_id, course_id, startDate, endDate) where startDate is not null and endDate is not null;
alter table Admin 
       add constraint FKrvpw49v1gv04f0c7krsqeprnk 
       foreign key (userId) 
       references EndUser;
alter table Course 
       add constraint FKh38cc75ivx0bay0sea8d38ydb 
       foreign key (level_id) 
       references Level;
alter table Enrollment 
       add constraint FKh9495fmmc7x1heiww4cus16kc 
       foreign key (course_id) 
       references Course;
alter table Enrollment 
       add constraint FKosl3nkqhbp2hx32nn652hlun2 
       foreign key (student_id) 
       references Student;
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
       add constraint FKmao6qqqxbsa8p2b667ri66ttp 
       foreign key (userId) 
       references EndUser;
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
       add constraint FKm2k2trigxg8nxxpa1qmn9q3ih 
       foreign key (userId) 
       references EndUser;
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
