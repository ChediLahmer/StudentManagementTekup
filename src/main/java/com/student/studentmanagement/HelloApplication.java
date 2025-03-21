package com.student.studentmanagement;

import com.student.studentmanagement.Infrastructure.ApplicationDbContext;
import com.student.studentmanagement.Infrastructure.SchemaExportUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Generate migration scripts based on entity classes
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String resourcesPath = "src/main/resources";
        String migrationsDir = resourcesPath + "/db/migration";

        // Create migrations directory if it doesn't exist
        Files.createDirectories(Paths.get(migrationsDir));

        // Generate schema script with a versioned filename
        String scriptPath = migrationsDir + "/V" + timestamp + "__Auto_Generated_Schema.sql";
        SchemaExportUtil.generateDDL(scriptPath);

        // Execute the migration script using JDBC
        executeMigrationScript(scriptPath);

        // Continue with your application
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Session session = ApplicationDbContext.getSession();
        if(session != null){
            stage.setTitle("Connected Successfully");
        } else {
            stage.setTitle("Hello!");
        }

        stage.setScene(scene);
        stage.show();
    }

    private void executeMigrationScript(String scriptPath) {
        // You can use JDBC to execute the SQL script
        // Or you can still use Flyway programmatically:
        /*
        Flyway flyway = Flyway.configure()
            .dataSource(
                "jdbc:sqlserver://localhost:1433;databaseName=StudentManagement;encrypt=true;trustServerCertificate=true",
                "chedyLocal",
                "0000"
            )
            .load();

        flyway.migrate();
        */

        // For simplicity, you can use the ApplicationDbContext to get a connection and execute the script
        try (Session session = ApplicationDbContext.getSession()) {
            String sql = new String(Files.readAllBytes(Paths.get(scriptPath)));
            session.beginTransaction();
            for (String statement : sql.split(";")) {
                if (!statement.trim().isEmpty()) {
                    session.createNativeQuery(statement).executeUpdate();
                }
            }
            session.getTransaction().commit();
            System.out.println("✅ Migration script executed successfully");
        } catch (Exception e) {
            System.err.println("❌ Error executing migration script: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}