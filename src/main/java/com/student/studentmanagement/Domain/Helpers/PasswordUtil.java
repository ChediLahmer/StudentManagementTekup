package com.student.studentmanagement.Domain.Helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {
    private static final String CONSTANT_SALT = "Tekup";

    public static String hashPassword(String password) {
        try {
            String saltedPassword = password + CONSTANT_SALT;

            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] hashedBytes = digest.digest(saltedPassword.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (Exception e) {
            System.err.println("Algorithm not found " + e.getMessage());
            return password + CONSTANT_SALT;
        }
    }
}
