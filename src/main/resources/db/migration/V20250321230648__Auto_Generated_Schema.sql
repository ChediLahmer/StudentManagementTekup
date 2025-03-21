drop table if exists Admin;
drop table if exists Course;
drop table if exists EndUser;
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
create table EndUser (
        user_type varchar(31) not null,
        userId uniqueidentifier not null,
        emailAddress varchar(255),
        lastName varchar(255),
        name varchar(255),
        password varchar(255),
        userType smallint check (userType between 0 and 2),
        primary key (userId)
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
        endDateTime datetime2(6),
        startDateTime datetime2(6),
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
        endDateTime datetime2(6),
        startDateTime datetime2(6),
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
create unique nonclustered index UKsmeepxrdn1p85ba9lequ8ad9k 
       on Enrollement (student_id, startDate, endDate) where startDate is not null and endDate is not null;
create unique nonclustered index UKi6a65utp2ty8mfdf56s8c9he5 
       on EvaluationType (subject_id, evalType) where evalType is not null;
create unique nonclustered index UKbj0376rt9khqewrcsnv2h5kh9 
       on StudentAbsence (student_id, startDateTime, endDateTime) where startDateTime is not null and endDateTime is not null;
create unique nonclustered index UK_8sy7v0sgi8aq6h2rt1pjyswrd 
       on Teacher (subject_id) where subject_id is not null;
alter table Admin 
       add constraint FKrvpw49v1gv04f0c7krsqeprnk 
       foreign key (userId) 
       references EndUser;
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
