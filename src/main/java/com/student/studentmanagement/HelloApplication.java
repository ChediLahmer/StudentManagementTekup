package com.student.studentmanagement;

import com.student.studentmanagement.Application.Repositories.LevelsRepo;
import com.student.studentmanagement.Domain.Entities.Admin;
import com.student.studentmanagement.Domain.Entities.Level;
import com.student.studentmanagement.Domain.Entities.Student;
import com.student.studentmanagement.Domain.Entities.Teacher;
import com.student.studentmanagement.Domain.Enums.UserType;
import com.student.studentmanagement.Infrastructure.ApplicationDbContext;
import com.student.studentmanagement.Infrastructure.SchemaExportUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.flywaydb.core.Flyway;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javafx.scene.image.Image;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            Image icon = new Image(getClass().getResource("/Images/icon.jpg").toExternalForm());
            stage.getIcons().add(icon);
            stage.setTitle("student management system");
            stage.setResizable(false);
            stage.show();
            stage.setOnCloseRequest(event ->{
                event.consume();
                cancel(stage);
            });

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    void cancel(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to exit!");
        alert.setContentText("are you sure?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("You successfully exit");
            stage.close();
        }
    }
    private void executeMigrationScript(String scriptPath) {
        try {
            String sql = new String(Files.readAllBytes(Paths.get(scriptPath)));
            String filteredSql = filterInitialMigrationScript(sql);
            Files.write(Paths.get(scriptPath), filteredSql.getBytes());

            String migrationsPath = new File("src/main/resources/db/migration").getAbsolutePath();

            Flyway flyway = Flyway.configure()
                    .dataSource(
                            "jdbc:sqlserver://localhost:1433;databaseName=StudentManagement;encrypt=true;trustServerCertificate=true",
                            "chedyLocal",
                            "0000"
                    )
                    .locations("filesystem:" + migrationsPath)
                    .load();

            flyway.migrate();
            System.out.println("✅ Flyway migration executed successfully");
        } catch (Exception e) {
            System.err.println("❌ Error executing Flyway migration: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String filterInitialMigrationScript(String sql) {
        StringBuilder filtered = new StringBuilder();

        for (String statement : sql.split(";")) {
            statement = statement.trim();

            if (statement.isEmpty() ||
                    statement.toLowerCase().contains("drop constraint") ||
                    (statement.toLowerCase().contains("alter table") &&
                            statement.toLowerCase().contains("drop"))) {
                continue;
            }

            filtered.append(statement).append(";\n");
        }

        return filtered.toString();
    }

    public static void main(String[] args) {
        launch();
    }
}