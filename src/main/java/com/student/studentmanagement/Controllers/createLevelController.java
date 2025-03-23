package com.student.studentmanagement.Controllers;

import com.student.studentmanagement.Application.Repositories.LevelsRepo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class createLevelController implements Initializable {
    @FXML
    private TextField levelNameInput;

    @FXML
    private Button saveBtn;

    @FXML
    private Button cancelBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        saveBtn.setOnAction(event -> handleSave());
        cancelBtn.setOnAction(event -> handleCancel());
    }
    private void handleSave() {
        LevelsRepo levelRepo = new LevelsRepo();
        levelRepo.createLevel(levelNameInput.getText());
        closeDialog();
    }

    private void handleCancel() {
        System.out.println("Cancelled");
        closeDialog();
    }


    private void closeDialog() {
        Stage stage = (Stage) levelNameInput.getScene().getWindow();
        stage.close();
    }
}
