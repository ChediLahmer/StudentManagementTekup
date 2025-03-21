package com.student.studentmanagement.Domain.Entities;
import com.student.studentmanagement.Domain.Enums.UserType;
import com.student.studentmanagement.Domain.Helpers.PasswordUtil;
import jakarta.persistence.*;
import java.util.UUID;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type")
public class EndUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;
    private String name;
    private String lastName;
    private String emailAddress;
    private String password;
    @Enumerated(EnumType.ORDINAL)
    private UserType userType;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public EndUser(){}

    public EndUser(UUID userId, String lastName, String name, String emailAddress, String password, UserType userType) {
        this.userId = userId;
        this.lastName = lastName;
        this.name = name;
        this.emailAddress = emailAddress;
        this.password = PasswordUtil.hashPassword(password);
        this.userType = userType;
    }
}
