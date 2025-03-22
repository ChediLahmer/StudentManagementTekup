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
import javafx.stage.Stage;
import org.flywaydb.core.Flyway;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        LevelsRepo repo = new LevelsRepo();
        List<Level> levels = repo.getAll();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();

        // Get the controller and set levels
        HelloController controller = fxmlLoader.getController();
        controller.setLevels(levels);

        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    private void executeMigrationScript(String scriptPath) {
        try {
            // Before migrating, filter out problematic statements from the initial migration
            String sql = new String(Files.readAllBytes(Paths.get(scriptPath)));
            String filteredSql = filterInitialMigrationScript(sql);
            Files.write(Paths.get(scriptPath), filteredSql.getBytes());

            // Get the absolute path of the migrations directory
            String migrationsPath = new File("src/main/resources/db/migration").getAbsolutePath();

            // Now use Flyway to apply the migration with classpath location
            Flyway flyway = Flyway.configure()
                    .dataSource(
                            "jdbc:sqlserver://localhost:1433;databaseName=StudentManagement;encrypt=true;trustServerCertificate=true",
                            "chedyLocal",
                            "0000"
                    )
                    .locations("filesystem:" + migrationsPath)
                    .load();

            // Execute the migration
            flyway.migrate();
            System.out.println("✅ Flyway migration executed successfully");
        } catch (Exception e) {
            System.err.println("❌ Error executing Flyway migration: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Helper method to filter out problematic statements from the initial migration
    private String filterInitialMigrationScript(String sql) {
        StringBuilder filtered = new StringBuilder();

        // Split by semicolon and process each statement
        for (String statement : sql.split(";")) {
            statement = statement.trim();

            // Skip statements that drop constraints or alter tables for non-existent objects
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