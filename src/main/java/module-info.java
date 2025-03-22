module com.student.studentmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires org.hibernate.orm.ant;
    requires flyway.core;
    requires java.desktop;
    opens com.student.studentmanagement to javafx.fxml;
    opens com.student.studentmanagement.Domain.Entities to org.hibernate.orm.core;
    exports com.student.studentmanagement;
}