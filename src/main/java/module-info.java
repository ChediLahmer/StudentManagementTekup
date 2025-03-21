module com.student.studentmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;

    opens com.student.studentmanagement to javafx.fxml;
    exports com.student.studentmanagement;
}