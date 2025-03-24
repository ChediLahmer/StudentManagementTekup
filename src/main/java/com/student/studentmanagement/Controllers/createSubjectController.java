package com.student.studentmanagement.Controllers;

import com.student.studentmanagement.Application.Repositories.LevelsRepo;
import com.student.studentmanagement.Application.Repositories.SubjectRepo;
import com.student.studentmanagement.Application.Repositories.TeachersRepo;
import com.student.studentmanagement.Domain.Entities.EvaluationType;
import com.student.studentmanagement.Domain.Entities.Level;
import com.student.studentmanagement.Domain.Entities.Teacher;
import com.student.studentmanagement.Domain.Enums.EvalType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class createSubjectController implements Initializable {

    @FXML
    private TextField subjectName;
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private MenuButton levelMenuButton;
    @FXML
    private MenuButton teacherMenuButton;
    @FXML
    private CheckBox exam;
    @FXML
    private CheckBox ds;
    @FXML
    private CheckBox project;

    private TeachersRepo teachersRepo = new TeachersRepo();
    private LevelsRepo levelsRepo = new LevelsRepo();

    private Teacher selectedTeacher = null;
    Set<Level> selectedLevels = new HashSet<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<Teacher> teachers = teachersRepo.getAll();
        List<Level> levels = levelsRepo.getAll();


        for (Teacher teacher : teachers) {
            MenuItem teacherItem = new MenuItem(teacher.getName() + " " + teacher.getLastName());
            teacherItem.setOnAction(e -> {
                selectedTeacher = teacher;
                teacherMenuButton.setText(teacher.getName() + " " + teacher.getLastName());  // Update MenuButton text
            });
            teacherMenuButton.getItems().add(teacherItem);
        }


        for (Level level : levels) {
            CheckMenuItem levelItem = new CheckMenuItem(level.getLevelName());
            levelItem.setOnAction(e -> updateSelectedLevels());
            levelMenuButton.getItems().add(levelItem);
        }

        saveBtn.setOnAction(new EventHandler<ActionEvent >() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Set<EvalType> evaluationTypes = new HashSet<>();
                    if(ds.isSelected()){
                        evaluationTypes.add(EvalType.DS);
                    }
                    if(exam.isSelected()){
                        evaluationTypes.add(EvalType.EXAM);
                    }
                    if (project.isSelected()) {
                        evaluationTypes.add(EvalType.PROJECT);
                    }
                    SubjectRepo subjectRepo = new SubjectRepo();
                    subjectRepo.createSubject(evaluationTypes , selectedTeacher ,selectedLevels , subjectName.getText());
                    Subject(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        cancelBtn.setOnAction(new EventHandler<ActionEvent >() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Subject(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void updateSelectedLevels() {
        selectedLevels.clear();
        for (MenuItem item : levelMenuButton.getItems()) {
            if (item instanceof CheckMenuItem) {
                CheckMenuItem checkItem = (CheckMenuItem) item;
                if (checkItem.isSelected()) {
                    Level level = findLevelByName(checkItem.getText());
                    selectedLevels.add(level);
                }
            }
        }
        StringBuilder selectedLevelsText = new StringBuilder();
        for (Level level : selectedLevels) {
            selectedLevelsText.append(level.getLevelName()).append(", ");
        }
        if (selectedLevelsText.length() > 0) {
            selectedLevelsText.delete(selectedLevelsText.length() - 2, selectedLevelsText.length()); // Remove trailing comma
        }
        levelMenuButton.setText(selectedLevelsText.toString());
    }

    private Level findLevelByName(String levelName) {
        for (Level level : levelsRepo.getAll()) {
            if (level.getLevelName().equals(levelName)) {
                return level;
            }
        }
        return null;
    }
    @FXML
    void Subject(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/student/studentmanagement/Subjects.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
