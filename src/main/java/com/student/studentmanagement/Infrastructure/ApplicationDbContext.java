package com.student.studentmanagement.Infrastructure;
import com.student.studentmanagement.Domain.Entities.EndUser;
import com.student.studentmanagement.Domain.Entities.Enrollment;
import com.student.studentmanagement.Domain.Entities.Student;
import com.student.studentmanagement.Domain.Entities.Teacher;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
public class ApplicationDbContext {
    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            configuration.addPackage("com.student.studentmanagement.Domain.Entities");
            configuration.addAnnotatedClass(EndUser.class);
            configuration.addAnnotatedClass(com.student.studentmanagement.Domain.Entities.Student.class);
            configuration.addAnnotatedClass(com.student.studentmanagement.Domain.Entities.Teacher.class);
            configuration.addAnnotatedClass(com.student.studentmanagement.Domain.Entities.Admin.class);
            configuration.addAnnotatedClass(com.student.studentmanagement.Domain.Entities.Subject.class);
            configuration.addAnnotatedClass(com.student.studentmanagement.Domain.Entities.Mark.class);
            configuration.addAnnotatedClass(com.student.studentmanagement.Domain.Entities.TeacherAbsence.class);
            configuration.addAnnotatedClass(com.student.studentmanagement.Domain.Entities.TeacherCourse.class);
            configuration.addAnnotatedClass(com.student.studentmanagement.Domain.Entities.EvaluationType.class);
            configuration.addAnnotatedClass(com.student.studentmanagement.Domain.Entities.StudentAbsence.class);
            configuration.addAnnotatedClass(com.student.studentmanagement.Domain.Entities.Course.class);
            configuration.addAnnotatedClass(Enrollment.class);
            configuration.addAnnotatedClass(com.student.studentmanagement.Domain.Entities.Level.class);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            System.err.println("❌ Error initializing Hibernate SessionFactory: " + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    // Open a new database session
    public static Session getSession() {
        return sessionFactory.openSession();
    }

    // Close the SessionFactory when application exits
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
