package com.student.studentmanagement.Controllers;

import com.student.studentmanagement.Application.Repositories.AuthenticationRepo;
import com.student.studentmanagement.Application.Repositories.LevelsRepo;
import com.student.studentmanagement.Domain.Entities.EndUser;
import com.student.studentmanagement.Domain.Enums.UserType;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField emailField;
    @FXML
    private Button loginBtn;
    @FXML
    private Label warning;

    private Scene scene;
    private Parent root;
    private PreparedStatement pst;
    private ResultSet rs;
    AuthenticationRepo authenticationRepo = new AuthenticationRepo();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginBtn.setOnAction(new EventHandler<ActionEvent >() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Login(event); // Call the Login method
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @FXML
    void Login(ActionEvent event) throws IOException{
        EndUser user = authenticationRepo.authenticateUser(emailField.getText() , passwordField.getText());
        if(user == null){
            warning.setText("❌ Email or password is wrong !!");
        }
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        switch (user.getUserType()){
            case UserType.ADMIN -> {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/student/studentmanagement/Levels.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            default -> {
                Alert alert = new Alert(Alert.AlertType.NONE, "Not implemented yet", ButtonType.OK);
                alert.setTitle("ALERT!");
                alert.showAndWait();
            }
        }
    }
}
