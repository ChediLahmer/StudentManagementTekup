package com.student.studentmanagement.Application.Repositories;
import com.student.studentmanagement.Infrastructure.DbContextFactory;
import com.student.studentmanagement.Infrastructure.IDbContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.function.Function;

public abstract class BaseRepository<T> {

    protected final IDbContext dbContext;
    protected final Class<T> entityClass;

    protected BaseRepository(Class<T> entityClass) {
        this.dbContext = DbContextFactory.getDbContext();
        this.entityClass = entityClass;
    }

    protected <R> R executeInTransaction(Function<Session, R> operation) {
        Session session = dbContext.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            R result = operation.apply(session);
            transaction.commit();
            return result;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            dbContext.closeSession(session);
        }
    }

    public T getById(Object id) {
        return executeInTransaction(session -> session.get(entityClass, id));
    }

    public List<T> getAll() {
        return executeInTransaction(session ->
                session.createQuery("from " + entityClass.getSimpleName(), entityClass).list());
    }

    public void save(T entity) {
        executeInTransaction(session -> {
            session.persist(entity);
            return null;
        });
    }

    public void update(T entity) {
        executeInTransaction(session -> {
            session.merge(entity);
            return null;
        });
    }

    public void delete(T entity) {
        executeInTransaction(session -> {
            session.remove(entity);
            return null;
        });
    }
}