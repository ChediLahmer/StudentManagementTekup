package com.student.studentmanagement.Infrastructure;


public class DbContextFactory {

    private static IDbContext dbContext;

    public static synchronized IDbContext getDbContext() {
        if (dbContext == null) {
            dbContext = ApplicationDbContext.getInstance();
        }
        return dbContext;
    }

    // For testing: allows injecting a mock implementation
    public static void setDbContext(IDbContext mockDbContext) {
        dbContext = mockDbContext;
    }
}
