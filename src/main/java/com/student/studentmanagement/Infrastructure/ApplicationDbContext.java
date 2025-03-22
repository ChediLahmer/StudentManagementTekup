package com.student.studentmanagement.Infrastructure;

import com.student.studentmanagement.Domain.Entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ApplicationDbContext implements IDbContext {
    private static SessionFactory sessionFactory;
    private static ApplicationDbContext instance;

    // Private constructor for singleton pattern
    private ApplicationDbContext() {
        try {
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

            // Add all entity classes
            configuration.addAnnotatedClass(EndUser.class);
            configuration.addAnnotatedClass(Student.class);
            configuration.addAnnotatedClass(Teacher.class);
            configuration.addAnnotatedClass(Admin.class);
            configuration.addAnnotatedClass(Subject.class);
            configuration.addAnnotatedClass(Mark.class);
            configuration.addAnnotatedClass(TeacherAbsence.class);
            configuration.addAnnotatedClass(TeacherCourse.class);
            configuration.addAnnotatedClass(EvaluationType.class);
            configuration.addAnnotatedClass(StudentAbsence.class);
            configuration.addAnnotatedClass(Course.class);
            configuration.addAnnotatedClass(Enrollment.class);
            configuration.addAnnotatedClass(Level.class);

            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            System.err.println("❌ Error initializing Hibernate SessionFactory: " + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    // Singleton pattern implementation
    public static synchronized ApplicationDbContext getInstance() {
        if (instance == null) {
            instance = new ApplicationDbContext();
        }
        return instance;
    }

    @Override
    public Session openSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void closeSession(Session session) {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    @Override
    public void shutdown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
}