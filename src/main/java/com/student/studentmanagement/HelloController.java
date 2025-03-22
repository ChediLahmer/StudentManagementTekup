package com.student.studentmanagement;

import com.student.studentmanagement.Domain.Entities.Level;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.List;

public class HelloController {

    @FXML
    private Label welcomeText;
    @FXML
    private ListView<String> listViewLevels;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void setLevels(List<Level> levels) {
        if (listViewLevels != null) {
            for (Level level : levels) {
                listViewLevels.getItems().add(level.getLevelName());  // Assuming Level has getLevelName()
            }
        } else {
            System.out.println("ListView is null.");
        }
    }
}
