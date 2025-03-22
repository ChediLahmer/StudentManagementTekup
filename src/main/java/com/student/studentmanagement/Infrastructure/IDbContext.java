package com.student.studentmanagement.Infrastructure;

import org.hibernate.Session;

public interface IDbContext {
    Session openSession();
    void closeSession(Session session);
    void shutdown();
}