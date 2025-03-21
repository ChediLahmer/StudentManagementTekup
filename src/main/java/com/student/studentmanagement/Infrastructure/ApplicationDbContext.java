package com.student.studentmanagement.Infrastructure;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
public class ApplicationDbContext {
    private static SessionFactory sessionFactory;

    static {
        try {
            // Load Hibernate configuration from hibernate.cfg.xml
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
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
