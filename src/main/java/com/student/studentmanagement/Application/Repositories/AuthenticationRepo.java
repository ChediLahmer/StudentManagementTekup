package com.student.studentmanagement.Application.Repositories;

import com.student.studentmanagement.Domain.Entities.EndUser;
import com.student.studentmanagement.Domain.Enums.UserType;
import com.student.studentmanagement.Domain.Helpers.PasswordUtil;
import org.hibernate.Session;

public class AuthenticationRepo extends BaseRepository<EndUser> {
    public AuthenticationRepo() {
        super(EndUser.class);
    }

    public EndUser authenticateUser(String email, String password) {
        String hashedPassword = PasswordUtil.hashPassword(password);
        return executeInTransaction(session -> {
            EndUser user = session.createQuery("FROM EndUser WHERE emailAddress = :email and password = :hashedPassword", EndUser.class)
                    .setParameter("email", email)
                    .setParameter("hashedPassword", hashedPassword)
                    .uniqueResult();

            return user;
        });
    }
}