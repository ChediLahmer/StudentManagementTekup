package com.student.studentmanagement.Infrastructure;


import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.Metadata;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

import java.io.File;
import java.util.EnumSet;

public class SchemaExportUtil {

    public static void generateDDL(String outputFilePath) {
        // Create service registry
        var serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        try {
            // Create metadata sources and metadata
            MetadataSources metadataSources = new MetadataSources(serviceRegistry);

            // Add all entity classes from your package
            // This will scan your entity classes automatically
            metadataSources.addPackage("com.student.studentmanagement.Domain.Entities");
            metadataSources.addAnnotatedClass(com.student.studentmanagement.Domain.Entities.User.class);
            metadataSources.addAnnotatedClass(com.student.studentmanagement.Domain.Entities.Student.class);
            metadataSources.addAnnotatedClass(com.student.studentmanagement.Domain.Entities.Subject.class);
            metadataSources.addAnnotatedClass(com.student.studentmanagement.Domain.Entities.Mark.class);
            metadataSources.addAnnotatedClass(com.student.studentmanagement.Domain.Entities.Teacher.class);
            metadataSources.addAnnotatedClass(com.student.studentmanagement.Domain.Entities.TeacherAbsence.class);
            metadataSources.addAnnotatedClass(com.student.studentmanagement.Domain.Entities.TeacherCourse.class);
            metadataSources.addAnnotatedClass(com.student.studentmanagement.Domain.Entities.EvaluationType.class);
            metadataSources.addAnnotatedClass(com.student.studentmanagement.Domain.Entities.StudentAbsence.class);
            metadataSources.addAnnotatedClass(com.student.studentmanagement.Domain.Entities.Admin.class);
            metadataSources.addAnnotatedClass(com.student.studentmanagement.Domain.Entities.Course.class);
            metadataSources.addAnnotatedClass(com.student.studentmanagement.Domain.Entities.Enrollement.class);
            metadataSources.addAnnotatedClass(com.student.studentmanagement.Domain.Entities.Level.class);

            Metadata metadata = metadataSources.buildMetadata();

            // Configure the schema export
            SchemaExport schemaExport = new SchemaExport();
            schemaExport.setOutputFile(outputFilePath);
            schemaExport.setDelimiter(";");
            schemaExport.setFormat(true);

            // Generate the schema creation script
            schemaExport.create(EnumSet.of(TargetType.SCRIPT), metadata);

            System.out.println("✅ Schema script generated successfully at: " + outputFilePath);
        } catch (Exception e) {
            System.err.println("❌ Error generating schema script: " + e.getMessage());
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
        }
    }
}
