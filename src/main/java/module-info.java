module com.student.studentmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    opens com.student.studentmanagement to javafx.fxml;
    exports com.student.studentmanagement;
}